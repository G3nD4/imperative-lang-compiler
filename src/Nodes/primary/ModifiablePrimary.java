package Nodes.primary;

import Nodes.Type;
import Nodes.jasmine.CodeGenerator;
import Nodes.jasmine.VariableInfo;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import static Nodes.Type.INTEGER;
import static Nodes.Type.REAL;

public class ModifiablePrimary extends Primary {

    public ModifiablePrimary(String identifier) {
        this.identifier = identifier;
    }

    public String identifier;

    public String toString(String indent) {
        IndentManager.print("Modifiable Primary identifier: " + identifier);
        return "";
    }

    public static ModifiablePrimary parse(ParseTree tree, MyLangParser parser) {
        return new ModifiablePrimary(tree.getChild(0).getText());
    }

    public String getLoadCode(CodeGenerator generator) {
        final VariableInfo varInfo = generator.getVariable(identifier);
        final Type varType = varInfo.getType();
        final int varIndex = varInfo.getIndex();

        StringBuilder code = new StringBuilder();
        switch (varType) {
            case INTEGER, BOOLEAN -> code.append("iload_").append(varIndex).append("\n");
            case REAL -> code.append("fload_").append(varIndex).append("\n");
            default -> throw new RuntimeException("Unsupported type for variable: " + identifier);
        }
        return code.toString();
    }

    public String getStoreCode(CodeGenerator generator, int index) {
        final VariableInfo varInfo = generator.getVariable(identifier);
        final Type varType = varInfo.getType();

        StringBuilder code = new StringBuilder();
        switch (varType) {
            case INTEGER, BOOLEAN -> code.append("istore_").append(index).append("\n");
            case REAL -> code.append("fstore_").append(index).append("\n");
            default -> throw new RuntimeException("Unsupported type for variable: " + identifier);
        }
        return code.toString();
    }
}
