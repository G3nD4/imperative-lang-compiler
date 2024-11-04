package Nodes.primary;

import Nodes.Type;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class RealLiteral extends Primary<Double> implements Literal<Double> {
    public Double value;

    public RealLiteral(Double value) {
        this.value = value;
        super.type = Type.REAL;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public String toString(String indent) {
        return indent + "---RealLit val = " + value + "\n";
    }

    public static RealLiteral parse(ParseTree tree, MyLangParser parser) {
        return new RealLiteral(Double.parseDouble(tree.getText()));
    }
}
