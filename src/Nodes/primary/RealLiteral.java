package Nodes.primary;

import Nodes.Enums.Type;
import main.IndentManager;
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
        IndentManager.print("RealLiteran: " + value.toString());

        return "";
    }

    public static RealLiteral parse(ParseTree tree, MyLangParser parser) {
        return new RealLiteral(Double.parseDouble(tree.getText()));
    }
}
