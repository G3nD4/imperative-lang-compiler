package Nodes.statement;

public class Return extends Statement {
    public final String expression;

    public String getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "Return{" +
                "expression='" + expression + '\'' +
                '}';
    }

    public Return(String expression) {
        this.expression = expression;
    }
}
