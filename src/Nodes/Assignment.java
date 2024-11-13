package Nodes;

import Nodes.expression.Expression;
import Nodes.primary.ModifiablePrimary;
import Nodes.statement.Statement;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

public class Assignment extends Statement {
    public ModifiablePrimary assignee;
    public Expression expression;

    public Assignment(ModifiablePrimary assignee, Expression expression) {
        this.assignee = assignee;
        this.expression = expression;
    }

    public static Assignment parse(ParseTree tree, MyLangParser parser) {
        final ModifiablePrimary assignee = ModifiablePrimary.parse(tree.getChild(0), parser);
        final Expression expression = Expression.parse(tree.getChild(2), parser);
        return new Assignment(assignee, expression);
    }

    @Override
    public String toString(String indent) {
        IndentManager.print("Assignment:");
        IndentManager.goDown();
        IndentManager.print("assignee:");
        IndentManager.goDown();
        IndentManager.print(assignee.toString(""));
        IndentManager.goUp();
        IndentManager.print(expression.toString(""));
        IndentManager.goUp();

        return "";
    }
}
