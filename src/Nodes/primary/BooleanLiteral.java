package Nodes.primary;

import Nodes.Type;
import Nodes.jasmine.CodeGenerator;
import Nodes.jasmine.JasminLoadable;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class BooleanLiteral extends Primary<Boolean> implements Literal<Boolean>, JasminLoadable {
    private final Boolean value;

    public BooleanLiteral(Boolean value) {
        this.value = value;
        super.type = Type.BOOLEAN;
    }

    public int jasmineConst() {
        if (value) {
            return 1;
        }
        return 0;
    }

    public String toString(String indent) {
        IndentManager.print("BooleanLiteran: " + value.toString());

        return "";
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    public static BooleanLiteral parse(ParseTree tree, MyLangParser parser) {
        return new BooleanLiteral(tree.getText().equals("true") ? Boolean.TRUE : Boolean.FALSE);
    }

    @Override
    public String getLoadCode(CodeGenerator generator) {
        return value ? "ldc 1" : "ldc 0";
    }
}
