package Nodes.statement;

import Nodes.Body;

public class WhileLoop extends Statement {
    private final String condition;
    private final Body body;

    public String getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return "WhileLoop{" +
                "condition='" + condition + '\'' +
                ", body=" + body +
                '}';
    }

    public Body getBody() {
        return body;
    }

    public WhileLoop(String condition, Body body) {
        this.condition = condition;
        this.body = body;
    }
}
