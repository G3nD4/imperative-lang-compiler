package Nodes.expression;

import Nodes.JasminConvertable;
import Nodes.Type;
import Nodes.jasmine.CodeGenerator;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class LogicalOrExpression extends Expression implements JasminConvertable {
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

    @Override
    public String toString(String indent) {
        IndentManager.print("Logical OR Expression:");
        IndentManager.goDown();
        for (int i = 0; i < operands.size(); ++i) {
            IndentManager.print(operands.get(i).toString(""));
            if (i != 0 && i < operands.size() - 1) {
                IndentManager.print("OR");
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
            generator.writeToProgram("ior");
        }
    }
}
