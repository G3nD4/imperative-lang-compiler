package Nodes.statement;

import Nodes.Body;
import Nodes.expression.Expression;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class WhileLoop extends Statement {
    public final Expression condition;
    public final Body body;


    @Override
    public String toString(String indent) {
        IndentManager.print("While Loop:");
        IndentManager.goDown();

        IndentManager.print("condition:");
        IndentManager.goDown();
        IndentManager.print(condition.toString(""));
        IndentManager.goUp();

        IndentManager.print(body.toString(""));

        IndentManager.goUp();
        return "";
    }

    public WhileLoop(Expression condition, Body body) {
        this.condition = condition;
        this.body = body;
    }

    public static WhileLoop parse(ParseTree tree, MyLangParser parser) {
        return new WhileLoop(Expression.parse(tree.getChild(1), parser), Body.parse(tree.getChild(4), parser));
    }
}
