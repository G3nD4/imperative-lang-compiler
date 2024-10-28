package Nodes.primary;

public class BooleanLiteral extends Literal<Boolean> implements Primary {
    public Boolean value;

    public BooleanLiteral(Boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BooleanLiteral{" +
                "value=" + value +
                '}';
    }
}
