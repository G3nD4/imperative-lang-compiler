package Tests.unit;

import Nodes.Enums.Operation;
import Nodes.Enums.Sign;
import Nodes.Enums.Type;
import Nodes.statement.Declarations.VariableDeclaration;
import Nodes.expression.MultiplicativeExpression;
import Nodes.expression.UnaryExpression;
import Nodes.jasmine.CodeGenerator;
import Nodes.primary.IntegerLiteral;
import Nodes.primary.ModifiablePrimary;
import Nodes.primary.RealLiteral;
import Tests.factory.VariableDeclarationFactory;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class MultiplicativeExpressionTest {
    @Test
    public void twoOperands() {
        final CodeGenerator generator = new CodeGenerator();

        // UnaryExpressions
        UnaryExpression num1 = new UnaryExpression(Sign.PLUS, new IntegerLiteral(2), Type.INTEGER);
        UnaryExpression num2 = new UnaryExpression(Sign.PLUS, new IntegerLiteral(3), Type.INTEGER);

        // Operands
        ArrayList<UnaryExpression> operands = new ArrayList<>();
        operands.add(num1);
        operands.add(num2);

        // Operations
        ArrayList<Operation> operations = new ArrayList<>();
        operations.add(Operation.MULTIPLY);

        // MultiplicativeExpression
        MultiplicativeExpression result = new MultiplicativeExpression(operands, operations, Type.INTEGER);
        result.generateCode(generator);

        // Test
        String real = generator.getProgramText();
        System.out.println(real);
        Assertions.assertEquals("ldc 2\nldc 3\nimul\n", real);
    }

    @Test
    public void threeOperands() {
        final CodeGenerator generator = new CodeGenerator();

        // UnaryExpressions
        UnaryExpression num1 = new UnaryExpression(Sign.PLUS, new IntegerLiteral(2), Type.INTEGER);
        UnaryExpression num2 = new UnaryExpression(Sign.PLUS, new IntegerLiteral(3), Type.INTEGER);
        UnaryExpression num3 = new UnaryExpression(Sign.PLUS, new IntegerLiteral(4), Type.INTEGER);

        // Operands
        ArrayList<UnaryExpression> operands = new ArrayList<>();
        operands.add(num1);
        operands.add(num2);
        operands.add(num3);

        // Operations
        ArrayList<Operation> operations = new ArrayList<>();
        operations.add(Operation.MULTIPLY);
        operations.add(Operation.MULTIPLY);

        // MultiplicativeExpression
        MultiplicativeExpression result = new MultiplicativeExpression(operands, operations, Type.INTEGER);
        result.generateCode(generator);

        // Test
        String real = generator.getProgramText();
        System.out.println(real);
        Assertions.assertEquals("ldc 2\nldc 3\nimul\nldc 4\nimul\n", real);
    }

    @Test
    public void threeOperandsReturnTypeReal() {
        final CodeGenerator generator = new CodeGenerator();

        // UnaryExpressions
        UnaryExpression num1 = new UnaryExpression(Sign.PLUS, new IntegerLiteral(2), Type.INTEGER);
        UnaryExpression num2 = new UnaryExpression(Sign.PLUS, new RealLiteral(3.0), Type.REAL);
        UnaryExpression num3 = new UnaryExpression(Sign.PLUS, new IntegerLiteral(4), Type.INTEGER);

        // Operands
        ArrayList<UnaryExpression> operands = new ArrayList<>();
        operands.add(num1);
        operands.add(num2);
        operands.add(num3);

        // Operations
        ArrayList<Operation> operations = new ArrayList<>();
        operations.add(Operation.MULTIPLY);
        operations.add(Operation.MODULO);

        // MultiplicativeExpression
        MultiplicativeExpression result = new MultiplicativeExpression(operands, operations, Type.REAL);
        result.generateCode(generator);

        // Test
        String real = generator.getProgramText().trim();
        System.out.println(real);
        Assertions.assertEquals("""
                ldc 2
                i2f
                ldc 3.0
                fmul
                ldc 4
                i2f
                frem""", real);
    }

    @Test
    public void threeIntegerOperandsReturnTypeReal() {
        final CodeGenerator generator = new CodeGenerator();

        // UnaryExpressions
        UnaryExpression num1 = new UnaryExpression(Sign.PLUS, new IntegerLiteral(2), Type.INTEGER);
        UnaryExpression num2 = new UnaryExpression(Sign.MINUS, new IntegerLiteral(8), Type.INTEGER);
        UnaryExpression num3 = new UnaryExpression(Sign.PLUS, new IntegerLiteral(4), Type.INTEGER);

        // Operands
        ArrayList<UnaryExpression> operands = new ArrayList<>();
        operands.add(num1);
        operands.add(num2);
        operands.add(num3);

        // Operations
        ArrayList<Operation> operations = new ArrayList<>();
        operations.add(Operation.MULTIPLY);
        operations.add(Operation.DIVIDE);

        // MultiplicativeExpression
        MultiplicativeExpression result = new MultiplicativeExpression(operands, operations, Type.REAL);
        result.generateCode(generator);

        // Test
        String real = generator.getProgramText();
        System.out.println(real);
        Assertions.assertEquals("""
                ldc 2
                i2f
                ldc 8
                ineg
                i2f
                fmul
                ldc 4
                i2f
                fdiv""", real);
    }

    @Test
    public void threeIntegerOperandsReturnTypeRealWithModifiablePrimary() {
        final CodeGenerator generator = new CodeGenerator();

        final String identifier = "a";
        final VariableDeclaration modPrimDeclaration = VariableDeclarationFactory.buildRealVarDeclaration(identifier, 5.0);
        modPrimDeclaration.generateCode(generator);
        final ModifiablePrimary modifiablePrimary = new ModifiablePrimary(identifier);

        // UnaryExpressions
        UnaryExpression num1 = new UnaryExpression(Sign.PLUS, modifiablePrimary, Type.REAL);
        UnaryExpression num2 = new UnaryExpression(Sign.MINUS, new IntegerLiteral(8), Type.INTEGER);
        UnaryExpression num3 = new UnaryExpression(Sign.PLUS, new IntegerLiteral(4), Type.INTEGER);

        // Operands
        ArrayList<UnaryExpression> operands = new ArrayList<>();
        operands.add(num1);
        operands.add(num2);
        operands.add(num3);

        // Operations
        ArrayList<Operation> operations = new ArrayList<>();
        operations.add(Operation.MULTIPLY);
        operations.add(Operation.DIVIDE);

        // MultiplicativeExpression
        MultiplicativeExpression result = new MultiplicativeExpression(operands, operations, Type.REAL);
        result.generateCode(generator);

        // Test
        String real = generator.getProgramText().trim();
        System.out.println(real);
        Assertions.assertEquals("""
                ldc 5.0
                fstore 0
                fload 0
                
                
                ldc 8
                ineg
                i2f
                fmul
                ldc 4
                i2f
                fdiv""", real);
    }
}
