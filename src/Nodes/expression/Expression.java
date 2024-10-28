package Nodes.expression;

public abstract class Expression {
    public LogicalOrExpression operand;

    public Expression(LogicalOrExpression operand) {
        this.operand = operand;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "operand=" + operand +
                '}';
    }
}
