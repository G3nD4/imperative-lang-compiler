package Nodes.expression;

import Nodes.Interfaces.JasminConvertable;
import Nodes.Enums.Operation;
import Nodes.Enums.Type;
import Nodes.jasmine.CodeGenerator;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;


public class MultiplicativeExpression extends Expression implements JasminConvertable {
    public ArrayList<UnaryExpression> operands;
    public ArrayList<Operation> operations;

    public MultiplicativeExpression(ArrayList<UnaryExpression> operands, ArrayList<Operation> operations, Type type) {
        this.operands = operands;
        this.operations = operations;
        super.type = type;
        super.returnType = type;
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
                    default -> throw new IllegalStateException("Unexpected value: " + tree.getChild(childCounter));
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
        IndentManager.print("Multiplicative Expression:");
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

    @Override
    public void generateCode(CodeGenerator generator) {
        // If this expression is needed only for priority handling, delegate generateCode to its child.
        if (operands.size() == 1) {
            generator.writeToProgram(operands.getFirst().getLoadCode(generator));
            return;
        }

        final boolean mustReturnReal = (this.type == Type.REAL) || mustReturnReal();

        for (int i = 0; i < operands.size(); ++i) {
            // load each operand's value
            final String loadValueInstructionCode = operands.get(i).getLoadCode(generator).trim();
            // write load instruction into program
            generator.writeToProgram(loadValueInstructionCode);
            // handle wrong type
            if (operands.get(i).getType(generator) == Type.BOOLEAN) {
                System.out.println("Operation prohibited for type BOOLEAN!");
                System.exit(1);
            }
            if (mustReturnReal) {
                // convert integer value to float value if needed
                if (operands.get(i).getType(generator) == Type.INTEGER) {
                    generator.writeToProgram("i2f");
                }
                if (i != 0) {
                    switch (operations.get(i - 1)) {
                        case Operation.MULTIPLY -> generator.writeToProgram("fmul");
                        case Operation.DIVIDE -> generator.writeToProgram("fdiv");
                        case Operation.MODULO -> generator.writeToProgram("frem");
                    }
                }
            } else {
                if (i != 0) {
                    switch (operations.get(i - 1)) {
                        case Operation.MULTIPLY -> generator.writeToProgram("imul");
                        case Operation.DIVIDE -> generator.writeToProgram("idiv");
                        case Operation.MODULO -> generator.writeToProgram("irem");
                    }
                }
            }
        }
    }

    private boolean mustReturnReal() {
        for (final UnaryExpression exp : operands) {
            if (exp.type == Type.REAL) {
                return true;
            }
            if (exp.type == Type.IDENTIFIER) {
                System.out.println("This type MUST NOT appear during code-generation process! FIX OPTMIZATION!");
                System.exit(666);
//                if (exp.primary.getType(generator) == Type.REAL) {
//                    return true;
//                }
            }
        }
        for (final Operation operation : operations) {
            if (operation == Operation.DIVIDE) {
                return true;
            }
        }
        return false;
    }
}
