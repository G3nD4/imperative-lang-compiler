package Nodes;

import java.util.List;

public class VariableDeclaration extends Statement {
    private String identifier;
    private String type;
    private String initialValue;

    public VariableDeclaration(String identifier, String type, String initialValue) {
        this.identifier = identifier;
        this.type = type;
        this.initialValue = initialValue;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getType() {
        return type;
    }

    public String getInitialValue() {
        return initialValue;
    }

    @Override
    public String toString() {
        return "VariableDeclaration{" +
                "identifier=" + identifier +
                ", type='" + type + '\'' +
                ", initialValues=" + initialValue +
                '}';
    }
}
