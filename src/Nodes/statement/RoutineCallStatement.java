package Nodes.statement;

import Nodes.Parameter;

import java.util.List;

public class RoutineCallStatement extends Statement {
    private final String identifier;
    private final List<Parameter> parameters;

    @Override
    public String toString(String indent) {
        return '\n' + indent + "RoutineCall:" + " \n" +
                indent + "           |" + '\n' +
                indent + "--- identifier=" + identifier + '\n' +
                indent + "--- parameters=" + printParameters(indent + "               ") +
                '}';
    }

    public String printParameters(String indent) {
        StringBuilder result = new StringBuilder("|\n");
        for (Parameter param : parameters) {
            result.append(indent).append("---").append(param.toString(indent + "               ")).append("\n");
        }
        return result.toString();
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public RoutineCallStatement(String identifier, List<Parameter> parameters) {
        this.identifier = identifier;
        this.parameters = parameters;
    }
}
