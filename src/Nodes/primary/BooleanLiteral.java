package Nodes.primary;

import Nodes.Type;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class BooleanLiteral extends Primary<Boolean> implements Literal<Boolean> {
    private final Boolean value;

    public BooleanLiteral(Boolean value) {
        this.value = value;
        super.type = Type.BOOLEAN;
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
        return new BooleanLiteral(tree.getText().equals("true") ? Boolean.TRUE : Boolean.FALSE);
    }
}
