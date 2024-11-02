package Nodes.expression;

import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class LogicalAndExpression extends Expression {
    public ArrayList<EqualityExpression> operands;

    public LogicalAndExpression(ArrayList<EqualityExpression> operands) {
        this.operands = operands;
    }

    public static LogicalAndExpression parse(ParseTree tree, MyLangParser parser) {
        ArrayList<EqualityExpression> operands = new ArrayList<>();

        for (int childCounter = 0; childCounter < tree.getChildCount(); childCounter = childCounter + 2) {
            // Skip the operation literal (AND)
            operands.add(EqualityExpression.parse(tree.getChild(childCounter), parser));
        }

        return new LogicalAndExpression(operands);
    }
}
