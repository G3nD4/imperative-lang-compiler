package Nodes;

import java.util.List;

public class RoutineDeclarationStatement extends Statement {
    private final String name;
    private final List<Parameter> parameters;
    private final String returnType;
    private final Block body;

    public RoutineDeclarationStatement(String name, List<Parameter> parameters, String returnType, Block body) {
        this.name = name;
        this.parameters = parameters;
        this.returnType = returnType;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public String getReturnType() {
        return returnType;
    }

    public Block getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Function{" +
                "name='" + name + '\'' +
                ", parameters=" + parameters +
                ", returnType='" + returnType + '\'' +
                ", body=" + body +
                '}';
    }
}
