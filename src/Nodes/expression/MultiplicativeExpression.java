package Nodes.expression;

import Nodes.Operation;
import main.MyLangParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;


public class MultiplicativeExpression extends Expression {
    public ArrayList<UnaryExpression> operands;
    public ArrayList<Operation> operations;

    public MultiplicativeExpression(ArrayList<UnaryExpression> operands, ArrayList<Operation> operations) {
        this.operands = operands;
        this.operations = operations;
    }

    public static MultiplicativeExpression parse(ParseTree tree, MyLangParser parser) {
        System.out.println(parser.getRuleNames()[((ParserRuleContext) tree).getRuleIndex()]);
        ArrayList<UnaryExpression> operands = new ArrayList<>();
        ArrayList<Operation> operations = new ArrayList<>();
        for (int childCounter = 0; childCounter < tree.getChildCount(); childCounter++) {
            if (childCounter % 2 == 0) {
                // So it is UnaryExpression
                operands.add(UnaryExpression.parse(tree.getChild(childCounter), parser));
            } else {
               // operation
                Operation operation = switch (String.valueOf(tree.getChild(childCounter))) {
                    case "*" -> Operation.MULTIPLY;
                    case "/" -> Operation.DIVIDE;
                    case "%" -> Operation.MODULO;
                    default ->
                            throw new IllegalStateException("Unexpected value: " + tree.getChild(childCounter));
                };
                operations.add(operation);
            }

        }
        return new MultiplicativeExpression(operands, operations);
    }
}
