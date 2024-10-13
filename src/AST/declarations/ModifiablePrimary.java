package AST.declarations;
import java.util.List;

import AST.Expression;
import Lexical_analyzer.TokenType;

public class ModifiablePrimary {
    private String identifier;
    private String anotherIdentifier;
    private List<Expression> body;

public ModifiablePrimary (String identifier, String anotherIdentifier, List<Expression> body) {
    this.identifier = identifier;
    this.anotherIdentifier = anotherIdentifier;
    this.body = body; 
}

}
