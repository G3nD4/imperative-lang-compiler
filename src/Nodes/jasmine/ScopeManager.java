package Nodes.jasmine;

import java.util.Stack;

public class ScopeManager {

    public ScopeManager() {
        // Empty scope stack means you are in global scope.
        scope = new Stack<>();
    }

    private final Stack<String> scope;

    public boolean isMainScope() {
        return scope.isEmpty();
    }

    public void enterScope(String scopeName) {
        scope.push(scopeName);
    }

    public void exitScope() {
        if (scope.isEmpty()) {
            System.out.println("You are already in main scope!");
            return;
        }
        scope.pop();
    }

    public String getCurrentScope() {
        return isMainScope() ? null : scope.getLast();
    }
}
