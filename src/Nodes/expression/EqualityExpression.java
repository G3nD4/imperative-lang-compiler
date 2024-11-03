package Nodes.expression;

import Lexical_analyzer.TokenType;
import Nodes.Type;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class EqualityExpression extends Expression {
    public ArrayList<RelationalExpression> operands;
    public ArrayList<TokenType> operations;

    public EqualityExpression(ArrayList<RelationalExpression> operands, ArrayList<TokenType> operations, Type type) {
        this.operands = operands;
        this.operations = operations;
        super.type = type;
    }

    @Override
    public String toString() {
        return "EqualityExpression{" +
                "operands=" + operands +
                ", operations=" + operations +
                '}';
    }

    public static EqualityExpression parse(ParseTree tree, MyLangParser parser) {
        ArrayList<RelationalExpression> operands = new ArrayList<>();
        ArrayList<TokenType> operations = new ArrayList<>();
        for (int childCounter = 0; childCounter < tree.getChildCount(); childCounter++) {
            if (childCounter % 2 == 0) {
                // So it is RelationalExpression
                operands.add(RelationalExpression.parse(tree.getChild(childCounter), parser));
            } else {
                // operation
                TokenType operation = switch (String.valueOf(tree.getChild(childCounter))) {
                    case "=" -> TokenType.EQUAL;
                    case "!=" -> TokenType.NOT_EQUAL;
                    default -> throw new IllegalStateException("Unexpected value: " + tree.getChild(childCounter));
                };
                operations.add(operation);
            }

        }

        if (operands.size() == 1) {
            // FIXME: type could be null (theoretically)
            return new EqualityExpression(operands, operations, operands.getFirst().type == null ? operands.getFirst().returnType : operands.getFirst().type);
        }
        Type leftType = operands.getFirst().returnType;
        if (leftType == null) {
            leftType = operands.getFirst().type;
        }
        for (int i = 1; i < operands.size(); ++i) {
            Type rightType = operands.get(i).type == null ? operands.get(i).returnType : operands.get(i).type;
            if (rightType == Type.BOOLEAN || leftType == Type.BOOLEAN) {
                if (rightType != Type.BOOLEAN || leftType != Type.BOOLEAN) {
                    System.out.println("Unsupported operation " + operations.get(i - 1).toString() + " for BOOLEAN");
                    System.exit(1);
                }
            }
            leftType = Type.BOOLEAN;
        }
        // TODO: maybe need to handle (true == Int|Real) but we do not think so

        return new EqualityExpression(operands, operations, Type.BOOLEAN);
    }
}
