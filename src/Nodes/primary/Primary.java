package Nodes.primary;

import Nodes.Enums.Type;
import Nodes.expression.Expression;
import Nodes.expression.LogicalOrExpression;
import Nodes.expression.UnaryExpression;
import Nodes.jasmine.CodeGenerator;
import Nodes.statement.RoutineCallStatement;
import main.MyLangParser;
import main.TreeBuilder;
import org.antlr.v4.runtime.tree.ParseTree;

public abstract class Primary<T> {
    public Expression expression;
    public ModifiablePrimary modifiablePrimary;
    public RoutineCallStatement routineCall;
    public T value;
    public Type type;

    public Type getType(CodeGenerator generator) {
//        if (this instanceof UnaryExpression
//                && ((UnaryExpression) this).primary instanceof ModifiablePrimary modPrim) {
//            return modPrim.getType(generator);
//        }

        if (type != null) {
        return type;
        }
        if (expression != null) {
            return expression.returnType;
        }
        if (modifiablePrimary != null) {
            return generator.getVariable(modifiablePrimary.identifier).getType();
        }
        if (routineCall != null) {
            return generator.getRoutineInfo(routineCall.getIdentifier()).getReturnType();
        }

//        Primary prim = this;
//        if (this instanceof LogicalOrExpression) {
//            if (((LogicalOrExpression)this).)
//        }

        System.out.println("Cannot determine type!");
        System.exit(1);

        return null;
    }

    boolean hasChildren() {
        return expression!= null || modifiablePrimary != null || routineCall != null;
    }

    public static Primary parse(ParseTree tree, MyLangParser parser) {
        ParseTree child = tree.getChild(0);
        if (child.getChildCount() == 0 && !child.getText().equals("(")) {
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
                            try {
                                return ModifiablePrimary.parse(child, parser);
                            } catch (Exception e3) {
                                throw new IllegalStateException("Incorrect value for Primary");
                            }
                        }
                    }
            }
        } else {
            if (child.getText().equals("(")) {
                child = tree.getChild(1);
            }
            String nextStep = TreeBuilder.TreeToRule(child, parser);
            switch (nextStep) {
                case "routineCall":
                    return RoutineCallPrimary.parse(child, parser);
                case "modifiablePrimary":
                    return ModifiablePrimary.parse(child, parser);
                case "expression":
                    return Expression.parse(child, parser);
            }
            System.out.println(TreeBuilder.TreeToRule(tree.getChild(0), parser));
            return null;
        }
    }

    public abstract String toString(String indent);


}
