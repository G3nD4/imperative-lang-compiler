package Tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import main.*;

public class LexicalAnalyzerTest {
    LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();

    @Test
    public void checkAssignment() {
        ArrayList<Token> tokens = lexicalAnalyzer.analyzeProgram(new File("src/Tests/Test_files/Assignment.txt"));
        ArrayList<TokenType> correctAnswer = new ArrayList<>(Arrays.asList(TokenType.IDENTIFIER, TokenType.ASSIGN, TokenType.IDENTIFIER));

        Assertions.assertEquals(correctAnswer, makeListOfTokenTypes(tokens));
    }

    @Test
    public void checkIfStatement() {
        ArrayList<Token> tokens = lexicalAnalyzer.analyzeProgram(new File("src/Tests/Test_files/IfStatement.txt"));
        ArrayList<TokenType> correctAnswer = new ArrayList<>(Arrays.asList(TokenType.IF, TokenType.IDENTIFIER,
                TokenType.MOD, TokenType.IDENTIFIER, TokenType.EQUAL, TokenType.IDENTIFIER, TokenType.THEN,
                TokenType.VAR, TokenType.IDENTIFIER, TokenType.COLON, TokenType.DATA_TYPE_INTEGER, TokenType.IS,
                TokenType.IDENTIFIER, TokenType.ELSE, TokenType.VAR, TokenType.IDENTIFIER, TokenType.COLON,
                TokenType.DATA_TYPE_INTEGER, TokenType.IS, TokenType.IDENTIFIER, TokenType.END));

        Assertions.assertEquals(correctAnswer, makeListOfTokenTypes(tokens));
    }

    @Test
    public void checkNestedIf() {
        ArrayList<Token> tokens = lexicalAnalyzer.analyzeProgram(new File("src/Tests/Test_files/NestedIf.txt"));
        ArrayList<TokenType> correctAnswer = new ArrayList<>(Arrays.asList(TokenType.IF, TokenType.IDENTIFIER,
                TokenType.LESS_THAN, TokenType.IDENTIFIER, TokenType.THEN, TokenType.IF, TokenType.IDENTIFIER,
                TokenType.LESS_THAN, TokenType.IDENTIFIER, TokenType.THEN, TokenType.VAR, TokenType.IDENTIFIER,
                TokenType.COLON, TokenType.DATA_TYPE_INTEGER, TokenType.IS, TokenType.IDENTIFIER, TokenType.ELSE,
                TokenType.VAR, TokenType.IDENTIFIER, TokenType.COLON, TokenType.DATA_TYPE_INTEGER, TokenType.IS,
                TokenType.IDENTIFIER, TokenType.END, TokenType.ELSE, TokenType.VAR, TokenType.IDENTIFIER,
                TokenType.COLON, TokenType.DATA_TYPE_INTEGER, TokenType.IS, TokenType.IDENTIFIER, TokenType.END));

        Assertions.assertEquals(correctAnswer, makeListOfTokenTypes(tokens));
    }

    @Test
    public void checkRoutine() {
        ArrayList<Token> tokens = lexicalAnalyzer.analyzeProgram(new File("src/Tests/Test_files/Routine.txt"));
        ArrayList<TokenType> correctAnswer = new ArrayList<>(Arrays.asList(TokenType.ROUTINE, TokenType.IDENTIFIER,
                TokenType.LEFT_PAREN, TokenType.IDENTIFIER, TokenType.COLON, TokenType.DATA_TYPE_INTEGER,
                TokenType.COMMA, TokenType.IDENTIFIER, TokenType.COLON, TokenType.DATA_TYPE_INTEGER, TokenType.RIGHT_PAREN,
                TokenType.COLON, TokenType.DATA_TYPE_INTEGER, TokenType.IS, TokenType.IDENTIFIER, TokenType.ASSIGN,
                TokenType.IDENTIFIER, TokenType.PLUS, TokenType.IDENTIFIER, TokenType.END));

        Assertions.assertEquals(correctAnswer, makeListOfTokenTypes(tokens));
    }

    @Test
    public void checkTypeDeclaration() {
        ArrayList<Token> tokens = lexicalAnalyzer.analyzeProgram(new File("src/Tests/Test_files/TypeDeclaration.txt"));
        ArrayList<TokenType> correctAnswer = new ArrayList<>(Arrays.asList(TokenType.TYPE, TokenType.IDENTIFIER,
                TokenType.IS, TokenType.DATA_TYPE_INTEGER));

        Assertions.assertEquals(correctAnswer, makeListOfTokenTypes(tokens));
    }

    @Test
    public void checkVariableDeclaration() {
        ArrayList<Token> tokens = lexicalAnalyzer.analyzeProgram(new File("src/Tests/Test_files/VariableDeclaration.txt"));
        ArrayList<TokenType> correctAnswer = new ArrayList<>(Arrays.asList(TokenType.VAR, TokenType.IDENTIFIER,
                TokenType.COLON, TokenType.DATA_TYPE_REAL, TokenType.IS, TokenType.IDENTIFIER));

        Assertions.assertEquals(correctAnswer, makeListOfTokenTypes(tokens));
    }

    @Test
    public void checkForLoop() {
        ArrayList<Token> tokens = lexicalAnalyzer.analyzeProgram(new File("src/Tests/Test_files/ForLoop.txt"));
        ArrayList<TokenType> correctAnswer = new ArrayList<>(Arrays.asList(TokenType.ROUTINE, TokenType.IDENTIFIER,
                TokenType.LEFT_PAREN, TokenType.RIGHT_PAREN, TokenType.COLON, TokenType.DATA_TYPE_INTEGER,
                TokenType.IS, TokenType.FOR, TokenType.IDENTIFIER, TokenType.IN, TokenType.IDENTIFIER, TokenType.RANGE,
                TokenType.IDENTIFIER, TokenType.LOOP, TokenType.IDENTIFIER, TokenType.ASSIGN, TokenType.IDENTIFIER, TokenType.PLUS,
                TokenType.IDENTIFIER, TokenType.END, TokenType.END));


        Assertions.assertEquals(correctAnswer, makeListOfTokenTypes(tokens));
    }

    @Test
    public void checkWhileLoop() {
        ArrayList<Token> tokens = lexicalAnalyzer.analyzeProgram(new File("src/Tests/Test_files/WhileLoop.txt"));
        ArrayList<TokenType> correctAnswer = new ArrayList<>(Arrays.asList(TokenType.ROUTINE, TokenType.IDENTIFIER,
                TokenType.LEFT_PAREN, TokenType.RIGHT_PAREN, TokenType.COLON, TokenType.DATA_TYPE_INTEGER,
                TokenType.IS, TokenType.WHILE, TokenType.IDENTIFIER, TokenType.LESS_EQUAL, TokenType.IDENTIFIER,
                TokenType.LOOP, TokenType.IDENTIFIER, TokenType.ASSIGN, TokenType.IDENTIFIER, TokenType.MULTIPLY,
                TokenType.IDENTIFIER, TokenType.IDENTIFIER, TokenType.ASSIGN, TokenType.IDENTIFIER, TokenType.PLUS,
                TokenType.IDENTIFIER, TokenType.END, TokenType.END));

        Assertions.assertEquals(correctAnswer, makeListOfTokenTypes(tokens));
    }

    private ArrayList<TokenType> makeListOfTokenTypes(ArrayList<Token> tokens) {
        ArrayList<TokenType> answer = new ArrayList<>();
        for (Token token : tokens) {
            answer.add(token.getType());
        }

        return answer;
    }
}
