package Nodes.statement;

import Nodes.expression.Expression;
import main.MyLangParser;
import main.TreeBuilder;
import org.antlr.v4.runtime.tree.ParseTree;

public class Return extends Statement {
    public final Expression expression;

    @Override
    public String toString(String indent) {
        return "Return: |" +
                indent + "--- expression=" + expression.toString(indent + "               ") + '\n' +
                '\n';
    }

    public Return(Expression expression) {
        this.expression = expression;
    }

    public static Return parse(ParseTree tree, MyLangParser parser) {
        /*
        Can be empty (just "return")
        or with expression (return expression)
        */
        System.out.println(TreeBuilder.TreeToRule(tree, parser));
        if (tree.getChildCount() == 1) {
            return new Return(null);
        } else {
            return new Return(Expression.parse(tree.getChild(1), parser));
        }
    }
}
