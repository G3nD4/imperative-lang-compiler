package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class LexicalAnalyzer {

    private HashMap<String, TokenType> tokens = new HashMap<>();
    private ArrayList<String> delimeters = new ArrayList<>();

    public LexicalAnalyzer() {
        initTokens();
    }

    public ArrayList<Token> analyzeProgram(File programFile) {
        ArrayList<Token> tokenList = new ArrayList<>();

        // Split file by \n and get list of all lines
        ArrayList<String> lines = new ArrayList<>();
        splitFileByLines(programFile, lines);

        for (String line : lines) {
            analyzeLine(line, tokenList, lines.indexOf(line) + 1);
        }

        ArrayList<Token> finalTokenList = combineDelimeters(tokenList);

        return finalTokenList;
    }

    private ArrayList<Token> combineDelimeters(ArrayList<Token> tokenList) {
        ArrayList<Token> combinedDelimetersTokenList = new ArrayList<>();
        int i = 0;
        while (i < tokenList.size()) {
            boolean combined = false;
            switch (tokenList.get(i).getName()) {
                case "<":
                    if ("=".equals(tokenList.get(i + 1).getName())) {
                        Token token = tokenList.get(i).copyWith("<=", TokenType.LESS_EQUAL);
                        combinedDelimetersTokenList.add(token);
                        ++i;
                        combined = true;
                    }
                    break;
                case ">":
                    if ("=".equals(tokenList.get(i + 1).getName())) {
                        Token token = tokenList.get(i).copyWith(">=", TokenType.GREATER_EQUAL);
                        combinedDelimetersTokenList.add(token);
                        ++i;
                        combined = true;
                    }
                    break;
                case ":":
                    if ("=".equals(tokenList.get(i + 1).getName())) {
                        Token token = tokenList.get(i).copyWith(":=", TokenType.ASSIGN);
                        combinedDelimetersTokenList.add(token);
                        ++i;
                        combined = true;
                    }
                    break;
                case "/":
                    if ("=".equals(tokenList.get(i + 1).getName())) {
                        Token token = tokenList.get(i).copyWith("/=", TokenType.NOT_EQUAL);
                        combinedDelimetersTokenList.add(token);
                        ++i;
                        combined = true;
                    }
                    break;
                case ".":
                    if (".".equals(tokenList.get(i + 1).getName())) {
                        Token token = tokenList.get(i).copyWith("..", TokenType.RANGE);
                        combinedDelimetersTokenList.add(token);
                        ++i;
                        combined = true;
                    } else {
                        // If we are on the first or last token of the line no need to do anything
                        if (i == 0 || i == tokenList.size() - 1) {
                            continue;
                        }
                        if (isNumber(tokenList.get(i - 1).getName()) && isNumber(tokenList.get(i + 1).getName())) {
                            String firstName = tokenList.get(i - 1).getName();
                            String secondName = tokenList.get(i + 1).getName();
                            Token token = TokenFactory.realNumber(tokenList.get(i - 1).getSpan(), TokenType.IDENTIFIER, tokenList.get(i - 1).getId(), firstName + "." + secondName, Double.parseDouble(firstName + "." + secondName));
                            combinedDelimetersTokenList.removeLast();
                            combinedDelimetersTokenList.add(token);
                            ++i;
                            combined = true;
                        }
                    }
                    break;

            }
            if (!combined) {
                combinedDelimetersTokenList.add(tokenList.get(i).copy());
            }
            ++i;
        }

        return combinedDelimetersTokenList;
    }

    private boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void analyzeLine(String line, ArrayList<Token> tokenList, int lineNumber) {
        String currentToken = null;

        for (int i = 0; i < line.length(); i++) {
            String currentChar = Character.toString(line.charAt(i));
            if (currentToken != null) {
                if (" ".equals(currentChar)) {
                    addToken(lineNumber, currentToken, tokenList);
                    currentToken = null;
                } else {
                    if (!"-1".equals(getDelimeter(currentChar))) {
                        addToken(lineNumber, currentToken, tokenList);
                        addToken(lineNumber, currentChar, tokenList);
                        currentToken = null;
                    } else {
                        currentToken = currentToken + line.charAt(i);
                    }
                }
            } else {
                if (!" ".equals(currentChar)) {
                    if (!"-1".equals(getDelimeter(currentChar))) {
                        addToken(lineNumber, currentChar, tokenList);
                    } else {
                        currentToken = currentChar;
                    }
                }
            }
        }
        if (currentToken != null) {
            addToken(lineNumber, currentToken, tokenList);
        }
    }

    String getDelimeter(String ch) {
        if (delimeters.contains(ch)) {
            return ch;
        }
        return "-1";
    }

    void addToken(int lineNumber, String currentToken, ArrayList<Token> tokenList) {
        if (tokens.containsKey(currentToken)) {
            tokenList.add(new Token(new Span(lineNumber), tokens.get(currentToken), tokenList.size(), currentToken));
        } else {
            if (isNumber(currentToken)) {
                Token token = TokenFactory.intNumber(new Span(lineNumber), TokenType.IDENTIFIER, tokenList.size(), currentToken, Integer.parseInt(currentToken));
                tokenList.add(token);
            } else {
                tokenList.add(new Token(new Span(lineNumber), TokenType.IDENTIFIER, tokenList.size(), currentToken));
            }
        }
    }

    private void splitFileByLines(File programFile, ArrayList<String> lines) {
        try (BufferedReader reader = new BufferedReader(new FileReader(programFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initTokens() {
        tokens.put("and", TokenType.AND);
        tokens.put("or", TokenType.OR);
        tokens.put("xor", TokenType.XOR);
        tokens.put("not", TokenType.NOT);

        tokens.put("+", TokenType.PLUS);
        tokens.put("-", TokenType.MINUS);
        tokens.put("*", TokenType.MULTIPLY);
        tokens.put("/", TokenType.DIVIDE);
        tokens.put("%", TokenType.MOD);

        tokens.put("<", TokenType.LESS_THAN);
        tokens.put(">", TokenType.GREATER_THAN);
        tokens.put(":=", TokenType.ASSIGN);
        tokens.put("<=", TokenType.LESS_EQUAL);
        tokens.put(">=", TokenType.GREATER_EQUAL);
        tokens.put("/=", TokenType.NOT_EQUAL);
        tokens.put("=", TokenType.EQUAL);

        tokens.put("(", TokenType.LEFT_PAREN);
        tokens.put(")", TokenType.RIGHT_PAREN);
        tokens.put("{", TokenType.LEFT_BRACKET);
        tokens.put("}", TokenType.RIGHT_BRACKET);
        tokens.put(":", TokenType.COLON);
        tokens.put(".", TokenType.DOT);

        tokens.put("var", TokenType.VAR);
        tokens.put("type", TokenType.TYPE);
        tokens.put("routine", TokenType.ROUTINE);
        tokens.put("is", TokenType.IS);
        tokens.put("end", TokenType.END);
        tokens.put("while", TokenType.WHILE);
        tokens.put("loop", TokenType.LOOP);
        tokens.put("for", TokenType.FOR);
        tokens.put("if", TokenType.IF);
        tokens.put("else", TokenType.ELSE);
        tokens.put("reverse", TokenType.REVERSE);
        tokens.put("..", TokenType.RANGE);
        tokens.put("then", TokenType.THEN);
        tokens.put("\n", TokenType.NEW_LINE);

        tokens.put("integer", TokenType.DATA_TYPE_INTEGER);
        tokens.put("real", TokenType.DATA_TYPE_REAL);
        tokens.put("boolean", TokenType.DATA_TYPE_BOOLEAN);

        tokens.put("true", TokenType.BOOLEAN_LITERAL_TRUE);
        tokens.put("false", TokenType.BOOLEAN_LITERAL_FALSE);

        tokens.put("eof", TokenType.EOF);

        delimeters.add("(");
        delimeters.add(")");
        delimeters.add("{");
        delimeters.add("}");
        delimeters.add(".");
        delimeters.add(":");
        delimeters.add(":");
        delimeters.add("=");
        delimeters.add("<");
        delimeters.add(">");
        delimeters.add("+");
        delimeters.add("-");
        delimeters.add("*");
        delimeters.add("/");
        delimeters.add("%");
    }
}
