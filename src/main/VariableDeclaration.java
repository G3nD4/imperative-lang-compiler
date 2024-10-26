package main;

import java.util.List;

public class VariableDeclaration {
    private List<String> identifiers;
    private String type;
    private List<String> initialValues;

    public VariableDeclaration(List<String> identifiers, String type, List<String> initialValues) {
        this.identifiers = identifiers;
        this.type = type;
        this.initialValues = initialValues;
    }

    public List<String> getIdentifiers() {
        return identifiers;
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
                "identifiers=" + identifiers +
                ", type='" + type + '\'' +
                ", initialValues=" + initialValues +
                '}';
    }
}
