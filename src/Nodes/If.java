package Nodes;

public class If extends Statement {
    public final String condition;
    public final Block block;

    public String getCondition() {
        return condition;
    }

    public Block getBody() {
        return block;
    }

    public If(String condition, Block block) {
        this.condition = condition;
        this.block = block;
    }

    @Override
    public String toString() {
        return "If{" +
                "condition='" + condition + '\'' +
                ", block=" + block +
                '}';
    }
}
