package Nodes;

import Nodes.expression.Expression;

public class Assignment extends Statement {
    public String name;
    public Expression expression;

    public Assignment(String name, Expression expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "variable='" + name + '\'' +
                ", expression='" + expression.toString() + '\'' +
                '}';
    }
}
