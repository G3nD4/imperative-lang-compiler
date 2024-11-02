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
//                expression = LogicalOrExpression.parse();
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    expression = MultiplicativeExpression.parse(expSubtree, parser);
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
//                expression = new Primary.parse(expSubtree, parser);
                    break;
                default: {
                }// 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2
            }
            ++depth;
        }
        return expression;
    }

}
