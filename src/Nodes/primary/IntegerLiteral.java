package Nodes.primary;

import Nodes.Type;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class IntegerLiteral extends Primary<Integer> implements Literal<Integer> {
    private final Integer value;

    public IntegerLiteral(int value) {
        this.value = value;
        super.type = Type.INTEGER;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "IntegerLiteral{" +
                "value=" + value +
                '}';
    }

    public static IntegerLiteral parse(ParseTree tree, MyLangParser parser) {
        int value = Integer.parseInt(String.valueOf(tree));
        return new IntegerLiteral(value);
    }
}
