package Nodes;

import Nodes.Enums.Type;
import Nodes.expression.Expression;
import Nodes.expression.UnaryExpression;
import Nodes.jasmine.CodeGenerator;
import Nodes.primary.BooleanLiteral;
import Nodes.primary.IntegerLiteral;
import Nodes.primary.ModifiablePrimary;
import Nodes.primary.RealLiteral;
import main.IndentManager;
import main.MyLangParser;
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

    public static VariableDeclaration parse(ParseTree tree, MyLangParser parser) {
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
        if (var.type == null) {
            var.type = var.expression.returnType;
        }
        if (var.type == null) {
            var.type = var.expression.type;
        }

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
        if (expression instanceof UnaryExpression) {
            final UnaryExpression exp = (UnaryExpression) expression;
            if (exp.primary instanceof ModifiablePrimary) {
                final ModifiablePrimary modifiablePrimary = (ModifiablePrimary)exp.primary;
                generator.writeToProgram(modifiablePrimary.getLoadCode(generator));
                generator.writeToProgram(modifiablePrimary.getStoreCode(generator, index));
            } else {
                if (exp.primary instanceof IntegerLiteral) {
                    generator.writeToProgram("ldc " + ((IntegerLiteral) exp.primary).getValue().toString());
                } else if (exp.primary instanceof BooleanLiteral) {
                    generator.writeToProgram("ldc " + ((BooleanLiteral)exp.primary).jasmineConst());
                } else if (exp.primary instanceof RealLiteral) {
                    generator.writeToProgram("ldc " + ((RealLiteral)exp.primary).value.toString());
                }
                // TODO: handle Expression
                switch (type) {
                    case Type.BOOLEAN, Type.INTEGER:
                        generator.writeToProgram("istore_" + index);
                        break;
                    case Type.REAL:
                        generator.writeToProgram("fstore_" + index);
                        break;
                    default:
                        System.out.println("Type " + type.name() + " is not supported!");
                        System.exit(1);
                }
            }
        }
    }
}
