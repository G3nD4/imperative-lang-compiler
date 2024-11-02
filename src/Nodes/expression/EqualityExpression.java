package Nodes.expression;

import Lexical_analyzer.TokenType;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class EqualityExpression extends Expression {
    public ArrayList<RelationalExpression> operands;
    public ArrayList<TokenType> operations;

    public EqualityExpression(ArrayList<RelationalExpression> operands, ArrayList<TokenType> operations) {
        this.operands = operands;
        this.operations = operations;
    }

    @Override
    public String toString() {
        return "EqualityExpression{" +
                "operands=" + operands +
                ", operations=" + operations +
                '}';
    }

    public static EqualityExpression parse(ParseTree tree, MyLangParser parser) {
        ArrayList<RelationalExpression> operands = new ArrayList<>();
        ArrayList<TokenType> operations = new ArrayList<>();
        for (int childCounter = 0; childCounter < tree.getChildCount(); childCounter++) {
            if (childCounter % 2 == 0) {
                // So it is RelationalExpression
                operands.add(RelationalExpression.parse(tree.getChild(childCounter), parser));
            } else {
                // operation
                TokenType operation = switch (String.valueOf(tree.getChild(childCounter))) {
                    case "=" -> TokenType.EQUAL;
                    case "!=" -> TokenType.NOT_EQUAL;
                    default -> throw new IllegalStateException("Unexpected value: " + tree.getChild(childCounter));
                };
                operations.add(operation);
            }

        }
        return new EqualityExpression(operands, operations);
    }
}
