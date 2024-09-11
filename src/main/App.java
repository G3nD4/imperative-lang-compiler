package main;

import java.io.File;
import java.util.ArrayList;

import Lexical_analyzer.LexicalAnalyzer;
import Lexical_analyzer.Token;

public class App {

    public static void main(String[] args) {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();

        ArrayList<Token> tokens = lexicalAnalyzer.analyzeProgram(new File("src/Tests/Test_files/IncorrectNames"));

        for (Token token : tokens) {
            System.out.println(token.toString());
        }
        System.out.println("Total amount of tokens: " + tokens.size());
    }
}
