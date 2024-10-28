package Nodes;

public class Assignment extends Statement {
    private String variable;
    private String expression;

    public Assignment(String variable, String expression) {
        this.variable = variable;
        this.expression = expression;
    }

    public String getVariable() {
        return variable;
    }

    public String getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "variable='" + variable + '\'' +
                ", expression='" + expression + '\'' +
                '}';
    }
}
