package main;

import Nodes.*;
import Nodes.expression.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MyListener extends MyLangBaseListener {
    private List<RoutineDeclarationStatement> routines = new ArrayList<>();
    private List<VariableDeclaration> variableDeclarations = new ArrayList<>();
    private List<IfStatement> ifStatements = new ArrayList<>();
    private Block rootBlock = new Block(); // Root block for top-level statements
    private Stack<Block> blockStack = new Stack<>();  // Stack to manage nested blocks

    public MyListener() {
        blockStack.push(rootBlock);
    }

    @Override
    public void enterRoutineDeclaration(MyLangParser.RoutineDeclarationContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        List<Parameter> parameters = new ArrayList<>();

        if (ctx.parameters() != null) {
            for (MyLangParser.ParameterContext paramCtx : ctx.parameters().parameter()) {
                String paramName = paramCtx.IDENTIFIER().getText();
                String paramType = paramCtx.type().getText();
                parameters.add(new Parameter(paramName, paramType));
            }
        }

        String returnType = ctx.type() != null ? ctx.type().getText() : "void";

        Block body = new Block();
        blockStack.push(body); // Start a new block for the function body

        routines.add(new RoutineDeclarationStatement(name, parameters, returnType, body));
    }

    @Override
    public void exitRoutineDeclaration(MyLangParser.RoutineDeclarationContext ctx) {
        blockStack.pop(); // Finish function block
    }

    @Override
    public void enterForLoop(MyLangParser.ForLoopContext ctx) {
        // Get loop variable
        String loopVariable = ctx.IDENTIFIER().getText();

        // Get range
        String range = ctx.range().getText();  // Or further parse if necessary

        // Check for 'REVERSE' keyword
        boolean reverse = ctx.REVERSE() != null;

        // Create a new block for the loop body
        Block forBody = new Block();

        // Create and add the ForLoop object to the current block in the stack
        ForLoop forLoop = new ForLoop(loopVariable, range, reverse, forBody);
        blockStack.peek().addStatement(forLoop);

        // Push the new for-loop block onto the stack to parse its body
        blockStack.push(forBody);
    }

    @Override
    public void exitForLoop(MyLangParser.ForLoopContext ctx) {
        // Pop the for-loop block from the stack after parsing its body
        blockStack.pop();
    }


    @Override
    public void enterAssignment(MyLangParser.AssignmentContext ctx) {
        String variable = ctx.modifiablePrimary().getText();
        String expression = ctx.expression().getText();
        Assignment assignment = new Assignment(variable, expression);

        blockStack.peek().addStatement(assignment); // Add assignment to the current block
    }

    @Override
    public void enterIfStatement(MyLangParser.IfStatementContext ctx) {
        Expression condition = null;
        Block ifBody = new Block();
        IfStatement ifStatement = new IfStatement(condition, ifBody);
        ifStatements.add(ifStatement);

        blockStack.peek().addStatement(ifStatement);
        blockStack.push(ifBody);
    }

    @Override
    public void exitIfStatement(MyLangParser.IfStatementContext ctx) {
        blockStack.pop();
    }

    @Override
    public void enterVariableDeclaration(MyLangParser.VariableDeclarationContext ctx) {
        String variableName = ctx.IDENTIFIER().getText();
        String type = ctx.type() != null ? ctx.type().getText() : "unknown";
        String initialization = ctx.expression() != null ? ctx.expression().getText() : null;

        VariableDeclaration varDecl = new VariableDeclaration(variableName, type, initialization);
        variableDeclarations.add(varDecl);  // Track the variable declarations

        blockStack.peek().addStatement(varDecl);  // Add variable declaration to the current block
    }

    @Override
    public void enterWhileLoop(MyLangParser.WhileLoopContext ctx) {
        String expr = ctx.expression().getText();
        Block whileBody = new Block();

        WhileLoop whileLoop = new WhileLoop(expr, whileBody);
        blockStack.peek().addStatement(whileLoop);

        blockStack.push(whileBody);
    }

    @Override
    public void exitWhileLoop(MyLangParser.WhileLoopContext ctx) {
        blockStack.pop();
    }

    public List<RoutineDeclarationStatement> getRoutines() {
        return routines;
    }

    public List<VariableDeclaration> getVariableDeclarations() {
        return variableDeclarations;
    }

    public List<IfStatement> getIfStatements() {
        return ifStatements;
    }

    public Block getRootBlock() {
        return rootBlock;
    }
}
