package Nodes.statement;

import Nodes.Body;
import Nodes.expression.Expression;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class WhileLoop extends Statement {
    public final Expression condition;
    public final Body body;


    @Override
    public String toString(String indent) {
        return "WhileLoop: |" +
                indent + "          " + "--- condition=" + condition.toString(indent + "          " + "             ") + '\n' +
                indent + "          " + "--- body=" + body.toString(indent + "          " + "         ") +
                '\n';
    }

    public WhileLoop(Expression condition, Body body) {
        this.condition = condition;
        this.body = body;
    }

    public static WhileLoop parse(ParseTree tree, MyLangParser parser) {
        return new WhileLoop(Expression.parse(tree.getChild(1), parser), Body.parse(tree.getChild(4), parser));
    }
}
