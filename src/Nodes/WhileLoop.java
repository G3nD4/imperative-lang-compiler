package Nodes;

public class WhileLoop extends Statement {
    private final String condition;
    private final Block body;

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

    public Block getBody() {
        return body;
    }

    public WhileLoop(String condition, Block body) {
        this.condition = condition;
        this.body = body;
    }
}
