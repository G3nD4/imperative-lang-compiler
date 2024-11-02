package Nodes.primary;

import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

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

    public static Primary parse(ParseTree tree, MyLangParser parser) {
        return null;
    }
}
