package Nodes.expression;

import Lexical_analyzer.TokenType;

public class AdditiveExpression extends Expression {
    public MultiplicativeExpression leftOperand;
    public MultiplicativeExpression rightOperand;
    public TokenType operation;
    public TokenType returnType;

    public AdditiveExpression(MultiplicativeExpression leftOperand, MultiplicativeExpression rightOperand, TokenType operation, TokenType returnType) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.operation = operation;
        this.returnType = returnType;
    }

    @Override
    public String toString() {
        return "AdditiveExpression{" +
                "leftOperand=" + leftOperand +
                ", rightOperand=" + rightOperand +
                ", operation=" + operation +
                ", returnType=" + returnType +
                '}';
    }

}
