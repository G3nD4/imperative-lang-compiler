package Nodes.primary;

import Nodes.IRoutine;
import Nodes.Parameter;

import java.util.List;

public class RoutineCallPrimary extends Primary implements IRoutine  {
    private final String identifier;
    private final List<Parameter> parameters;;

    public RoutineCallPrimary(String identifier, List<Parameter> parameters) {
        this.identifier = identifier;
        this.parameters = parameters;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "RoutineCall{" +
                "identifier='" + identifier + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
