package Nodes.statement;

import Nodes.Body;
import Nodes.expression.Expression;

public class IfStatement extends Statement {
    public final Expression condition;
    public final Body ifBody;
    public final Body elseBody;

    public IfStatement(Expression condition, Body ifBody, Body elseBody) {
        this.condition = condition;
        this.ifBody = ifBody;
        this.elseBody = elseBody;
    }

    @Override
    public String toString() {
        return "IfStatement{" +
                "condition=" + condition +
                ", ifBlock=" + ifBody +
                ", elseBlock=" + elseBody +
                '}';
    }
}
