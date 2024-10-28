package Nodes.expression;

import Nodes.Assignment;
import Nodes.Statement;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public abstract class Expression {
    public static Expression parse(String ruleName, ParseTree tree, MyLangParser parser) {
        ParseTree expSubtree = tree;
        int depth = 0;
        while (expSubtree.getChildCount() <= 1) {
            expSubtree = expSubtree.getChild(0);
            ++depth;
        }
        Expression expression;
        switch (depth) {
            case 0:
//                expression = LogicalOrExpression.parse();
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            default: {}// 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2
        }
        return null;
    }
}
