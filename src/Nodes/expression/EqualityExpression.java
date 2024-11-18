package Nodes.expression;

import Lexical_analyzer.TokenType;
import Nodes.JasminConvertable;
import Nodes.Type;
import Nodes.jasmine.CodeGenerator;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class EqualityExpression extends Expression implements JasminConvertable {
    public ArrayList<RelationalExpression> operands;
    public ArrayList<TokenType> operations;

    public EqualityExpression(ArrayList<RelationalExpression> operands, ArrayList<TokenType> operations, Type type) {
        this.operands = operands;
        this.operations = operations;
        super.type = type;
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

        if (operands.size() > 2) {
            System.out.println("Relational expression must have exactly 2 operands! Your expression contains " + operands.size() + ".");
            System.exit(1);
        }

        return new EqualityExpression(operands, operations, Type.BOOLEAN);
    }

    @Override
    public void generateCode(CodeGenerator generator) {
        if (operands.size() == 1) {
            operands.getFirst().generateCode(generator);
            return;
        }

        // Generate code for the first operand
        operands.get(0).generateCode(generator);
        if (operands.get(0).getType(generator) == Type.INTEGER) {
            generator.writeToProgram("i2f");
        }

        // Generate code for the second operand
        operands.get(1).generateCode(generator);
        if (operands.get(1).getType(generator) == Type.INTEGER) {
            generator.writeToProgram("i2f");
        }

        // Generate unique labels
        String trueLabel = generator.generateUniqueLabel("true");
        String endLabel = generator.generateUniqueLabel("end");

        // For floating-point comparison
        generator.writeToProgram("fcmpl");

        switch (operations.getFirst()) {
            case EQUAL -> generator.writeToProgram("ifeq " + trueLabel);
            case NOT_EQUAL -> generator.writeToProgram("ifne " + trueLabel);
            default -> throw new IllegalStateException("Unexpected operation: " + operations.getFirst());
        }

        // Push false and jump to end
        generator.writeToProgram("iconst_0");
        generator.writeToProgram("goto " + endLabel);

        // Push true
        generator.writeLabel(trueLabel);
        generator.writeToProgram("iconst_1");

        // End label
        generator.writeLabel(endLabel);
    }


    @Override
    public String toString(String indent) {
        IndentManager.print("Equality Expression:");
        IndentManager.goDown();
        for (int i = 0; i < operands.size(); ++i) {
            IndentManager.print(operands.get(i).toString(""));
            if (i != 0 && i < operands.size() - 1) {
                IndentManager.print(operations.get(i - 1).toString());
            }
        }
        IndentManager.goUp();

        return "";
    }
}
