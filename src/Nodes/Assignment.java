package Nodes;

import Nodes.expression.Expression;
import Nodes.statement.Statement;
import main.MyLangParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

public class Assignment extends Statement {
    public String name;
    public Expression expression;

    public Assignment(String name, Expression expression) {
        this.name = name;
        this.expression = expression;
    }

    public static Assignment parse(ParseTree tree, MyLangParser parser) {
        return new Assignment(tree.getChild(0).getText(), Expression.parse(tree.getChild(2), parser));
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "variable='" + name + '\'' +
                ", expression='" + expression.toString() + '\'' +
                '}';
    }
}
