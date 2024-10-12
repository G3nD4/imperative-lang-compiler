package AST.declarations;

import AST.Expression;
import Lexical_analyzer.TokenType;

public class VariableDeclaration {

    public String identifier;
    public TokenType type;
    public Expression expression;

    public VariableDeclaration(String identifier, TokenType type, Expression expression) {
        this.identifier = identifier;
        this.type = type;
        this.expression = expression;
    }
}
