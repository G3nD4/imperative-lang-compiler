package Nodes.primary;

import main.MyLangParser;
import main.TreeBuilder;
import org.antlr.v4.runtime.tree.ParseTree;

public abstract interface Primary {
    public static Primary parse(ParseTree tree, MyLangParser parser) {
        ParseTree child = tree.getChild(0);
        Primary result;
        if (child.getChildCount() == 0) {
            // INTEGER_LITERAL | REAL_LITERAL | TRUE | FALSE
            String literal = String.valueOf(child);
            switch (literal) {
                case "true", "false":
                    result = BooleanLiteral.parse(child, parser);
                    break;
                default:
                    try {
                        Integer.parseInt(literal);
                        result = IntegerLiteral.parse(child, parser);
                    } catch (Exception e) {
                        try {
                            Double.parseDouble(literal);
                            result = RealLiteral.parse(child, parser);
                        } catch (Exception e2) {
                            throw new IllegalStateException("Incorrect value for Primary");
                        }
                    }
                    break;
            }
        } else {
            String nextStep = TreeBuilder.TreeToRule(child, parser);
            switch (nextStep) {
                case "routineCall":
//                    result = RoutineCallStatement.parse(child, parser);
                    break;
                case "modifiablePrimary":
                    break;
                case "expression":
                    break;
            }
            System.out.println(TreeBuilder.TreeToRule(tree.getChild(0), parser));
            result = null;
        }
        return result;
    }
}
