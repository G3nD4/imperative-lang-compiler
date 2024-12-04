package Nodes.expression;

import Nodes.Interfaces.JasminConvertable;
import Nodes.Enums.Type;
import Nodes.jasmine.CodeGenerator;
import main.IndentManager;
import main.antlrTree.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class LogicalAndExpression extends Expression implements JasminConvertable {
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
        IndentManager.print("Logical AND Expression:");
        IndentManager.goDown();
        for (int i = 0; i < operands.size(); ++i) {
            IndentManager.print(operands.get(i).toString(""));
            if (i != 0 && i < operands.size() - 1) {
                IndentManager.print("AND");
            }
        }
        IndentManager.goUp();

        return "";
    }

    @Override
    public void generateCode(CodeGenerator generator) {
        // If this expression is needed only for priority handling, delegate generateCode to its child.
        if (operands.size() == 1) {
            operands.getFirst().generateCode(generator);
            return;
        }

        // Generate code for the first operand
        operands.getFirst().generateCode(generator);

        // For each subsequent operand
        for (int i = 1; i < operands.size(); i++) {
            // Generate code for current operand
            operands.get(i).generateCode(generator);
            // Perform AND operation
            generator.writeToProgram("iand");
        }
    }
}
