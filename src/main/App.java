package main;

import Nodes.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.IOException;
import java.util.*;

public class App {

    public static void main(String[] args) {
        try {
            CharStream charStream = CharStreams.fromFileName(
                    "/home/adel/Desktop/compilers-project/imperative-lang-compiler/src/Tests/Test_files/Routine.txt");

            MyLangLexer myLangLexer = new MyLangLexer(charStream);
            CommonTokenStream tokenStream = new CommonTokenStream(myLangLexer);
            MyLangParser myLangParser = new MyLangParser(tokenStream);

            MyLangParser.ProgramContext context = myLangParser.program();

            UnusedVariableChecker.check(myLangParser);

//            TreeNode root = TreeBuilder.buildTree(context.children.getFirst(), myLangParser);

//            System.out.println(context.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class VariableTracker {
    private final Deque<Map<String, Integer>> scopes = new ArrayDeque<>(); // Stack of variable scopes

    public VariableTracker() {
        // Push the global scope when starting
        enterScope();
    }

    // Enter a new scope (e.g., start of a routine or block)
    public void enterScope() {
        scopes.push(new HashMap<>());
    }

    // Exit the current scope (e.g., end of a routine or block)
    public void exitScope() {
        scopes.pop();
    }

    // Declare a variable in the current scope
    public void declareVariable(String variableName) {
        if (!scopes.isEmpty()) {
            scopes.peek().put(variableName, 0);
        }
    }

    // Mark a variable as used if it exists in any scope
    public void useVariable(String variableName) {
        for (Map<String, Integer> scope : scopes) {
            if (scope.containsKey(variableName)) {
                scope.put(variableName, scope.get(variableName) + 1);
                return;
            }
        }
    }

    // Retrieve unused variables in the current scope
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

class UnusedVariableListener extends MyLangBaseListener {
    private final VariableTracker variableTracker = new VariableTracker();

    // Enter a new scope for a routine or block
    @Override
    public void enterRoutineDeclaration(MyLangParser.RoutineDeclarationContext ctx) {
        variableTracker.enterScope();
    }

    // Exit the current scope at the end of the routine
    @Override
    public void exitRoutineDeclaration(MyLangParser.RoutineDeclarationContext ctx) {
        Set<String> unusedVars = variableTracker.getUnusedVariablesInCurrentScope();
        if (!unusedVars.isEmpty()) {
            System.out.println("Unused variables in routine '" + ctx.IDENTIFIER().getText() + "': " + unusedVars);
        }
        variableTracker.exitScope();
    }

    // Track variable declarations
    @Override
    public void enterVariableDeclaration(MyLangParser.VariableDeclarationContext ctx) {
        String variableName = ctx.IDENTIFIER().getText();
        variableTracker.declareVariable(variableName);
    }

    // Track variable usage
    @Override
    public void enterModifiablePrimary(MyLangParser.ModifiablePrimaryContext ctx) {
        for (TerminalNode node :ctx.IDENTIFIER()) {
            variableTracker.useVariable(node.getText());
        }
    }

    public VariableTracker getVariableTracker() {
        return variableTracker;
    }
}

class UnusedVariableChecker {

    public static void check(MyLangParser parser) {
        ParseTree tree = parser.program();  // Assuming `program` is the entry rule

        // Walk the parse tree with our listener
        UnusedVariableListener listener = new UnusedVariableListener();
        ParseTreeWalker.DEFAULT.walk(listener, tree);

        // Check for unused variables in the global scope after parsing
        Set<String> unusedGlobalVariables = listener.getVariableTracker().getUnusedVariablesInCurrentScope();
        if (!unusedGlobalVariables.isEmpty()) {
            System.out.println("Unused global variables: " + unusedGlobalVariables);
        }
    }
}
