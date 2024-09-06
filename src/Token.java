
public class Token {

    public Token(Span span, TokenType type, int id, String name) {
        this.span = span;
        this.type = type;
        this.id = id;
        this.name = name;
    }

    public Token(Span span, TokenType type, int id, String name, double realValue) {
        this.span = span;
        this.type = type;
        this.id = id;
        this.name = name;
        this.realValue = realValue;
    }

    public Token(Span span, TokenType type, int id, String name, int intValue) {
        this.span = span;
        this.type = type;
        this.id = id;
        this.name = name;
        this.intValue = intValue;
    }

    public Token(Span span, TokenType type, int id, String name, int intValue, double realValue) {
        this.span = span;
        this.type = type;
        this.id = id;
        this.name = name;
        this.intValue = intValue;
        this.realValue = realValue;
    }

    private Span span;
    private TokenType type;
    private int id;
    private String name;

    private int intValue;
    private double realValue;

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
        return "Token:\n\t" + "[" + name + "]" + "\n\ttype: " + type + "\n\tid: " + id + "\n\tline number: " + span.getLineNumber() + "\n\tint_value: " + intValue + "\n\treal_value: " + realValue + "\n---------------------------\n";
    }

    public Token copyWith(String newName, TokenType type) {
        return new Token(span, type, id, newName, intValue, realValue);
    }

    public Token copyWith(String newName, double doubleVal) {
        return new Token(span, type, id, newName, intValue, realValue);
    }

    public Token copy() {
        return new Token(span, type, id, name, intValue, realValue);
    }

}
