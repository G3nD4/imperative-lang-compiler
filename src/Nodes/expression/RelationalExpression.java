package Nodes.expression;

import Lexical_analyzer.TokenType;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class RelationalExpression extends Expression {
    public ArrayList<AdditiveExpression> operands;
    public ArrayList<TokenType> operations;


    public RelationalExpression(ArrayList<AdditiveExpression> operands, ArrayList<TokenType> operations) {
        this.operands = operands;
        this.operations = operations;
    }

    @Override
    public String toString() {
        return "RelationalExpression{" +
                "operands=" + operands +
                ", operations=" + operations +
                '}';
    }

    public static RelationalExpression parse(ParseTree tree, MyLangParser parser) {
        ArrayList<AdditiveExpression> operands = new ArrayList<>();
        ArrayList<TokenType> operations = new ArrayList<>();
        for (int childCounter = 0; childCounter < tree.getChildCount(); childCounter++) {
            if (childCounter % 2 == 0) {
                // So it is AdditiveExpression
                operands.add(AdditiveExpression.parse(tree.getChild(childCounter), parser));
            } else {
                // operation
                TokenType operation = switch (String.valueOf(tree.getChild(childCounter))) {
                    case "<" -> TokenType.LESS_THAN;
                    case ">" -> TokenType.GREATER_THAN;
                    case "<=" -> TokenType.LESS_EQUAL;
                    case ">=" -> TokenType.GREATER_EQUAL;
                    default -> throw new IllegalStateException("Unexpected value: " + tree.getChild(childCounter));
                };
                operations.add(operation);
            }

        }
        return new RelationalExpression(operands, operations);
    }
}
