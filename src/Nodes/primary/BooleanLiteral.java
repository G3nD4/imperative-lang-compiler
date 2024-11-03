package Nodes.primary;

import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class BooleanLiteral extends Primary<Boolean> implements Literal<Boolean> {
    private final Boolean value;

    public BooleanLiteral(Boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BooleanLiteral{" +
                "value=" + value +
                '}';
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    public static BooleanLiteral parse(ParseTree tree, MyLangParser parser) {
        return null;
    }
}
