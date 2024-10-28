package Nodes.expression;

import Lexical_analyzer.TokenType;
import Nodes.primary.Primary;

public class UnaryExpression {
    public TokenType operation;
    public TokenType returnType;
    public Primary primary;

    public UnaryExpression(TokenType operation, Primary primary, TokenType returnType) {
        this.operation = operation;
        this.primary = primary;
        this.returnType = returnType;
    }

    @Override
    public String toString() {
        return "UnaryExpression{" +
                "operation=" + operation +
                ", returnType=" + returnType +
                ", primary=" + primary +
                '}';
    }

    public TokenType getReturnType() {
        return returnType;
    }

    public void setReturnType(TokenType returnType) {
        this.returnType = returnType;
    }
}
