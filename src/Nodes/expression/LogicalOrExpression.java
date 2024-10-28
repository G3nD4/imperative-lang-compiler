package Nodes.expression;

public class LogicalOrExpression {
    public LogicalAndExpression leftOperand;
    public LogicalAndExpression rightOperand;

    public LogicalOrExpression(LogicalAndExpression leftOperand, LogicalAndExpression rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public String toString() {
        return "LogicalOrExpression{" +
                "leftOperand=" + leftOperand +
                ", rightOperand=" + rightOperand +
                '}';
    }
}
