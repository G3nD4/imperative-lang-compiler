package Tests.unit;

import Helpers.RemoveRedundantEnters;
import Nodes.*;
import Nodes.expression.*;
import Nodes.jasmine.CodeGenerator;
import Nodes.primary.IntegerLiteral;
import Nodes.primary.ModifiablePrimary;
import Nodes.statement.ForLoop;
import Tests.factory.MultiplicationExpressionFactory;
import Tests.factory.VariableDeclarationFactory;
import org.junit.Test;

import java.util.ArrayList;

public class ForLoopTest {

    @Test
    public void testForLoopWithAverage() {
        // This test creates:
        // var sum := 0
        // var avg := 0.0
        // for i in 1..10 loop
        //     sum := sum + i
        //     avg := sum / i
        // end
        CodeGenerator generator = new CodeGenerator();

        // Declare initial variables
        VariableDeclaration sumVar = VariableDeclarationFactory.buildIntegerVarDeclaration("sum", 0);
        VariableDeclaration avgVar = VariableDeclarationFactory.buildRealVarDeclaration("avg", 0.0);

        sumVar.generateCode(generator);
        avgVar.generateCode(generator);

        // Create range expressions
        UnaryExpression startExpr = new UnaryExpression(Sign.PLUS, new IntegerLiteral(1), Type.INTEGER);
        UnaryExpression endExpr = new UnaryExpression(Sign.PLUS, new IntegerLiteral(10), Type.INTEGER);
        Range range = new Range(startExpr, endExpr);

        // Create body with multiple statements
        Body body = new Body();

        // 1. sum := sum + i
        ModifiablePrimary sumRef = new ModifiablePrimary("sum");
        ModifiablePrimary iRef = new ModifiablePrimary("i");

        ArrayList<MultiplicativeExpression> sumOperands = new ArrayList<>();
        sumOperands.add(MultiplicationExpressionFactory.buildFromUnary(new UnaryExpression(Sign.PLUS, sumRef, Type.INTEGER)));
        sumOperands.add(MultiplicationExpressionFactory.buildFromUnary(new UnaryExpression(Sign.PLUS, iRef, Type.INTEGER)));

        ArrayList<Sign> sumOps = new ArrayList<>();
        sumOps.add(Sign.PLUS);

        AdditiveExpression sumAddition = new AdditiveExpression(sumOperands, sumOps, Type.INTEGER);
        Assignment sumAssignment = new Assignment(sumRef, sumAddition);
        body.addStatement(sumAssignment);

        // 2. avg := sum / i (will be converted to real)
        ModifiablePrimary sumDivRef = new ModifiablePrimary("sum");
        ModifiablePrimary iDivRef = new ModifiablePrimary("i");

        ArrayList<UnaryExpression> divOperands = new ArrayList<>();
        divOperands.add(new UnaryExpression(Sign.PLUS, sumDivRef, Type.INTEGER));
        divOperands.add(new UnaryExpression(Sign.PLUS, iDivRef, Type.INTEGER));

        ArrayList<Operation> divOps = new ArrayList<>();
        divOps.add(Operation.DIVIDE);

        MultiplicativeExpression division = new MultiplicativeExpression(divOperands, divOps, Type.REAL);
        final ModifiablePrimary avg = new ModifiablePrimary("avg");
        Assignment avgAssignment = new Assignment(avg, division);
        body.addStatement(avgAssignment);

        // Create and generate for loop
        ForLoop forLoop = new ForLoop("i", range, false, body);
        forLoop.generateCode(generator);

        String jasminCode = generator.getProgramText();
        jasminCode = RemoveRedundantEnters.remove(jasminCode);
        System.out.println(jasminCode);
    }
}
