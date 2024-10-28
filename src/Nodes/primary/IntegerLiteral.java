package Nodes.primary;

public class IntegerLiteral extends Literal<Integer> implements Primary {
    public Integer value;

    public IntegerLiteral(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "IntegerLiteral{" +
                "value=" + value +
                '}';
    }
}
