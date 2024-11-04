package Nodes.expression;

import Nodes.Operation;
import Nodes.Type;
import main.MyLangParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;


public class MultiplicativeExpression extends Expression {
    public ArrayList<UnaryExpression> operands;
    public ArrayList<Operation> operations;

    public MultiplicativeExpression(ArrayList<UnaryExpression> operands, ArrayList<Operation> operations, Type type) {
        this.operands = operands;
        this.operations = operations;
        super.type = type;
    }

    public static MultiplicativeExpression parse(ParseTree tree, MyLangParser parser) {
        ArrayList<UnaryExpression> operands = new ArrayList<>();
        ArrayList<Operation> operations = new ArrayList<>();
        for (int childCounter = 0; childCounter < tree.getChildCount(); childCounter++) {
            if (childCounter % 2 == 0) {
                // So it is UnaryExpression
                operands.add(UnaryExpression.parse(tree.getChild(childCounter), parser));
            } else {
               // operation
                Operation operation = switch (String.valueOf(tree.getChild(childCounter))) {
                    case "*" -> Operation.MULTIPLY;
                    case "/" -> Operation.DIVIDE;
                    case "%" -> Operation.MODULO;
                    default ->
                            throw new IllegalStateException("Unexpected value: " + tree.getChild(childCounter));
                };
                operations.add(operation);
            }

        }

        Type type = Type.INTEGER;
        if (operands.size() == 1) {
            // FIXME: type could be null (theoretically)
            return new MultiplicativeExpression(operands, operations, operands.getFirst().type == null ? operands.getFirst().returnType : operands.getFirst().type);
        }
        for (int i = 0; i < operands.size(); ++i) {
            if (operands.get(i).type == Type.BOOLEAN || operands.get(i).returnType == Type.BOOLEAN) {
                System.out.println("Unsupported operation " + operations.get(i == 0 ? 0 : i - 1).toString() + " for BOOLEAN");
                System.exit(1);
            }
            if (operands.get(i).type == Type.REAL || operands.get(i).returnType == Type.REAL) {
                type = Type.REAL;
                break;
            }
        }
        if (type != Type.REAL) {
            for (Operation operation : operations) {
                if (operation == Operation.DIVIDE) {
                    type = Type.REAL;
                    break;
                }
            }
        }

        return new MultiplicativeExpression(operands, operations, type);
    }

    @Override
    public String toString(String indent) {
        StringBuilder result = new StringBuilder();
        String applyedIndent = indent;
        if (operands.size() < 2) {
            applyedIndent = "";
        }
        for (int i = 0; i < operands.size(); ++i) {
            result.append(applyedIndent).append(operands.get(i).toString(indent)).append('\n');
            if (i != 0 && i < operands.size() - 1) {
                result.append(applyedIndent).append(operations.get(i - 1).toString()).append('\n');
            }
        }
        return result.toString();
    }
}
