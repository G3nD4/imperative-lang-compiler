package AST.declarations;

public class ParameterDeclaration {

    private String identifier;
    private TokenType type;

    public ParameterDeclaration(String identifier, TokenType type) {
        this.identifier = identifier;
        this.type = type;
    }
}
