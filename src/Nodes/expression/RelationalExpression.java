package Nodes.expression;

import Lexical_analyzer.TokenType;
import Nodes.JasminConvertable;
import Nodes.Type;
import Nodes.jasmine.CodeGenerator;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class RelationalExpression extends Expression implements JasminConvertable {
    public ArrayList<AdditiveExpression> operands;
    public ArrayList<TokenType> operations;

    public RelationalExpression(ArrayList<AdditiveExpression> operands, ArrayList<TokenType> operations, Type type) {
        this.operands = operands;
        this.operations = operations;
        super.type = type;
    }

    @Override
    public String toString(String indent) {
        IndentManager.print("Relational Expression:");
        IndentManager.goDown();
        for (int i = 0; i < operands.size(); ++i) {
            IndentManager.print(operands.get(i).toString(""));
            if (i != 0 && i < (operands.size() - 1)) {
                IndentManager.print(operations.get(i - 1).toString());
            } else if (i == 0 && !operations.isEmpty()) {
                IndentManager.print(operations.getFirst().toString());
            }
        }
        IndentManager.goUp();

        return "";
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

        if (operands.size() > 2) {
            System.out.println("Relational expression must have exactly 2 operands! Your expression contains " + operands.size() + ".");
            System.exit(1);
        }

        return new RelationalExpression(operands, operations, Type.BOOLEAN);
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

        // Switch on the operation to generate the appropriate comparison code
        /*
        ATTENTION: need to flip operations, because Jasmin
        compares LAST value with PRE-last
        5 -> 8 for GREATER_THAN will be compared as 8 > 5
         */
        generator.writeToProgram("swap");
        switch (operations.getFirst()) {
            case LESS_THAN: {
                // Compare if value1 < value2
                generator.writeToProgram("fcmpl"); // Compare two floats
                String labelTrue = generator.generateUniqueLabel("less_than");  // Unique label for less-than comparison
                generator.writeToProgram("iflt " + labelTrue); // Jump to true case if x < y
                generator.writeToProgram("iconst_0"); // Push 0 (false) onto the stack
                String labelEnd = generator.generateUniqueLabel("end");  // End label for jump after false case
                generator.writeToProgram("goto " + labelEnd);

                generator.writeLabel(labelTrue); // True case: push 1 (true) onto the stack
                generator.writeToProgram("iconst_1");
                generator.writeLabel(labelEnd);
                generator.writeToProgram("invokevirtual java/io/PrintStream/println(I)V");
                generator.writeToProgram("return");
                break;
            }
            case GREATER_THAN: {
                // Compare if value1 > value2
                generator.writeToProgram("fcmpl"); // Compare two floats
                String labelTrue = generator.generateUniqueLabel("greater_than");  // Unique label for greater-than comparison
                generator.writeToProgram("ifgt " + labelTrue); // Jump to true case if x > y
                generator.writeToProgram("iconst_0"); // Push 0 (false) onto the stack
                String labelEnd = generator.generateUniqueLabel("end");  // End label for jump after false case
                generator.writeToProgram("goto " + labelEnd);

                generator.writeLabel(labelTrue); // True case: push 1 (true) onto the stack
                generator.writeToProgram("iconst_1");
                generator.writeLabel(labelEnd);
                generator.writeToProgram("invokevirtual java/io/PrintStream/println(I)V");
                generator.writeToProgram("return");
                break;
            }
            case LESS_EQUAL: {
                // Compare if value1 <= value2
                generator.writeToProgram("fcmpl"); // Compare two floats
                String labelTrue = generator.generateUniqueLabel("less_equal");  // Unique label for less-than-equal comparison
                generator.writeToProgram("ifle " + labelTrue); // Jump to true case if x <= y
                generator.writeToProgram("iconst_0"); // Push 0 (false) onto the stack
                String labelEnd = generator.generateUniqueLabel("end");  // End label for jump after false case
                generator.writeToProgram("goto " + labelEnd);

                generator.writeLabel(labelTrue); // True case: push 1 (true) onto the stack
                generator.writeToProgram("iconst_1");
                generator.writeLabel(labelEnd);
                generator.writeToProgram("invokevirtual java/io/PrintStream/println(I)V");
                generator.writeToProgram("return");
                break;
            }
            case GREATER_EQUAL: {
                // Compare if value1 >= value2
                generator.writeToProgram("fcmpl"); // Compare two floats
                String labelTrue = generator.generateUniqueLabel("greater_equal");  // Unique label for greater-than-equal comparison
                generator.writeToProgram("ifge " + labelTrue); // Jump to true case if x >= y
                generator.writeToProgram("iconst_0"); // Push 0 (false) onto the stack
                String labelEnd = generator.generateUniqueLabel("end");  // End label for jump after false case
                generator.writeToProgram("goto " + labelEnd);

                generator.writeLabel(labelTrue); // True case: push 1 (true) onto the stack
                generator.writeToProgram("iconst_1");
                generator.writeLabel(labelEnd);
                generator.writeToProgram("invokevirtual java/io/PrintStream/println(I)V");
                generator.writeToProgram("return");
                break;
            }
            default:
                throw new IllegalStateException("Unexpected operation: " + operations.get(0));
        }
    }

}
