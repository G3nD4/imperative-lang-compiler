package Nodes.expression;

import Lexical_analyzer.TokenType;

public class EqualityExpression extends Expression {
    public RelationalExpression leftOperand;
    public RelationalExpression rightOperand;
    public TokenType operation;

    public EqualityExpression(RelationalExpression leftOperand, RelationalExpression rightOperand, TokenType operation) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "EqualityExpression{" +
                "leftOperand=" + leftOperand +
                ", rightOperand=" + rightOperand +
                ", operation=" + operation +
                '}';
    }
}
