package Nodes.expression;

import Lexical_analyzer.TokenType;
import Nodes.Type;
import Nodes.primary.Primary;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public abstract class Expression extends Primary {
    public Type returnType;


    public static Expression parse(ParseTree tree, MyLangParser parser) {
        ParseTree expSubtree = tree;
        int depth = 0;
        Expression expression = null;
        while (expSubtree.getChild(0).getChildCount() != 0) {
            expSubtree = expSubtree.getChild(0);
            switch (depth) {
                // Если у node только 1 ребёнок, то надо уходить вниз (кроме unaryExpression)
                // В таком случае игнорим этот expression и идём ниже
                case 0:
                    if (expSubtree.getChildCount() > 1) {
                        expression = LogicalOrExpression.parse(expSubtree, parser);
                        expression.returnType = Type.BOOLEAN;
                        return LogicalOrExpression.parse(expSubtree, parser);
                    }
                    break;
                case 1:
                    if (expSubtree.getChildCount() > 1) {
                        expression = LogicalAndExpression.parse(expSubtree, parser);
                        expression.returnType = Type.BOOLEAN;
                        return expression;
                    }
                    break;
                case 2:
                    if (expSubtree.getChildCount() > 1) {
                        expression = EqualityExpression.parse(expSubtree, parser);
                        expression.returnType = Type.BOOLEAN;
                        return expression;
                    }
                    break;
                case 3:
                    if (expSubtree.getChildCount() > 1) {
                        expression = RelationalExpression.parse(expSubtree, parser);
                        expression.returnType = Type.BOOLEAN;
                        return expression;
                    }
                    break;
                case 4:
                    if (expSubtree.getChildCount() > 1) {
                        expression = AdditiveExpression.parse(expSubtree, parser);
                        return expression;
                    }
                    break;
                case 5:
                    if (expSubtree.getChildCount() > 1) {
                        expression = MultiplicativeExpression.parse(expSubtree, parser);
                        return expression;
                    }
                    break;
                case 6:
                    expression = UnaryExpression.parse(expSubtree, parser);
                    break;
            }
            ++depth;
        }
        return expression;
    }

}
