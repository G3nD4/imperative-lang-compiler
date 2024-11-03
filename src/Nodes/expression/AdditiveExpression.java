package Nodes.expression;

import Lexical_analyzer.TokenType;
import Nodes.Operation;
import Nodes.Sign;
import Nodes.Type;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class AdditiveExpression extends Expression {
    public ArrayList<MultiplicativeExpression> operands;
    public ArrayList<Sign> operations;

    public AdditiveExpression(ArrayList<MultiplicativeExpression> operands, ArrayList<Sign> operations, Type type) {
        this.operands = operands;
        this.operations = operations;
        super.type = type;
    }

    public static AdditiveExpression parse(ParseTree tree, MyLangParser parser) {
        ArrayList<MultiplicativeExpression> operands = new ArrayList<>();
        ArrayList<Sign> operations = new ArrayList<>();
        for (int childCounter = 0; childCounter < tree.getChildCount(); childCounter++) {
            if (childCounter % 2 == 0) {
                // So it is MultiplicativeExpression
                operands.add(MultiplicativeExpression.parse(tree.getChild(childCounter), parser));
            } else {
                // operation
                operations.add(Sign.fromString(String.valueOf(tree.getChild(childCounter))));
            }

        }

        Type type = Type.INTEGER;
        if (operands.size() == 1) {
            // FIXME: type could be null (theoretically)
            return new AdditiveExpression(operands, operations, operands.getFirst().type == null ? operands.getFirst().returnType : operands.getFirst().type);
        }
        for (int i = 0; i < operands.size(); ++i) {
            if (operands.get(i).type == Type.BOOLEAN || operands.get(i).returnType == Type.BOOLEAN) {
                System.out.println("Unsupported operation " + operations.get(i == 0 ? 0 : i - 1).toString() + " for BOOLEAN");
                System.exit(1);
            }
            if (operands.get(i).type == Type.REAL || operands.get(i).returnType == Type.REAL) {
                type = Type.REAL;
                break;
            }
        }

        return new AdditiveExpression(operands, operations, type);
    }

}
