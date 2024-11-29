package Nodes.expression;

import Nodes.Interfaces.JasminConvertable;
import Nodes.Enums.Sign;
import Nodes.Enums.Type;
import Nodes.jasmine.CodeGenerator;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class AdditiveExpression extends Expression implements JasminConvertable {
    public ArrayList<MultiplicativeExpression> operands;
    public ArrayList<Sign> operations;

    public AdditiveExpression(ArrayList<MultiplicativeExpression> operands, ArrayList<Sign> operations, Type type) {
        this.operands = operands;
        this.operations = operations;
        super.type = type;
    }

    public static AdditiveExpression parse(ParseTree tree, MyLangParser parser) {
        ArrayList<MultiplicativeExpression> operands = new ArrayList<>();
        ArrayList<Sign> operations = new ArrayList<>();
        for (int childCounter = 0; childCounter < tree.getChildCount(); childCounter++) {
            if (childCounter % 2 == 0) {
                // So it is MultiplicativeExpression
                operands.add(MultiplicativeExpression.parse(tree.getChild(childCounter), parser));
            } else {
                // operation
                operations.add(Sign.fromString(String.valueOf(tree.getChild(childCounter))));
            }
        }

        Type type = Type.INTEGER;
        if (operands.size() == 1) {
            // FIXME: type could be null (theoretically)
            return new AdditiveExpression(operands, operations, operands.getFirst().type == null ? operands.getFirst().returnType : operands.getFirst().type);
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

        return new AdditiveExpression(operands, operations, type);
    }

    @Override
    public String toString(String indent) {
        IndentManager.print("Additive Expression:");
        IndentManager.goDown();
        for (int i = 0; i < operands.size(); ++i) {
            if (i != 0) {
                IndentManager.print(operations.get(i - 1).toString());
            }
            IndentManager.print(operands.get(i).toString(""));
        }
        IndentManager.goUp();

        return "";
    }

    @Override
    public void generateCode(CodeGenerator generator) {
        // If this expression is needed only for priority handling, delegate generateCode to its child.
        if (operands.size() == 1) {
            operands.getFirst().generateCode(generator);
            return;
        }

        final boolean mustReturnReal = (this.type == Type.REAL) || mustReturnReal();

        for (int i = 0; i < operands.size(); ++i) {
            // execute operand's expressions
            operands.get(i).generateCode(generator);
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
                        case Sign.PLUS -> generator.writeToProgram("fadd");
                        case Sign.MINUS -> generator.writeToProgram("fsub");
                    }
                }
            } else {
                if (i != 0) {
                    switch (operations.get(i - 1)) {
                        case Sign.PLUS -> generator.writeToProgram("iadd");
                        case Sign.MINUS -> generator.writeToProgram("isub");
                    }
                }
            }
        }
    }

    private boolean mustReturnReal() {
        for (final MultiplicativeExpression exp : operands) {
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
        return false;
    }
}
