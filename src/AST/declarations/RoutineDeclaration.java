package AST.declarations;

import AST.Expression;
import Lexical_analyzer.TokenType;
import java.util.List;

public class RoutineDeclaration {

    private String identifier;
    private TokenType returnType;
    private Parameters parameters;
    private List<Expression> body;

    public RoutineDeclaration(String identifier, TokenType returnType, Parameters parameters, List<Expression> body) {
        this.parameters = parameters;
        this.identifier = identifier;
        this.returnType = returnType;
        this.body = body;
    }

}
