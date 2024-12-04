package Nodes.statement.Declarations;

import Nodes.Enums.Type;
import Nodes.Interfaces.JasminConvertable;
import Nodes.Program;
import Nodes.expression.Expression;
import Nodes.expression.UnaryExpression;
import Nodes.jasmine.CodeGenerator;
import Nodes.primary.*;
import main.IndentManager;
import main.antlrTree.MyLangParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

public class VariableDeclaration extends Declaration implements JasminConvertable {
    private String identifier;
    private Type type;
    private Expression expression;

    public VariableDeclaration(String identifier, Type type, Expression expression) {
        this.identifier = identifier;
        this.type = type;
        this.expression = expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public static VariableDeclaration parse(ParseTree tree, MyLangParser parser) {
        // Need to handle modifiable primary types. Does not affect parse method logic.
        VariableDeclaration var = new VariableDeclaration("", null, null);

        var.identifier = tree.getChild(1).getText();
        var.type = !tree.getChild(2).getText().equals(":") ? null : Type.fromString(tree.getChild(3).getText());
        int valueIndex = var.type == null ? 3 : 5;
        var.expression = Expression.parse(tree.getChild(valueIndex), parser);

        // TODO: apply type inference

        // SEMANTIC ERROR CHECK
        if (var.expression == null && var.type == null) {
            System.out.println("Variable must have at least type or value!");
            System.out.println("Error occurred on line: " + ((ParserRuleContext) tree).start.getLine() + " at character: " + ((ParserRuleContext) tree).start.getCharPositionInLine());
            System.exit(1);
        }


//        if (var.expression == null) {
//            if (var.type == Type.INTEGER) {
//            }
//        }

        if (var.type == null) {
            var.type = var.expression.returnType;
        }
        if (var.type == null) {
            var.type = var.expression.type;
        }

        Program.registerVariable(var.getIdentifier(), var.type);

        return var;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Type getType() {
        return type;
    }

    public Expression getExpression() {
        return expression;
    }

    public String toString(String indent) {
        IndentManager.print("VariableDeclaration:");
        IndentManager.goDown();
        IndentManager.print("identifier: " + identifier);
        if (type != null) {
            IndentManager.print("type: " + type.toString().toLowerCase());
        }
        if (expression != null) {
            IndentManager.print("initializing expression: " + expression.toString(""));
        }
        IndentManager.goUp();
        return "";
    }

    @Override
    public void generateCode(CodeGenerator generator) {
        generator.registerVariable(identifier, type);
        int index = generator.getCurrentStackIndex();

        // ERROR: variable type and expression type do not match
        if (this.expression != null && this.type != this.expression.type) {
            System.out.println("Error in " + this.identifier + " variable declaration. Should be " + this.type
                    + ", but " + this.expression.type + " was provided. [VariableDeclaration]");
            System.exit(1);
        }
        if (expression instanceof UnaryExpression exp) {
            if (exp.primary instanceof ModifiablePrimary modifiablePrimary) {
                generator.writeToProgram(modifiablePrimary.getLoadCode(generator));
                generator.writeToProgram(modifiablePrimary.getStoreCode(generator, index));
            } else {
                if (exp.primary instanceof IntegerLiteral) {
                    generator.writeToProgram("ldc " + ((IntegerLiteral) exp.primary).getValue().toString());
                } else if (exp.primary instanceof BooleanLiteral) {
                    generator.writeToProgram("ldc " + ((BooleanLiteral) exp.primary).jasmineConst());
                } else if (exp.primary instanceof RealLiteral) {
                    generator.writeToProgram("ldc " + ((RealLiteral) exp.primary).value.toString());
                } else if (exp.primary instanceof RoutineCallPrimary) {
                    ((RoutineCallPrimary) exp.primary).generateCode(generator);
                }

                if (exp.primary.type != this.type) {
                    System.out.println("Variable " + this.identifier + " should have type "
                            + this.type.toString() + ". But " + exp.primary.type +
                            " was provided in his expression. [VariableDeclaration]");
                    System.exit(1);
                    return;
                }

                switch (type) {
                    case Type.BOOLEAN, Type.INTEGER:
                        generator.writeToProgram("istore " + index);
                        break;
                    case Type.REAL:
                        generator.writeToProgram("fstore " + index);
                        break;
                    default:
                        System.out.println("Type " + type.name() + " is not supported!");
                        System.exit(1);
                }
            }
        } else {
            // In case "var result: integer" we need to store only address
            if (expression == null) {
                return;
            }
            expression.generateCode(generator);
            switch (type) {
                case Type.BOOLEAN, Type.INTEGER:
                    generator.writeToProgram("istore " + index);
                    break;
                case Type.REAL:
                    generator.writeToProgram("fstore " + index);
                    break;
                default:
                    System.out.println("Type " + type.name() + " is not supported!");
                    System.exit(1);
            }
        }
    }
}
