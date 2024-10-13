package AST.declarations;

import Lexical_analyzer.Token;

public class Primary {
    private Token token;
    private ModifiablePrimary modifiablePrimary;

    public Primary (Token token, ModifiablePrimary modifiablePrimary) {
        this.token = token;
        this.modifiablePrimary = modifiablePrimary;
    }
}
