package Nodes;

import Nodes.Enums.Type;
import Nodes.expression.Expression;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class RoutineCallParameter {
    public Expression expression;

    public RoutineCallParameter(Expression expression) {
        this.expression = expression;
    }

    public static RoutineCallParameter parse(ParseTree tree, MyLangParser parser) {
        return new RoutineCallParameter(Expression.parse(tree, parser));
    }

    public String toString(String indent) {
        IndentManager.print("RoutineCallParameter:");
        IndentManager.print(expression.toString(""));
        return "";
    }

    public Type getType() {
        return expression.type;
    }
}
