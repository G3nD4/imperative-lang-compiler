package Nodes.jasmine;

import Nodes.Parameter;
import Nodes.Enums.Type;
import org.antlr.v4.misc.OrderedHashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CodeGenerator {

    public CodeGenerator() {
        types.put("integer", Type.INTEGER);
        types.put("real", Type.REAL);
        types.put("boolean", Type.BOOLEAN);
    }

    private final StringBuilder assemblyProgram = new StringBuilder();
    private int currentStackIndex = 0;  // REFERS TO FIRST FREE INDEX
    private final Map<String, VariableInfo> variables = new HashMap<>();
    private final Map<String, RoutineInfo> routines = new HashMap<>();
    private int labelCounter = 0; // Counter for generating unique labels
    private HashMap<String, Integer> stackIndices = new HashMap<>();
    static private final Map<String, Type> types = new HashMap<>();
    private final ScopeManager scopeManager = new ScopeManager();

    public void enterScope(String scopeName) {
        scopeManager.enterScope(scopeName);
    }

    public void exitScope() {
        scopeManager.exitScope();
    }

    public void registerVariable(String name, Type type) {
        final String scope = scopeManager.getCurrentScope();
        if (scope.equals("main")) {
            final int index = getAndIncreaseCurrentStackIndex();
            variables.put(name, new VariableInfo(index, type));
        } else {
            final int index = getAndIncreaseCurrentStackIndex();
            routines.get(scope).registerVariable(name, new VariableInfo(index, type));
        }
    }

    public void registerRoutine(String name, Type returnType, ArrayList<Parameter> params) {
        if (routines.containsKey(name)) {
            System.out.println("Routine " + name + " is already defined!");
            System.exit(1);
        }
        final OrderedHashMap<String, VariableInfo> parameters = new OrderedHashMap<>();
        for (final Parameter param : params) {
            parameters.put(param.getName(), new VariableInfo(getAndIncreaseCurrentStackIndex(), param.getType()));
        }
        final RoutineInfo routineInfo = new RoutineInfo(returnType, parameters);
        routines.put(name, routineInfo);
    }

    public VariableInfo getVariable(String name) {
        final String scope = scopeManager.getCurrentScope();
        if (scope != null) {
            // Try to get variable from current scope.
            final VariableInfo info = routines.get(scope).getVariables().get(name);
            if (info != null) {
                return info;
            }
            // Variable is not found in current scope.
        }
        // TODO may cause issues
        // Get variable from global scope.
        final VariableInfo info = variables.get(name);
        // If trying to access a variable that does not exist, log an error and exit the program
        if (info == null) {
            System.out.println(("Variable " + name + " does not exist!"));
            System.exit(1);
        }
        return info;
    }

    public String getProgramText() {
        return assemblyProgram.toString();
    }

    public void writeToProgram(String instruction) {
        assemblyProgram.append(instruction).append('\n');
    }

    public void writeToProgram(ArrayList<String> instructions) {
        for (String instruction : instructions) {
            assemblyProgram.append(instruction).append('\n');
        }
    }

    public String generateUniqueLabel(String baseLabel) {
        return baseLabel + "_" + (labelCounter++);
    }

    public void writeLabel(String label) {
        assemblyProgram.append(label).append(":\n");
    }

    public int getCurrentStackIndex() {
        final String scope = scopeManager.getCurrentScope();
        stackIndices.putIfAbsent(scope, 0);
        final int index = stackIndices.get(scope);
        return index > 0 ? index - 1 : 0;
    }

    public int getFreeIndex() {
        final String scope = scopeManager.getCurrentScope();
        stackIndices.putIfAbsent(scope, 0);
        return stackIndices.get(scope);
    }

    public int getAndIncreaseCurrentStackIndex() {
        final String scope = scopeManager.getCurrentScope();
        stackIndices.putIfAbsent(scope, 0);
        final int index = stackIndices.get(scope);
        stackIndices.put(scope, index + 1);
        return index;
    }

    public void setStackIndex(int index) {
        final String scope = scopeManager.getCurrentScope();
        stackIndices.putIfAbsent(scope, 0);
        stackIndices.put(scope, index);
    }

    public void increaseStackIndex() {
        final String scope = scopeManager.getCurrentScope();
        stackIndices.putIfAbsent(scope, 0);
        stackIndices.put(scope, stackIndices.get(scope) + 1);
    }

    // Checks if the routine with "name" exists
    public Boolean isRoutine(String routineName) {
        return routines.containsKey(routineName);
    }

    public RoutineInfo getRoutineInfo(String routineName) {
        return this.routines.get(routineName);
    }

    public static Type getType(String identifier) {
        final Type type = types.get(identifier);
        if (type == null) {
            System.out.println("Type " + identifier + " is not defined!");
            System.exit(1);
        }
        return type;
    }

    public static void registerType(String identifier, Type type) {
        types.put(identifier, type);
    }

    public String getCurrentScope() {
        return scopeManager.getCurrentScope();
    }
}
