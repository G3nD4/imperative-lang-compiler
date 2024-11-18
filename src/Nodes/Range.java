package Nodes;

import Nodes.expression.Expression;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class Range {
    Expression leftExpression;
    Expression rightExpression;

    public Expression getStart() {
        return leftExpression;
    }

    public Expression getEnd() {
        return rightExpression;
    }

    public Range(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public static Range parse(ParseTree tree, MyLangParser parser) {
        return new Range(Expression.parse(tree.getChild(0), parser), Expression.parse(tree.getChild(2), parser));
    }



    public String toString(String indent) {
        IndentManager.print("Range:");
        IndentManager.goDown();
        IndentManager.print(leftExpression.toString(""));
        IndentManager.print("..");
        IndentManager.print(rightExpression.toString(""));
        IndentManager.goUp();

        return "";
    }
}
