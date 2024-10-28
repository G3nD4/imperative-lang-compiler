package Nodes;

import java.util.List;

public class RoutineCall extends Statement {
    private final String identifier;
    private final List<Parameter> parameters;

    @Override
    public String toString() {
        return "RoutineCall{" +
                "identifier='" + identifier + '\'' +
                ", parameters=" + parameters +
                '}';
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public RoutineCall(String identifier, List<Parameter> parameters) {
        this.identifier = identifier;
        this.parameters = parameters;
    }
}
