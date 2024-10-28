package main;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        try {
            CharStream charStream = CharStreams.fromFileName(
                    "C:\\Users\\HUAWEI\\IdeaProjects\\imperative-lang-compiler\\src\\Tests\\Test_files\\ForLoop.txt");

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

    private static void printBlockDetails(Block block, int indentLevel) {
        String indent = "    ".repeat(indentLevel);
        for (Statement statement : block.getStatements()) {
            if (statement instanceof Assignment) {
                System.out.println(indent + "Assignment: " + statement);
            } else if (statement instanceof ForLoop) {
                ForLoop forLoop = (ForLoop) statement;
                System.out.println(indent + "For Loop:");
                System.out.println(indent + "  Variable: " + forLoop.getLoopVariable());
                System.out.println(indent + "  Range: " + forLoop.getRange());
                System.out.println(indent + "  Reverse: " + forLoop.isReverse());
                System.out.println(indent + "  Body:");
                printBlockDetails(forLoop.getBody(), indentLevel + 2);
            }
        }
    }

}
