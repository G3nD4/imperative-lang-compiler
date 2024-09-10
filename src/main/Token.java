package main;

import java.util.ArrayList;

public class Token implements ICopyable<Token> {

    public Token(Span span, TokenType type, int id, String name) {
        this.span = span;
        this.type = type;
        this.id = id;
        this.name = name;
    }

    protected Span span;
    protected TokenType type;
    protected int id;
    protected String name;

    public String getName() {
        return name;
    }

    public TokenType getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public Span getSpan() {
        return span;
    }

    @Override
    public String toString() {
        return "Token:\n\t" + "[" + name + "]" + "\n\ttype: " + type + "\n\tid: " + id + "\n\tline number: " + span.getLineNumber() + "\n---------------------------\n";
    }

    public Token copyWith(String newName, TokenType type) {
        return new Token(span, type, id, newName);
    }

    public Token copyWith(String newName, double doubleVal) {
        return new Token(span, type, id, newName);
    }

    @Override
    public Token copy() {
        return new Token(span, type, id, name);
    }

}
