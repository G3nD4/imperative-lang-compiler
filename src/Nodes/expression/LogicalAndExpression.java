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

    @Override
    public String toString(String indent) {
        StringBuilder result = new StringBuilder();
        String applyedIndent = indent;
        if (operands.size() < 2) {
            applyedIndent = "";
        }
        for (int i = 0; i < operands.size(); ++i) {
            result.append(applyedIndent).append(operands.get(i).toString(indent)).append("\n");
            if (i < operands.size() - 1) {
                result.append(applyedIndent).append("AND\n");
            }
        }
        return result.toString();
    }
}
