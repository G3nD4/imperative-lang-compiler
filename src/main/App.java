package main;

import Nodes.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        try {
            CharStream charStream = CharStreams.fromFileName(
                    "C:\\Users\\HUAWEI\\IdeaProjects\\imperative-lang-compiler\\src\\Tests\\Test_files\\WhileLoop.txt");

            MyLangLexer myLangLexer = new MyLangLexer(charStream);
            CommonTokenStream tokenStream = new CommonTokenStream(myLangLexer);
            MyLangParser myLangParser = new MyLangParser(tokenStream);

            MyLangParser.ProgramContext context = myLangParser.program();

            MyListener listener = new MyListener();
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(listener, context);

            System.out.println("Functions:");
            listener.getRoutines().forEach(App::printFunctionDetails);
            System.out.println("--------------------------");

            System.out.println("If statements:");
            listener.getIfStatements().forEach(App::printIfDetails);
            System.out.println("--------------------------");

            System.out.println("\nVariable Declarations:");
            System.out.println(listener.getVariableDeclarations());
            System.out.println("--------------------------");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printFunctionDetails(Function function) {
        System.out.println("Function: " + function.getName());
        System.out.println("Return Type: " + function.getReturnType());
        System.out.println("Parameters: " + function.getParameters());
        System.out.println("Body:");
        printBlockDetails(function.getBody(), 1);
    }

    public static void printIfDetails(If ifStatement) {
        System.out.println("Condition: " + ifStatement.getCondition());
        System.out.println("Body:");
        printBlockDetails(ifStatement.getBody(), 1);
    }

    private static void printBlockDetails(Block block, int indentLevel) {
        String indent = "    ".repeat(indentLevel);
        for (Statement statement : block.getStatements()) {
            if (statement instanceof Assignment) {
                Assignment assignment = (Assignment) statement;
                System.out.println(indent + "Assignment: " + assignment.getVariable() + " = " + assignment.getExpression());
            } else if (statement instanceof VariableDeclaration) {
                VariableDeclaration varDecl = (VariableDeclaration) statement;
                System.out.println(indent + "Variable Declaration: " + varDecl.getType() + " " + varDecl.getIdentifier() +
                        (varDecl.getInitialValue() != null ? " = " + varDecl.getInitialValue() : ""));
            } else if (statement instanceof If) {
                If ifStatement = (If) statement;
                System.out.println(indent + "If Statement: Condition = " + ifStatement.getCondition());
                System.out.println(indent + "  Body:");
                printBlockDetails(ifStatement.getBody(), indentLevel + 1);
            } else if (statement instanceof ForLoop) {
                ForLoop forLoop = (ForLoop) statement;
                System.out.println(indent + "For Loop:");
                System.out.println(indent + "  Variable: " + forLoop.getLoopVariable());
                System.out.println(indent + "  Range: " + forLoop.getRange());
                System.out.println(indent + "  Reverse: " + forLoop.isReverse());
                System.out.println(indent + "  Body:");
                printBlockDetails(forLoop.getBody(), indentLevel + 1);
            } else if (statement instanceof WhileLoop) {
                WhileLoop whileLoop = (WhileLoop) statement;
                System.out.println(indent + "While Loop: Condition = " + whileLoop.getCondition());
                System.out.println(indent + "  Body:");
                printBlockDetails(whileLoop.getBody(), indentLevel + 1);
            }
        }
    }
}
