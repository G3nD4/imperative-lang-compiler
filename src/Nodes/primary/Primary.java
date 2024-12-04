package Nodes.primary;

import Nodes.Enums.Type;
import Nodes.expression.Expression;
import Nodes.expression.LogicalOrExpression;
import Nodes.expression.UnaryExpression;
import Nodes.jasmine.CodeGenerator;
import Nodes.statement.RoutineCallStatement;
import main.antlrTree.MyLangParser;
import main.TreeBuilder;
import org.antlr.v4.runtime.tree.ParseTree;

public abstract class Primary<T> {
    public Expression expression;
    public ModifiablePrimary modifiablePrimary;
    public RoutineCallStatement routineCall;
    public T value;
    public Type type;

    public Type getType(CodeGenerator generator) {
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
        if (this instanceof RoutineCallPrimary) {
            // TODO: get type from code generator
            final Type type = generator.getRoutineInfo(((RoutineCallPrimary) this).getIdentifier()).getReturnType();
            return type;
        }

//        Primary prim = this;
//        if (this instanceof LogicalOrExpression) {
//            if (((LogicalOrExpression)this).)
//        }

        System.out.println("Cannot determine type!");
        System.exit(1);

        return null;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    boolean hasChildren() {
        return expression!= null || modifiablePrimary != null || routineCall != null;
    }

    public static Primary parse(ParseTree tree, MyLangParser parser) {
        ParseTree child = tree.getChild(0);
        if (child.getChildCount() == 0 && !child.getText().equals("(") && !(tree instanceof MyLangParser.RoutineCallContext)) {
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
            return switch (nextStep) {
                case "routineCall" -> RoutineCallPrimary.parse(child, parser);
                case "modifiablePrimary" -> ModifiablePrimary.parse(child, parser);
                case "expression" -> Expression.parse(child, parser);
                default -> null;
            };

        }
    }

    public abstract String toString(String indent);


}
