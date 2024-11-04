package Nodes.expression;

import Lexical_analyzer.TokenType;
import Nodes.Type;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class RelationalExpression extends Expression {
    public ArrayList<AdditiveExpression> operands;
    public ArrayList<TokenType> operations;

    public RelationalExpression(ArrayList<AdditiveExpression> operands, ArrayList<TokenType> operations, Type type) {
        this.operands = operands;
        this.operations = operations;
        super.type = type;
    }

    @Override
    public String toString(String indent) {
        StringBuilder result = new StringBuilder();
        String applyedIndent = indent;
        if (operands.size() < 2) {
            applyedIndent = "";
        }
        for (int i = 0; i < operands.size(); ++i) {
            result.append(applyedIndent).append(operands.get(i).toString(indent)).append("\n");
            if (i != 0 && i < (operands.size() - 1)) {
                result.append(applyedIndent).append(operations.get(i - 1).toString()).append("\n");
            } else if (i == 0 && !operations.isEmpty()) {
                result.append(applyedIndent).append(operations.getFirst().toString()).append("\n");
            }
        }

        return result.toString();
    }

    public static RelationalExpression parse(ParseTree tree, MyLangParser parser) {
        ArrayList<AdditiveExpression> operands = new ArrayList<>();
        ArrayList<TokenType> operations = new ArrayList<>();
        for (int childCounter = 0; childCounter < tree.getChildCount(); childCounter++) {
            if (childCounter % 2 == 0) {
                // So it is AdditiveExpression
                operands.add(AdditiveExpression.parse(tree.getChild(childCounter), parser));
            } else {
                // operation
                TokenType operation = switch (String.valueOf(tree.getChild(childCounter))) {
                    case "<" -> TokenType.LESS_THAN;
                    case ">" -> TokenType.GREATER_THAN;
                    case "<=" -> TokenType.LESS_EQUAL;
                    case ">=" -> TokenType.GREATER_EQUAL;
                    default -> throw new IllegalStateException("Unexpected value: " + tree.getChild(childCounter));
                };
                operations.add(operation);
            }

        }

        if (operands.size() == 1) {
            // FIXME: type could be null (theoretically)
            return new RelationalExpression(operands, operations, operands.getFirst().type == null ? operands.getFirst().returnType : operands.getFirst().type);
        }
        for (int i = 0; i < operands.size(); ++i) {
            if (operands.get(i).type == Type.BOOLEAN || operands.get(i).returnType == Type.BOOLEAN) {
                System.out.println("Unsupported operation " + operations.get(i == 0 ? 0 : i - 1).toString() + " for BOOLEAN");
                System.exit(1);
            }
        }

        return new RelationalExpression(operands, operations, Type.BOOLEAN);
    }
}
