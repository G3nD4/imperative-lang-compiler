package Nodes.jasmine;

import java.util.Stack;

public class ScopeManager {

    public ScopeManager() {
        // Empty scope stack means you are in global scope.
        scope = new Stack<>();
    }

    private final Stack<String> scope;

    public boolean isMainScope() {
        return scope.size() == 1 && scope.peek().equals("main");
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
        System.out.println(scope);
        return isMainScope() ? "main" : scope.getLast();
    }
}
