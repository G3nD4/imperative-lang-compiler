package Nodes.primary;

import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class RealLiteral extends Primary<Double> implements Literal<Double> {
    public Double value;

    public RealLiteral(Double value) {
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "RealLiteral{" +
                "value=" + value +
                '}';
    }

    public static RealLiteral parse(ParseTree tree, MyLangParser parser) {
        return null;
    }
}
