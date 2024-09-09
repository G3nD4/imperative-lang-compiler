package main;

public class TokenFactory {

    public static Token realNumber(Span span, TokenType type, int id, String name, double realValue) {
        return new Token(span, type, id, name, realValue);
    }

    public static Token intNumber(Span span, TokenType type, int id, String name, int intValue) {
        return new Token(span, type, id, name, intValue);
    }
}
