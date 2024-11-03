package Nodes.expression;

import Nodes.Type;
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

        // We assume that OR is applied only to boolean, boolean
        // boolean OR boolean
        for (LogicalAndExpression operand : operands) {
            if (operand.type == Type.REAL || operand.type == Type.INTEGER) {
                System.out.println("Operator 'or' cannot be applied to " + operand.type.toString().toLowerCase());
                System.exit(1);
            }
        }

        return new LogicalOrExpression(operands);
    }
}
