package Nodes.jasmine;

import Nodes.Type;
import org.antlr.v4.misc.OrderedHashMap;

public class RoutineInfo {
    private final Type returnType;
    private final OrderedHashMap<String, VariableInfo> variables;

    public RoutineInfo(Type returnType, OrderedHashMap<String, VariableInfo> variables) {
        this.returnType = returnType;
        this.variables = variables;
    }

    public Type getReturnType() {
        return returnType;
    }

    public void registerVariable(String name, VariableInfo info) {
        this.variables.put(name, info);
    }

    public OrderedHashMap<String, VariableInfo> getVariables() {
        return variables;
    }
}
