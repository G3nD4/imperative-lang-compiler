package Nodes.primary;

import Nodes.expression.Expression;
import Nodes.statement.RoutineCallStatement;
import main.MyLangParser;
import main.TreeBuilder;
import org.antlr.v4.runtime.tree.ParseTree;

public abstract class Primary<T> {
    public Expression expression;
    public ModifiablePrimary<T> modifiablePrimary;
    public RoutineCallStatement routineCall;
    public T value;

    boolean hasChildren() {
        return expression!= null || modifiablePrimary != null || routineCall != null;
    }

    public static Object parse(ParseTree tree, MyLangParser parser) {
        ParseTree child = tree.getChild(0);
        if (child.getChildCount() == 0) {
            // INTEGER_LITERAL | REAL_LITERAL | TRUE | FALSE
            String literal = String.valueOf(child);
            switch (literal) {
                case "true", "false":
                    return BooleanLiteral.parse(child, parser);
                default:
                    try {
                        Integer.parseInt(literal);
                        return IntegerLiteral.parse(child, parser);
                    } catch (Exception e) {
                        try {
                            Double.parseDouble(literal);
                            return RealLiteral.parse(child, parser);
                        } catch (Exception e2) {
                            throw new IllegalStateException("Incorrect value for Primary");
                        }
                    }
            }
        } else {
            String nextStep = TreeBuilder.TreeToRule(child, parser);
            switch (nextStep) {
                case "routineCall":
                    return RoutineCallStatement.parse(child, parser);
                case "modifiablePrimary":
                    return ModifiablePrimary.parse(child, parser);
                case "expression":
                    return Expression.parse(child, parser);
            }
            System.out.println(TreeBuilder.TreeToRule(tree.getChild(0), parser));
            return null;
        }
    }
}
