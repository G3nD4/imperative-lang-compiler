package Lexical_analyzer;

public class RealToken extends Token {
    private double realValue;

    public RealToken(Span span, TokenType type, int id, String name, double realValue) {
        super(span, type, id, name);
        this.realValue = realValue;
    }

    @Override
    public String toString() {
        return "RealToken:\n\t" + "[" + name + "]" + "\n\ttype: " + type + "\n\tid: " + id + "\n\tline number: " + span.getLineNumber() + "\n---------------------------\n";
    }

    @Override
    public Token copy() {
        return new RealToken(span, type, id, name, realValue);
    }
}
