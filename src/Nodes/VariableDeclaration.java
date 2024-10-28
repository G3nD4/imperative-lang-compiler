package Nodes;

import java.util.List;

public class VariableDeclaration extends Statement {
    private String identifier;
    private String type;
    private List<String> initialValues;

    public VariableDeclaration(String identifier, String type, List<String> initialValues) {
        this.identifier = identifier;
        this.type = type;
        this.initialValues = initialValues;
    }

    public String getIdentifiers() {
        return identifier;
    }

    public String getType() {
        return type;
    }

    public List<String> getInitialValues() {
        return initialValues;
    }

    @Override
    public String toString() {
        return "VariableDeclaration{" +
                "identifier=" + identifier +
                ", type='" + type + '\'' +
                ", initialValues=" + initialValues +
                '}';
    }
}
