package Nodes.expression;

import Lexical_analyzer.TokenType;
import Nodes.Operation;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class AdditiveExpression extends Expression {
    public ArrayList<MultiplicativeExpression> operands;
    public TokenType operation;

    public AdditiveExpression(ArrayList<MultiplicativeExpression> operands, TokenType operation) {
        this.operands = operands;
        this.operation = operation;
    }

    public static AdditiveExpression parse(ParseTree tree, MyLangParser parser) {
        ArrayList<MultiplicativeExpression> operands = new ArrayList<>();
        TokenType operation = null;
        for (int childCounter = 0; childCounter < tree.getChildCount(); childCounter++) {
            if (childCounter % 2 == 0) {
                // So it is MultiplicativeExpression
                operands.add(MultiplicativeExpression.parse(tree.getChild(childCounter), parser));
            } else {
                // operation
                operation = switch (String.valueOf(tree.getChild(childCounter))) {
                    case "+" -> TokenType.PLUS;
                    case "-" -> TokenType.MINUS;
                    default ->
                            throw new IllegalStateException("Unexpected value: " + tree.getChild(childCounter));
                };
            }

        }
        return new AdditiveExpression(operands, operation);
    }

}
