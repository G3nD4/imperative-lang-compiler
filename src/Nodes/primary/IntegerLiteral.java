package Nodes.primary;

import Nodes.Enums.Type;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class IntegerLiteral extends Primary<Integer> implements Literal<Integer> {
    private Integer value;

    public IntegerLiteral(int value) {
        this.value = value;
        super.type = Type.INTEGER;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue(Integer value) {
        this.value = value;
    }

    public String toString(String indent) {
        IndentManager.print("IntegerLiteral: " + value.toString());

        return "";
    }

    public static IntegerLiteral parse(ParseTree tree, MyLangParser parser) {
        int value = Integer.parseInt(String.valueOf(tree));
        return new IntegerLiteral(value);
    }
}
