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
    public String toString(String indent) {
        return indent + "TypeDeclaration:" + '\n' +
                indent + "---identifier: " + identifier +
                indent + "---type: " + type.toString() + '\n';
    }
}
