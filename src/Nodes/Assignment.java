package Nodes;

import Nodes.expression.Expression;
import Nodes.primary.ModifiablePrimary;
import Nodes.statement.Statement;
import main.MyLangParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

public class Assignment extends Statement {
    public ModifiablePrimary assignee;
    public Expression expression;

    public Assignment(ModifiablePrimary assignee, Expression expression) {
        this.assignee = assignee;
        this.expression = expression;
    }

    public static Assignment parse(ParseTree tree, MyLangParser parser) {
        final ModifiablePrimary assignee = ModifiablePrimary.parse(tree.getChild(0), parser);
        final Expression expression = Expression.parse(tree.getChild(2), parser);
        return new Assignment(assignee, expression);
    }

    @Override
    public String toString(String indent) {
        return "Assignment: |" + "\n" +
                indent + "--- variable=" + assignee.toString(indent + "             ") + '\n' +
                indent + "--- expression=" + expression.toString(indent + "               ") + '\n' +
                '\n';
    }
}
