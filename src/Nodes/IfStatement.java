package Nodes;

import Nodes.expression.Expression;

public class IfStatement extends Statement {
    public final Expression condition;
    public final Block ifBlock;
    public final Block elseBlock;

    public IfStatement(Expression condition, Block ifBlock, Block elseBlock) {
        this.condition = condition;
        this.ifBlock = ifBlock;
        this.elseBlock = elseBlock;
    }

    @Override
    public String toString() {
        return "IfStatement{" +
                "condition=" + condition +
                ", ifBlock=" + ifBlock +
                ", elseBlock=" + elseBlock +
                '}';
    }
}
