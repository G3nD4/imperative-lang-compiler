package Nodes;

public class TypeDeclaration extends Statement {
    private String identifier;
    private String type;

    public TypeDeclaration(String identifier, String type) {
        this.identifier = identifier;
        this.type = type;
    }

    public String getIdentifiers() {
        return identifier;
    }

    public String getType() {
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
