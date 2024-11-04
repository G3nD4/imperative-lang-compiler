package Nodes;

import Nodes.expression.Expression;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class Range {
    Expression leftExpression;
    Expression rightExpression;

    public Range(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public static Range parse(ParseTree tree, MyLangParser parser) {
        return new Range(Expression.parse(tree.getChild(0), parser), Expression.parse(tree.getChild(2), parser));
    }

    public String toString(String indent) {
        return "Range:\n" +
                indent + "|\n" +
                indent + "--- left =" + leftExpression.toString(indent + "          ") + "\n" +
                indent + "--- right =" + rightExpression.toString(indent + "           ") + '\n';
    }
}
