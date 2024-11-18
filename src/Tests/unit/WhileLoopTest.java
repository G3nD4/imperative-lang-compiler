package Tests.unit;

import Helpers.RemoveRedundantEnters;
import Helpers.RunJasmin;
import Nodes.*;
import Nodes.expression.*;
import Nodes.jasmine.CodeGenerator;
import Nodes.primary.IntegerLiteral;
import Nodes.primary.ModifiablePrimary;
import Nodes.statement.WhileLoop;
import Tests.factory.MultiplicationExpressionFactory;
import Tests.factory.VariableDeclarationFactory;
import org.junit.Test;
import Lexical_analyzer.TokenType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WhileLoopTest {
    @Test
    public void testSimpleWhileLoop() {
        CodeGenerator generator = new CodeGenerator();

        // Create counter variable i = 0
        IntegerLiteral zero = new IntegerLiteral(0);
        UnaryExpression initValue = new UnaryExpression(Sign.PLUS, zero, Type.INTEGER);
        VariableDeclaration counter = new VariableDeclaration("i", Type.INTEGER, initValue);

        // Create condition i < 5
        ModifiablePrimary counterVar = new ModifiablePrimary("i");
        UnaryExpression leftUnary = new UnaryExpression(Sign.PLUS, counterVar, Type.INTEGER);
        MultiplicativeExpression leftMult = MultiplicationExpressionFactory.buildFromModifiablePrimary(Sign.PLUS, counterVar, Type.INTEGER);
        final ArrayList<MultiplicativeExpression> a1 = new ArrayList<>();
        a1.add(leftMult);
        AdditiveExpression leftAdd = new AdditiveExpression(a1, new ArrayList<>(), Type.INTEGER);

        IntegerLiteral five = new IntegerLiteral(5);
        MultiplicativeExpression rightMult = MultiplicationExpressionFactory.buildOneInteger(5);
        final ArrayList<MultiplicativeExpression> a2 = new ArrayList<>();
        a2.add(rightMult);
        AdditiveExpression rightAdd = new AdditiveExpression(a2, new ArrayList<>(), Type.INTEGER);

        ArrayList<AdditiveExpression> operands = new ArrayList<>();
        operands.add(leftAdd);
        operands.add(rightAdd);

        ArrayList<TokenType> operations = new ArrayList<>();
        operations.add(TokenType.LESS_THAN);

        RelationalExpression condition = new RelationalExpression(operands, operations, Type.BOOLEAN);

        // Create empty body for now
        Body body = new Body();

        // Create i + 1 expression
        ModifiablePrimary iVar = new ModifiablePrimary("i");
        MultiplicativeExpression iMult = MultiplicationExpressionFactory.buildFromModifiablePrimary(Sign.PLUS, iVar, Type.INTEGER);

        MultiplicativeExpression oneMult = MultiplicationExpressionFactory.buildOneInteger(1);

        ArrayList<MultiplicativeExpression> addOperands = new ArrayList<>();
        addOperands.add(iMult);
        addOperands.add(oneMult);

        ArrayList<Sign> addOps = new ArrayList<>();
        addOps.add(Sign.PLUS);

        AdditiveExpression increment = new AdditiveExpression(addOperands, addOps, Type.INTEGER);

        // Create assignment i := i + 1
        Assignment incrementI = new Assignment(iVar, increment);
        body.addStatement(incrementI);

        WhileLoop whileLoop = new WhileLoop(condition, body);

        // Generate code
        counter.generateCode(generator);
        whileLoop.generateCode(generator);

        String jasminCode = generator.getProgramText();
        jasminCode = RemoveRedundantEnters.remove(jasminCode);
        System.out.println(jasminCode);
    }

    @Test
    public void testComplexWhileLoop() {
        // This test creates:
        // int sum = 0;
        // int i = 1;
        // real avg = 0.0;
        // while (i <= 10) {
        //     sum = sum + i;
        //     i = i + 1;
        //     avg = sum / i;
        // }
        // TODO: generated program:
        //
        //        public static void main(String[] var0) {
        //            int var100 = 0;
        //            int var1 = 1;
        //            float var2 = 0.0F;
        //
        //            for(boolean var10000 = !((float)var1 > (float)10); var10000; var10000 = !((float)var1 > (float)10)) {
        //                var100 += var1;
        //                ++var1;
        //                var2 = (float)var100 / (float)var1;
        //            }
        //
        //        }
        CodeGenerator generator = new CodeGenerator();

        // Declare initial variables
        VariableDeclaration sumVar = VariableDeclarationFactory.buildIntegerVarDeclaration("sum", 0);
        VariableDeclaration counterVar = VariableDeclarationFactory.buildIntegerVarDeclaration("i", 1);
        VariableDeclaration avgVar = VariableDeclarationFactory.buildRealVarDeclaration("avg", 0.0);
        ModifiablePrimary avgModPrim = new ModifiablePrimary("avg");

        sumVar.generateCode(generator);
        counterVar.generateCode(generator);
        avgVar.generateCode(generator);

        // Create condition i <= 10
        ModifiablePrimary iVar = new ModifiablePrimary("i");
        UnaryExpression leftUnary = new UnaryExpression(Sign.PLUS, iVar, Type.INTEGER);
        MultiplicativeExpression leftMult = MultiplicationExpressionFactory.buildFromUnary(leftUnary);
        final ArrayList<MultiplicativeExpression> a1 = new ArrayList<>();
        a1.add(leftMult);
        AdditiveExpression leftAdd = new AdditiveExpression(a1, new ArrayList<>(), Type.INTEGER);

        IntegerLiteral ten = new IntegerLiteral(10);
        UnaryExpression rightUnary = new UnaryExpression(Sign.PLUS, ten, Type.INTEGER);
        MultiplicativeExpression rightMult = MultiplicationExpressionFactory.buildFromUnary(rightUnary);
        final ArrayList<MultiplicativeExpression> a2 = new ArrayList<>();
        a2.add(rightMult);
        AdditiveExpression rightAdd = new AdditiveExpression(a2, new ArrayList<>(), Type.INTEGER);

        ArrayList<AdditiveExpression> operands = new ArrayList<>();
        operands.add(leftAdd);
        operands.add(rightAdd);

        ArrayList<TokenType> operations = new ArrayList<>();
        operations.add(TokenType.LESS_EQUAL);

        RelationalExpression condition = new RelationalExpression(operands, operations, Type.BOOLEAN);

        // Create body with multiple statements
        Body body = new Body();

        // 1. sum = sum + i
        ModifiablePrimary sumVarRef = new ModifiablePrimary("sum");
        UnaryExpression sumUnary = new UnaryExpression(Sign.PLUS, sumVarRef, Type.INTEGER);
        MultiplicativeExpression sumMult = MultiplicationExpressionFactory.buildFromModifiablePrimary(Sign.PLUS, sumVarRef, Type.INTEGER);

        ModifiablePrimary iVarRef = new ModifiablePrimary("i");
        UnaryExpression iUnary = new UnaryExpression(Sign.PLUS, iVarRef, Type.INTEGER);
        MultiplicativeExpression iMult = MultiplicationExpressionFactory.buildFromUnary(iUnary);

        ArrayList<MultiplicativeExpression> sumOperands = new ArrayList<>();
        sumOperands.add(sumMult);
        sumOperands.add(iMult);

        ArrayList<Sign> sumOps = new ArrayList<>();
        sumOps.add(Sign.PLUS);

        AdditiveExpression sumPlusI = new AdditiveExpression(sumOperands, sumOps, Type.INTEGER);
        Assignment sumAssignment = new Assignment(sumVarRef, sumPlusI);
        body.addStatement(sumAssignment);

        // 2. i = i + 1
        ModifiablePrimary iVarInc = new ModifiablePrimary("i");
        MultiplicativeExpression iMultInc = MultiplicationExpressionFactory.buildFromModifiablePrimary(Sign.PLUS, iVarInc, Type.INTEGER);

        IntegerLiteral one = new IntegerLiteral(1);
        MultiplicativeExpression oneMult = MultiplicationExpressionFactory.buildOneInteger(1);

        ArrayList<MultiplicativeExpression> incOperands = new ArrayList<>();
        incOperands.add(iMultInc);
        incOperands.add(oneMult);

        ArrayList<Sign> incOps = new ArrayList<>();
        incOps.add(Sign.PLUS);

        AdditiveExpression increment = new AdditiveExpression(incOperands, incOps, Type.INTEGER);
        Assignment incrementI = new Assignment(iVarInc, increment);
        body.addStatement(incrementI);

        // 3. avg = sum / i (will be converted to real)
        ModifiablePrimary sumVarDiv = new ModifiablePrimary("sum");
        UnaryExpression sumUnaryDiv = new UnaryExpression(Sign.PLUS, sumVarDiv, Type.INTEGER);

        ModifiablePrimary iVarDiv = new ModifiablePrimary("i");
        UnaryExpression iUnaryDiv = new UnaryExpression(Sign.PLUS, iVarDiv, Type.INTEGER);

        ArrayList<UnaryExpression> divOperands = new ArrayList<>();
        divOperands.add(sumUnaryDiv);
        divOperands.add(iUnaryDiv);

        ArrayList<Operation> divOps = new ArrayList<>();

        divOps.add(Operation.DIVIDE);

        MultiplicativeExpression division = new MultiplicativeExpression(divOperands, divOps, Type.REAL);
        final ArrayList<MultiplicativeExpression> a3 = new ArrayList<>();
        a3.add(division);
        Assignment avgAssignment = new Assignment(avgModPrim, new AdditiveExpression(a3, new ArrayList<>(), Type.REAL));
        body.addStatement(avgAssignment);

        // Create and generate while loop
        WhileLoop whileLoop = new WhileLoop(condition, body);
        whileLoop.generateCode(generator);

        String jasminCode = generator.getProgramText();
        jasminCode = RemoveRedundantEnters.remove(jasminCode);
        System.out.println(jasminCode);

        // Verify the generated code contains all necessary components
        String code = jasminCode.trim();
        System.out.println(code);
    }

}
