package Nodes.expression;

public class SimpleExpression extends Expression {
    public LogicalOrExpression operand;

    public SimpleExpression(LogicalOrExpression operand) {
        this.operand = operand;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "operand=" + operand +
                '}';
    }
}