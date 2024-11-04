package Nodes.expression;

import Nodes.Type;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class LogicalAndExpression extends Expression {
    public ArrayList<EqualityExpression> operands;

    public LogicalAndExpression(ArrayList<EqualityExpression> operands, Type type) {
        this.operands = operands;
        super.type = type;
    }

    public static LogicalAndExpression parse(ParseTree tree, MyLangParser parser) {
        ArrayList<EqualityExpression> operands = new ArrayList<>();

        for (int childCounter = 0; childCounter < tree.getChildCount(); childCounter = childCounter + 2) {
            // Skip the operation literal (AND)
            operands.add(EqualityExpression.parse(tree.getChild(childCounter), parser));
        }

        // We assume that AND is applied only to boolean, boolean
        // boolean AND boolean
        for (EqualityExpression operand : operands) {
            if (operand.type == Type.REAL || operand.type == Type.INTEGER) {
                System.out.println("Operator 'and' cannot be applied to " + operand.type.toString().toLowerCase());
                System.exit(1);
            }
        }

        return new LogicalAndExpression(operands, Type.BOOLEAN);
    }
}
