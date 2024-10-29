package main;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.HashSet;
import java.util.Set;

public class KeywordsListener extends MyLangBaseListener {
    private boolean inLoop = false;
    private boolean inFunction = false;

    @Override
    public void enterWhileLoop(MyLangParser.WhileLoopContext ctx) {
        inLoop = true;
    }

    @Override
    public void exitWhileLoop(MyLangParser.WhileLoopContext ctx) {
        inLoop = false;
    }

    @Override
    public void enterForLoop(MyLangParser.ForLoopContext ctx) {
        inLoop = true;
    }

    @Override
    public void exitForLoop(MyLangParser.ForLoopContext ctx) {
        inLoop = false;
    }

    @Override
    public void enterRoutineDeclaration(MyLangParser.RoutineDeclarationContext ctx) {
        inFunction = true;
    }

    @Override
    public void exitRoutineDeclaration(MyLangParser.RoutineDeclarationContext ctx) {
        inFunction = false;
    }

    @Override
    public void enterBreakStatement(MyLangParser.BreakStatementContext ctx) {
        if (!inLoop) {
            System.out.println("Error: 'break' can only appear inside loops.");
            System.exit(-1);
        }
    }

    @Override
    public void enterReturnStatement(MyLangParser.ReturnStatementContext ctx) {
        if (!inFunction) {
            System.out.println("Error: 'return' can only appear inside functions.");
            System.exit(-1);
        }
    }
}