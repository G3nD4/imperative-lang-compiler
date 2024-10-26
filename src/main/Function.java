package main;

import java.util.List;

public class Function {
    private final String name;
    private final List<Parameter> parameters;
    private final String returnType;
    private final String body;

    public Function(String name, List<Parameter> parameters, String returnType, String body) {
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

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Function{" +
                "name='" + name + '\'' +
                ", parameters=" + parameters +
                ", returnType='" + returnType + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
