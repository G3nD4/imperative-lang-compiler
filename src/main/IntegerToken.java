package main;

public class IntegerToken extends Token {
    private int intValue;

    public IntegerToken(Span span, TokenType type, int id, String name, int intValue) {
        super(span, type, id, name);
        this.intValue = intValue;
    }

    @Override
    public String toString() {
        return "IntegerToken:\n\t" + "[" + name + "]" + "\n\ttype: " + type + "\n\tid: " + id + "\n\tline number: "
                + span.getLineNumber() + "\n---------------------------\n";
    }

    @Override
    public Token copy() {
        return new IntegerToken(span, type, id, name, intValue);
    }
}
