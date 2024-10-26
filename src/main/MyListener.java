package main;

import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.ArrayList;
import java.util.List;

public class MyListener extends MyLangBaseListener {
    private List<Function> routines = new ArrayList<>();
    private List<VariableDeclaration> variableDeclarations = new ArrayList<>();
    private List<Assignment> assignments = new ArrayList<>(); // List to store assignments

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
        String body = ctx.body().getText();  // You might want to process this further

        routines.add(new Function(name, parameters, returnType, body));
    }

    @Override
    public void enterVariableDeclaration(MyLangParser.VariableDeclarationContext ctx) {
        List<String> identifiers = new ArrayList<>();
        identifiers.addAll(ctx.identifierList().IDENTIFIER().stream().map(i -> i.getText()).toList());

        String type = ctx.type() != null ? ctx.type().getText() : null;
        List<String> initialValues = ctx.expressionList() != null
                ? ctx.expressionList().expression().stream().map(e -> e.getText()).toList()
                : new ArrayList<>();

        variableDeclarations.add(new VariableDeclaration(identifiers, type, initialValues));
    }

    @Override
    public void enterAssignment(MyLangParser.AssignmentContext ctx) {
        String variable = ctx.modifiablePrimary().getText(); // Extract the variable being assigned to
        String expression = ctx.expression().getText(); // Extract the expression assigned to the variable
        assignments.add(new Assignment(variable, expression));
    }

    public List<Function> getRoutines() {
        return routines;
    }

    public List<VariableDeclaration> getVariableDeclarations() {
        return variableDeclarations;
    }

    public List<Assignment> getAssignments() {
        return assignments; // Getter for assignments
    }
}
