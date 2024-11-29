package Nodes.statement.Declarations;

import Nodes.Enums.Type;
import Nodes.jasmine.CodeGenerator;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class TypeDeclaration extends Declaration {
    private final String identifier;
    private final Type type;

    public TypeDeclaration(String identifier, Type type) {
        this.identifier = identifier;
        this.type = type;
    }

    public static TypeDeclaration parse(ParseTree tree, MyLangParser parser) {
        final String identifier = tree.getChild(1).getText();
        final Type type = Type.fromString(tree.getChild(3).getText());
        return new TypeDeclaration(identifier, type);
    }

    public String getIdentifier() {
        return identifier;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString(String indent) {
        IndentManager.print("User Type Declaration:");
        IndentManager.goDown();
        IndentManager.print("identifier: " + identifier);
        IndentManager.print("type: " + type.toString().toLowerCase());
        IndentManager.goUp();
        return "";
    }

    @Override
    public void generateCode(CodeGenerator generator) {

    }
}
