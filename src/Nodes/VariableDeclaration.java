package Nodes;

import Nodes.expression.Expression;
import Nodes.statement.Statement;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

public class VariableDeclaration extends Declaration {
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
}
