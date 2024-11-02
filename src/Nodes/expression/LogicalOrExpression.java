package Nodes.expression;

import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class LogicalOrExpression extends Expression {
    public ArrayList<LogicalAndExpression> operands;

    public LogicalOrExpression(ArrayList<LogicalAndExpression> operands) {
        this.operands = operands;
    }

    public static LogicalOrExpression parse(ParseTree tree, MyLangParser parser) {
        ArrayList<LogicalAndExpression> operands = new ArrayList<>();

        for (int childCounter = 0; childCounter < tree.getChildCount(); childCounter = childCounter + 2) {
            // Skip the operation literal (OR)
            operands.add(LogicalAndExpression.parse(tree.getChild(childCounter), parser));
        }

        return new LogicalOrExpression(operands);
    }
}
