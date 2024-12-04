package ErrorsFounder;

import java.util.*;

class VariableTracker {
    private final Stack<Map<String, Integer>> scopes = new Stack<>();

    public VariableTracker() {
        enterScope();
    }

    public void enterScope() {
        scopes.push(new HashMap<>());
    }

    public void exitScope() {
        if (!scopes.isEmpty()) {
            scopes.pop();
        }
    }

    public void declareParameter(String paramName) {
        if (!scopes.isEmpty()) {
            scopes.peek().put(paramName, 0);
        }
    }

    public void declareVariable(String variableName) {
        if (!scopes.isEmpty()) {
            scopes.peek().put(variableName, 0);
        }
    }

    public boolean isDeclared(String variableName) {
        for (Map<String, Integer> scope : scopes) {
            if (scope.containsKey(variableName)) {
                return true;
            }
        }
        return false;
    }

    public void useVariable(String variableName) {
        if (!isDeclared(variableName)) {
            System.out.println("Error: Variable '" + variableName + "' used before declaration.");
            System.exit(-1);
        }
        for (Map<String, Integer> scope : scopes) {
            if (scope.containsKey(variableName)) {
                scope.put(variableName, scope.get(variableName) + 1);
                return;
            }
        }
    }

    public Set<String> getUnusedVariablesInCurrentScope() {
        Set<String> unusedVars = new HashSet<>();
        if (!scopes.isEmpty()) {
            for (Map.Entry<String, Integer> entry : scopes.peek().entrySet()) {
                if (entry.getValue() == 0) {
                    unusedVars.add(entry.getKey());
                }
            }
        }
        return unusedVars;
    }
}