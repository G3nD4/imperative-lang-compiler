package Nodes;

import Nodes.statement.Statement;
import main.IndentManager;

public class TypeDeclaration extends Declaration {
    private final String identifier;
    private final Type type;

    public TypeDeclaration(String identifier, Type type) {
        this.identifier = identifier;
        this.type = type;
    }

    public String getIdentifiers() {
        return identifier;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString(String indent) {
        IndentManager.print("User Type Declaration:");
        IndentManager.goDown();
        IndentManager.print(identifier);
        IndentManager.print(type.toString().toLowerCase());
        IndentManager.goUp();
        return "";
    }
}
