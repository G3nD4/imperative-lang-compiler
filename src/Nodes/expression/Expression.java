package Nodes.expression;

import Nodes.primary.Primary;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public abstract class Expression {
    public static Expression parse(ParseTree tree, MyLangParser parser) {
        ParseTree expSubtree = tree;
        int depth = 0;
        Expression expression = null;
        System.out.print('\n');
        System.out.println("i am in exp parse");
        while (expSubtree.getChild(0).getChildCount() != 0) {
            expSubtree = expSubtree.getChild(0);
            switch (depth) {
                case 0:
                    expression = LogicalOrExpression.parse(expSubtree, parser);
                    if (expSubtree.getChildCount() > 1) {
                        return expression;
                    }
                    break;
                case 1:
                    expression = LogicalAndExpression.parse(expSubtree, parser);
                    if (expSubtree.getChildCount() > 1) {
                        return expression;
                    }
                    break;
                case 2:
                    expression = EqualityExpression.parse(expSubtree, parser);
                    if (expSubtree.getChildCount() > 1) {
                        return expression;
                    }
                    break;
                case 3:
                    expression = RelationalExpression.parse(expSubtree, parser);
                    if (expSubtree.getChildCount() > 1) {
                        return expression;
                    }
                    break;
                case 4:
                    expression = AdditiveExpression.parse(expSubtree, parser);
                    if (expSubtree.getChildCount() > 1) {
                        return expression;
                    }
                    break;
                case 5:
                    expression = MultiplicativeExpression.parse(expSubtree, parser);
                    break;
            }
            ++depth;
        }
        return expression;
    }

}
