package Nodes;

import Nodes.statement.Statement;

public class TypeDeclaration extends Declaration {
    private String identifier;
    private Type type;

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
    public String toString() {
        return "VariableDeclaration{" +
                "identifier=" + identifier +
                ", type='" + type + '\'' +
                '}';
    }
}
