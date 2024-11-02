package Nodes.primary;

import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

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

    public static IntegerLiteral parse(ParseTree tree, MyLangParser parser) {
        int value = Integer.parseInt(String.valueOf(tree));
        return new IntegerLiteral(value);
    }
}
