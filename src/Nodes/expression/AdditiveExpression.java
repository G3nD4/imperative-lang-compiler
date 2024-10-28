package Nodes.expression;

import Lexical_analyzer.TokenType;

import java.util.ArrayList;

public class AdditiveExpression extends Expression {
    public ArrayList<MultiplicativeExpression> operands;
    public TokenType operation;
    public TokenType returnType;

    public AdditiveExpression(ArrayList<MultiplicativeExpression> operands, MultiplicativeExpression rightOperand, TokenType operation, TokenType returnType) {
        this.operands = operands;
        this.operation = operation;
        this.returnType = returnType;
    }

}
