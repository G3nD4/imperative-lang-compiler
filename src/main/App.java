package main;

import java.io.File;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();

        ArrayList<Token> tokens = lexicalAnalyzer.analyzeProgram(new File("src/main/code.txt"));

        for (Token token : tokens) {
            System.out.println(token.toString());
        }
        System.out.println("Total amount of tokens: " + tokens.size());
    }
}
