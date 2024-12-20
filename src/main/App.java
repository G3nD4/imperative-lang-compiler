package main;

import Helpers.AddIndents;
import Nodes.Program;
import Nodes.expression.AdditiveExpression;
import Nodes.expression.MultiplicativeExpression;
import Nodes.expression.UnaryExpression;
import Nodes.jasmine.CodeGenerator;
import Nodes.jasmine.ScopeManager;
import Nodes.statement.Declarations.VariableDeclaration;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.IOException;
import java.util.*;

import Nodes.Enums.Type;

import java.io.FileWriter;
import java.io.PrintWriter;

public class App {

    public static void main(String[] args) {
        try {
//            CharStream charStream = CharStreams.fromFileName(
//                    "/home/adel/Desktop/compilers-project/imperative-lang-compiler/src/Tests/Test_files/ForLoop.txt");

            CharStream charStream = CharStreams.fromFileName(
                    "C:\\Users\\HUAWEI\\IdeaProjects\\imperative-lang-compiler\\src\\Tests\\Test_files\\Routine.txt");


            MyLangLexer myLangLexer = new MyLangLexer(charStream);
            CommonTokenStream tokenStream = new CommonTokenStream(myLangLexer);
            MyLangParser myLangParser = new MyLangParser(tokenStream);

            MyLangParser.ProgramContext context = myLangParser.program();

            ModifiablePrimaryTypesOptimizator listener = new ModifiablePrimaryTypesOptimizator();
            ParseTreeWalker.DEFAULT.walk(listener, context);

            Set<String> unusedGlobalVariables = listener.getVariableTracker().getUnusedVariablesInCurrentScope();
            if (!unusedGlobalVariables.isEmpty()) {
                System.out.println("Unused global variables: " + unusedGlobalVariables);
            }

            KeywordsListener keywordUsageListener = new KeywordsListener();
            ParseTreeWalker.DEFAULT.walk(keywordUsageListener, context);

            TreeNode root = TreeBuilder.buildTree(context.children.getFirst(), myLangParser);
            RoutineDeadCodeOptimizer routineDeadCodeOptimizer = new RoutineDeadCodeOptimizer();
            routineDeadCodeOptimizer.optimize((Program) ((InternalNode) root).data);

            ConstantFoldingOptimizer constantFoldingOptimizer = new ConstantFoldingOptimizer();
            constantFoldingOptimizer.optimize((Program) ((InternalNode) root).data);


            final Program program = (Program) ((InternalNode) root).data;
            final CodeGenerator generator = new CodeGenerator();
            String result = program.generateInstructions(generator);
            result = AddIndents.add(result);
            System.out.println(result);


//            try (PrintWriter writer = new PrintWriter(new FileWriter("output.txt"))) {
//                writer.println("\n\n\n");
//                writer.println(root.toString("       "));
//                writer.println("\n\n\nDone");
//                System.out.println("Output successfully written to output.txt");
//            } catch (IOException e) {
//                System.err.println("An error occurred while writing to the file:");
//                e.printStackTrace();
//            }

            System.out.println("\n\n\nDone");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

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

class UnusedVariableListener extends MyLangBaseListener {
    private final VariableTracker variableTracker = new VariableTracker();

    @Override
    public void enterRoutineDeclaration(MyLangParser.RoutineDeclarationContext ctx) {
        variableTracker.enterScope();

        if (ctx.parameters() != null) {
            for (MyLangParser.ParameterContext param : ctx.parameters().parameter()) {
                String paramName = param.IDENTIFIER().getText();
                variableTracker.declareParameter(paramName);
            }
        }
    }

    @Override
    public void exitRoutineDeclaration(MyLangParser.RoutineDeclarationContext ctx) {
        Set<String> unusedVars = variableTracker.getUnusedVariablesInCurrentScope();
        if (!unusedVars.isEmpty()) {
            System.out.println("Unused variables in routine '" + ctx.IDENTIFIER().getText() + "': " + unusedVars);
        }
        variableTracker.exitScope();
    }

    @Override
    public void enterForLoop(MyLangParser.ForLoopContext ctx) {
        // Declare the loop variable
        String loopVarName = ctx.IDENTIFIER().getText();
        variableTracker.declareVariable(loopVarName);
        variableTracker.enterScope();
    }

    @Override
    public void exitForLoop(MyLangParser.ForLoopContext ctx) {
        variableTracker.exitScope();
    }

    @Override
    public void enterVariableDeclaration(MyLangParser.VariableDeclarationContext ctx) {
        String variableName = ctx.IDENTIFIER().getText();
        variableTracker.declareVariable(variableName);
    }

    @Override
    public void enterModifiablePrimary(MyLangParser.ModifiablePrimaryContext ctx) {
        for (TerminalNode node : ctx.IDENTIFIER()) {
            variableTracker.useVariable(node.getText());
        }
    }

    public VariableTracker getVariableTracker() {
        return variableTracker;
    }
}


class ModifiablePrimaryTypesOptimizator extends MyLangBaseListener {
    private final VariableTracker variableTracker = new VariableTracker();
    private ScopeManager scopeManager = new ScopeManager();
    private HashMap<String, HashMap<String, Type>> modPrimToType = new HashMap<>();

    public ModifiablePrimaryTypesOptimizator() {
        scopeManager.enterScope("main");
    }

    @Override
    public void enterRoutineDeclaration(MyLangParser.RoutineDeclarationContext ctx) {
        variableTracker.enterScope();

        if (ctx.parameters() != null) {
            for (MyLangParser.ParameterContext param : ctx.parameters().parameter()) {
                String paramName = param.IDENTIFIER().getText();
                variableTracker.declareParameter(paramName);
            }
        }
    }

    @Override
    public void exitRoutineDeclaration(MyLangParser.RoutineDeclarationContext ctx) {
        Set<String> unusedVars = variableTracker.getUnusedVariablesInCurrentScope();
        if (!unusedVars.isEmpty()) {
            System.out.println("Unused variables in routine '" + ctx.IDENTIFIER().getText() + "': " + unusedVars);
        }
        variableTracker.exitScope();
    }

    @Override
    public void enterForLoop(MyLangParser.ForLoopContext ctx) {
        // Declare the loop variable
        String loopVarName = ctx.IDENTIFIER().getText();
        variableTracker.declareVariable(loopVarName);
        variableTracker.enterScope();
    }

    @Override
    public void exitForLoop(MyLangParser.ForLoopContext ctx) {
        variableTracker.exitScope();
    }

    @Override
    public void enterVariableDeclaration(MyLangParser.VariableDeclarationContext ctx) {
        String variableName = ctx.IDENTIFIER().getText();
        variableTracker.declareVariable(variableName);
    }

    @Override
    public void enterModifiablePrimary(MyLangParser.ModifiablePrimaryContext ctx) {
        for (TerminalNode node : ctx.IDENTIFIER()) {
            variableTracker.useVariable(node.getText());
        }
    }

    public VariableTracker getVariableTracker() {
        return variableTracker;
    }
}

