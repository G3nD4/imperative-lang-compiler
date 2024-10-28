package Nodes.expression;

import Lexical_analyzer.TokenType;

public class MultiplicativeExpression extends Expression {
    public UnaryExpression leftOperand;
    public UnaryExpression rightOperand;
    public TokenType operation;
    public TokenType returnType;

    public MultiplicativeExpression(UnaryExpression leftOperand, UnaryExpression rightOperand, TokenType operation, TokenType returnType) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.operation = operation;
        this.returnType = returnType;
    }

    @Override
    public String toString() {
        return "MultiplicativeExpression{" +
                "leftOperand=" + leftOperand +
                ", rightOperand=" + rightOperand +
                ", operation=" + operation +
                ", returnType=" + returnType +
                '}';
    }
}
