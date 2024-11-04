package Nodes.statement;

import Nodes.Body;
import Nodes.expression.Expression;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class IfStatement extends Statement {
    public final Expression condition;
    public final Body ifBody;
    public final ArrayList<Expression> ifElseConditions;
    public final ArrayList<Body> ifElseBodies;
    public final Body elseBody;

    public boolean hasElseBody() {
        return elseBody != null;
    }

    public static IfStatement parse(ParseTree tree, MyLangParser parser) {
        Expression condition = null;
        Body ifBody = null;
        Body elseBody = null;
        ArrayList<Expression> ifElseConditions = new ArrayList<>();
        ArrayList<Body> ifElseBodies = new ArrayList<>();


        int elseIfIndex = -100;
        for (int i = 0; i < tree.getChildCount(); ++i) {
            if (i == 1) {
                condition = Expression.parse(tree.getChild(1), parser);
            } else if (i == 4) {
                ifBody = Body.parse(tree.getChild(4), parser);
            } else if (tree.getChild(i).getText().equals("elseif")) {
                elseIfIndex = i;
            } else if (i == elseIfIndex + 1) {
                ifElseConditions.add(Expression.parse(tree.getChild(elseIfIndex + 1), parser));
            } else if (i == elseIfIndex + 4) {
                ifElseBodies.add(Body.parse(tree.getChild(elseIfIndex + 4), parser));
            } else if (tree.getChild(i).getText().equals("else")) {
                int elseBodyIndex = tree.getChildCount() - 3;
                elseBody = Body.parse(tree.getChild(elseBodyIndex), parser);
                break;
            }
        }

        return new IfStatement(condition, ifBody, ifElseConditions, ifElseBodies, elseBody);
    }

    public IfStatement(Expression condition, Body ifBody, ArrayList<Expression> ifElseConditions, ArrayList<Body> ifElseBodies, Body elseBody) {
        this.condition = condition;
        this.ifBody = ifBody;
        this.ifElseConditions = ifElseConditions == null ? new ArrayList<>() : ifElseConditions;
        this.ifElseBodies = ifElseBodies == null ? new ArrayList<>() : ifElseBodies;
        this.elseBody = elseBody;
    }

    @Override
    public String toString(String indent) {
        StringBuilder result = new StringBuilder("IfStatement:\n" +
                indent + "--- condition= \n" + condition.toString(indent) + "\n" +
                indent + "--- if body= " + ifBody.toString(indent + "             ") + "\n" +
                indent + "--- additional conditions: " + printAdditionalConditions(indent + "                           ") + "\n"
        );
        if (elseBody != null) {
            result.append(indent).append("--- else body= ").append(elseBody.toString(indent + "               ")).append("\n");
        }
        return result.toString();
    }

    String printAdditionalConditions(String indent) {
        StringBuilder result = new StringBuilder("\n");

        for (int i = 0; i < ifElseConditions.size(); ++i) {
            result.append(indent).append("--- condition: ").append(ifElseConditions.get(i).toString(""));
            result.append(indent).append("--- body: ").append(ifElseBodies.get(i).toString(indent + "          ")).append("\n");
            result.append(indent).append("\n");
        }

        return result.toString();
    }
}
