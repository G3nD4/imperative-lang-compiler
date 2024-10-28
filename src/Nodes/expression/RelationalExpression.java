package Nodes.expression;

import Lexical_analyzer.TokenType;

public class RelationalExpression extends Expression {
    public AdditiveExpression leftOperand;
    public AdditiveExpression rightOperand;
    public TokenType operation;

    public RelationalExpression(AdditiveExpression leftOperand, AdditiveExpression rightOperand, TokenType operation) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "RelationalExpression{" +
                "leftOperand=" + leftOperand +
                ", rightOperand=" + rightOperand +
                ", operation=" + operation +
                '}';
    }
}
