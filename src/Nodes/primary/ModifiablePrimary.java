package Nodes.primary;

import Nodes.Enums.Type;
import Nodes.Program;
import Nodes.jasmine.CodeGenerator;
import Nodes.jasmine.VariableInfo;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class ModifiablePrimary extends Primary {

    public ModifiablePrimary(String identifier) {
        this.identifier = identifier;
        super.type = Program.getVariableType(identifier);
    }

    public ModifiablePrimary(String identifier, CodeGenerator generator) {
        this.identifier = identifier;
        super.type = Program.getVariableTypeWithScope(identifier, generator);
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
            case INTEGER, BOOLEAN -> code.append("iload ").append(varIndex).append("\n");
            case REAL -> code.append("fload ").append(varIndex).append("\n");
            default -> throw new RuntimeException("Unsupported type for variable: " + identifier);
        }
        return code.toString();
    }

    public String getReturnCode(CodeGenerator generator) {
        final VariableInfo varInfo = generator.getVariable(identifier);
        final Type varType = varInfo.getType();
        final int varIndex = varInfo.getIndex();

        StringBuilder code = new StringBuilder();
        switch (varType) {
            case INTEGER, BOOLEAN -> code.append("ireturn_").append(varIndex).append("\n");
            case REAL -> code.append("freturn_").append(varIndex).append("\n");
            default -> throw new RuntimeException("Unsupported type for variable: " + identifier);
        }
        return code.toString();
    }

    public String getStoreCode(CodeGenerator generator, int index) {
        final VariableInfo varInfo = generator.getVariable(identifier);
        final Type varType = varInfo.getType();

        StringBuilder code = new StringBuilder();
        switch (varType) {
            case INTEGER, BOOLEAN -> code.append("istore ").append(index).append("\n");
            case REAL -> code.append("fstore ").append(index).append("\n");
            default -> throw new RuntimeException("Unsupported type for variable: " + identifier);
        }
        return code.toString();
    }

    // This method is used ONLY after parsing, in code generation phase
    public Type getType(CodeGenerator generator) {
        final VariableInfo varInfo = generator.getVariable(identifier);
        final Type varType = varInfo.getType();

        switch (varType) {
            case INTEGER -> {
                return Type.INTEGER;
            }
            case BOOLEAN -> {
                return Type.BOOLEAN;
            }
            case REAL -> {
                return Type.REAL;
            }
            // Maybe need to handle IDENTIFIER type
            default -> throw new RuntimeException("Unsupported type for variable: " + identifier);
        }
    }
}
