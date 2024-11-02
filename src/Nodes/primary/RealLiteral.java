package Nodes.primary;

import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

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

    public static Primary parse(ParseTree tree, MyLangParser parser) {
        return null;
    }
}
