package Nodes.expression;

import Lexical_analyzer.TokenType;
import Nodes.primary.Primary;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class UnaryExpression extends Expression {
    public TokenType operation;
    public TokenType returnType;
    public Primary primary;

    public UnaryExpression(TokenType operation, Primary primary, TokenType returnType) {
        this.operation = operation;
        this.primary = primary;
        this.returnType = returnType;
    }

    public UnaryExpression(Primary primary) {
        this.primary = primary;
    }

    public static UnaryExpression parse(ParseTree tree, MyLangParser parser) {
        return null;
    }

    @Override
    public String toString() {
        return "UnaryExpression{" +
                "operation=" + operation +
                ", returnType=" + returnType +
                ", primary=" + primary +
                '}';
    }

    public TokenType getReturnType() {
        return returnType;
    }

    public void setReturnType(TokenType returnType) {
        this.returnType = returnType;
    }
}
