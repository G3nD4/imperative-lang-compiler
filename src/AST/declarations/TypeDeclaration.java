package AST.declarations;

import Lexical_analyzer.TokenType;

public class TypeDeclaration {

    public String identifier;
    public TokenType type;

    public TypeDeclaration(String identifier, TokenType type) {
        this.identifier = identifier;
        this.type = type;
    }
}
