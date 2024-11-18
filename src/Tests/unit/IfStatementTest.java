package Tests.unit;

import Lexical_analyzer.TokenType;
import Nodes.*;
import Nodes.Assignment;
import Nodes.expression.*;
import Nodes.jasmine.CodeGenerator;
import Nodes.primary.BooleanLiteral;
import Nodes.primary.IntegerLiteral;
import Nodes.primary.ModifiablePrimary;
import Nodes.statement.IfStatement;
import Tests.factory.MultiplicationExpressionFactory;
import Tests.factory.VariableDeclarationFactory;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class IfStatementTest {

    @Test
    public void testSimpleIf() {
        CodeGenerator generator = new CodeGenerator();

        // var x := true
        VariableDeclaration xDecl = VariableDeclarationFactory.buildBooleanVarDeclaration("x", true);
        ModifiablePrimary xModPrim = new ModifiablePrimary("x");
        xDecl.generateCode(generator);

        // if x then
        //     x := false
        // end
        Body ifBody = new Body();
        Assignment assignment = new Assignment(xModPrim, new UnaryExpression(Sign.PLUS, new BooleanLiteral(false), Type.BOOLEAN));
        ifBody.addStatement(assignment);

        IfStatement ifStatement = new IfStatement(
                new UnaryExpression(Sign.PLUS, new ModifiablePrimary("x"), Type.BOOLEAN),
                ifBody,
                new ArrayList<>(),
                new ArrayList<>(),
                null
        );

        ifStatement.generateCode(generator);

        String result = generator.getProgramText().trim();
        System.out.println(result);
    }

    @Test
    public void testIfElseIf() {
        CodeGenerator generator = new CodeGenerator();

        // var x := 5
        VariableDeclaration xDecl = VariableDeclarationFactory.buildIntegerVarDeclaration("x", 5);
        xDecl.generateCode(generator);
        ModifiablePrimary xModPrim = new ModifiablePrimary("x");

        // Create bodies
        Body ifBody = new Body();
        ifBody.addStatement(new Assignment(xModPrim, new UnaryExpression(Sign.PLUS, new IntegerLiteral(1), Type.INTEGER)));

        Body elseIfBody = new Body();
        elseIfBody.addStatement(new Assignment(xModPrim, new UnaryExpression(Sign.PLUS, new IntegerLiteral(2), Type.INTEGER)));

        ArrayList<Expression> elseIfConditions = new ArrayList<>();
        ArrayList<Body> elseIfBodies = new ArrayList<>();

        // x > 3
        final MultiplicativeExpression xModPrimMult = MultiplicationExpressionFactory.buildFromUnary(new UnaryExpression(Sign.PLUS, xModPrim, Type.INTEGER));
        ArrayList<MultiplicativeExpression> operands1 = new ArrayList<>();
        operands1.add(xModPrimMult);
        final MultiplicativeExpression intLitModPrim = MultiplicationExpressionFactory.buildFromUnary(new UnaryExpression(Sign.PLUS, new IntegerLiteral(3), Type.INTEGER));
        ArrayList<MultiplicativeExpression> operands2 = new ArrayList<>();
        operands2.add(intLitModPrim);

        ArrayList<AdditiveExpression> operands = new ArrayList<>();
        operands.add(new AdditiveExpression(operands1, new ArrayList<>(), Type.INTEGER));
        operands.add(new AdditiveExpression(operands2, new ArrayList<>(), Type.INTEGER));
        ArrayList<TokenType> ops = new ArrayList<>();
        ops.add(TokenType.GREATER_THAN);
        Expression condition = new RelationalExpression(operands, ops, Type.BOOLEAN);

        elseIfConditions.add(condition);
        elseIfBodies.add(elseIfBody);

        IfStatement ifStatement = new IfStatement(
                condition,  // same condition for main if
                ifBody,
                elseIfConditions,
                elseIfBodies,
                null
        );

        ifStatement.generateCode(generator);

        String result = generator.getProgramText().trim();
        System.out.println(result);
    }
}
