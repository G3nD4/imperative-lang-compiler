package Nodes;

import Nodes.expression.Expression;

public class IfStatement extends Statement {
    public final Expression condition;
    public final Block block;

    public Expression getCondition() {
        return condition;
    }

    public Block getBody() {
        return block;
    }

    public IfStatement(Expression condition, Block block) {
        this.condition = condition;
        this.block = block;
    }

    @Override
    public String toString() {
        return "If{" +
                "condition='" + condition.toString() + '\'' +
                ", block=" + block +
                '}';
    }
}
