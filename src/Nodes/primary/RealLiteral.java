package Nodes.primary;

public class RealLiteral extends Literal<Double> implements Primary {
    public Double value;

    public RealLiteral(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "RealLiteral{" +
                "value=" + value +
                '}';
    }
}
