package Nodes.expression;

import Lexical_analyzer.TokenType;

public class LogicalAndExpression extends Expression {
    public EqualityExpression leftOperand;
    public EqualityExpression rightOperand;

    public LogicalAndExpression(EqualityExpression leftOperand, EqualityExpression rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public String toString() {
        return "LogicalAndExpression{" +
                "leftOperand=" + leftOperand +
                ", rightOperand=" + rightOperand +
                '}';
    }
}
