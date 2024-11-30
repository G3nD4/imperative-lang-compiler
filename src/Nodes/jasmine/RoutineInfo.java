package Nodes.jasmine;

import Nodes.Enums.Type;
import org.antlr.v4.misc.OrderedHashMap;

import java.util.ArrayList;

public class RoutineInfo {
    private final Type returnType;
    private final OrderedHashMap<String, VariableInfo> variables;
    private final ArrayList<Type> parametersTypes;

    public RoutineInfo(Type returnType, OrderedHashMap<String, VariableInfo> variables) {
        this.returnType = returnType;
        this.variables = variables;
        this.parametersTypes = new ArrayList<>();
        variables.values().forEach(variableInfo -> parametersTypes.add(variableInfo.getType()));
    }

    public Type getReturnType() {
        return returnType;
    }

    public void registerVariable(String name, VariableInfo info) {
        if (this.variables.containsKey(name)) {
            System.out.println("Variable " + name + " is already defined!");
            System.exit(1);
        }
        this.variables.put(name, info);
    }

    public OrderedHashMap<String, VariableInfo> getVariables() {
        return variables;
    }

    public ArrayList<Type> getParametersTypes() {
        return parametersTypes;
    }
}
