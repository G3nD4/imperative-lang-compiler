
public enum TokenType {
    // Keywords
    VAR, TYPE, ROUTINE, IS, END, WHILE, LOOP, FOR, IF, ELSE, REVERSE, BOOLEAN_LITERAL_TRUE, BOOLEAN_LITERAL_FALSE, RANGE, THEN, NEW_LINE,
    // Data types
    DATA_TYPE_INTEGER, DATA_TYPE_REAL, DATA_TYPE_BOOLEAN,
    // Operators
    PLUS, MINUS, MULTIPLY, DIVIDE, MOD, ASSIGN, LESS_THAN, LESS_EQUAL, GREATER_THAN, GREATER_EQUAL, EQUAL, NOT_EQUAL,
    AND, OR, XOR, NOT,
    // Identifiers
    IDENTIFIER,
    // Delimiters
    LEFT_PAREN, RIGHT_PAREN, LEFT_BRACKET, RIGHT_BRACKET, COLON, DOT,
    // Special tokens
    EOF; // End of file
}
