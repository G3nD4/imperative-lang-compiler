package Nodes.expression;

import Nodes.JasminConvertable;
import Nodes.Sign;
import Nodes.Type;
import Nodes.jasmine.CodeGenerator;
import Nodes.jasmine.JasminLoadable;
import Nodes.primary.*;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class UnaryExpression extends Expression implements JasminLoadable, JasminConvertable {
    public Sign sign;
    public Primary primary;

    public UnaryExpression(Sign sign, Primary primary, Type type) {
        this.sign = sign;
        this.primary = primary;
        super.type = type;
    }

    public static UnaryExpression parse(ParseTree tree, MyLangParser parser) {
        Sign sign = Sign.PLUS;

        if (tree.getChildCount() == 1) {
            // we have only Primary
            final Primary _primary = Primary.parse(tree.getChild(0), parser);
            return new UnaryExpression(sign, _primary, _primary != null ? _primary.type : null);
        } else {
            // we have sign and Primary
            sign = switch (String.valueOf(tree.getChild(0))) {
                case "+" -> Sign.PLUS;
                case "-" -> Sign.MINUS;
                case "not" -> Sign.NOT;
                default -> throw new IllegalStateException("Unexpected value: " + tree.getChild(0));
            };
            final Primary _primary = Primary.parse(tree.getChild(1), parser);
            return new UnaryExpression(sign, _primary, _primary != null ? _primary.type : null );
        }
    }



    @Override
    public String toString(String indent) {
        IndentManager.print("Unary Expression:");

        IndentManager.goDown();

        IndentManager.print("sign:" + sign.toString().toLowerCase());
        IndentManager.print(primary.toString(""));

        IndentManager.goUp();

        return "";
    }

    @Override
    public void generateCode(CodeGenerator generator) {
        generator.writeToProgram(getLoadCode(generator));
    }
    @Override
    /*
    Loads variable with respect to its sign: PLUS, MINUS, NOT;

    How works:
        - loads variable
        - handles its sign

    Result:
        - program has result of this unary expression on top of the stack;
     */
    public String getLoadCode(CodeGenerator generator) {
        final StringBuilder code = new StringBuilder();

        // if this unary expression is applied to variable
        if (primary instanceof ModifiablePrimary) {
            // load variable's value
            generator.writeToProgram(((ModifiablePrimary) primary).getLoadCode(generator));
        } else {
            // load constant value
            if (type == Type.INTEGER || type == Type.BOOLEAN) {
                if (type == Type.INTEGER) {
                    code.append("ldc ").append(((IntegerLiteral)primary).getValue()).append("\n");
                } else {
                    code.append(((BooleanLiteral)primary).getLoadCode(generator)).append("\n");
                }
            } else if (type == Type.REAL) {
                code.append("ldc ").append(((RealLiteral)primary).getValue()).append("\n");
            }
        }

        Type expType;
        // if this unary expression is applied to variable, get its type
        if (type == Type.IDENTIFIER) {
            assert primary instanceof ModifiablePrimary;
            expType = generator.getVariable(((ModifiablePrimary) primary).identifier).getType();
        } else {
            expType = type;
        }

        // handle sign
        switch (sign) {
            case Sign.PLUS:
                // do nothing
                break;
            case Sign.MINUS:
                // negate value if it is INTEGER or REAL, throw error if it is BOOLEAN
                if (expType == Type.BOOLEAN) {
                    System.out.println("Cannot apply '-' to BOOLEAN type!");
                    System.exit(1);
                }
                if (expType == Type.INTEGER) {
                    code.append("ineg\n");
                } else if (expType == Type.REAL) {
                    code.append("fneg\n");
                }
                break;
            case Sign.NOT:
                // NOT is applied only to BOOLEAN

                // catch errors if any
                catchErrorsForNotIfAny(expType);

                // apply not
                if (expType == Type.BOOLEAN) {
                    if (primary instanceof BooleanLiteral) {
                        // if initial value is true, load [not true] to stack, which is 0;
                        // load 1 otherwise
                        code.append("ineg\n");
                    }
                }
        }
        return code.toString();
    }

    private void catchErrorsForNotIfAny(Type type) {
        if (type == null) {
            System.out.println("The type of the variable is not defined!");
            System.exit(1);
        }
        if (type == Type.INTEGER || type == Type.REAL) {
            System.out.println("Cannot apply NOT to " + type.name().toUpperCase());
            System.exit(1);
        }
    }
}
