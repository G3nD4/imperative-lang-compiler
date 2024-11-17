package Tests.unit;

import Nodes.Sign;
import Nodes.Type;
import Nodes.VariableDeclaration;
import Nodes.expression.UnaryExpression;
import Nodes.jasmine.CodeGenerator;
import Nodes.primary.BooleanLiteral;
import Nodes.primary.IntegerLiteral;
import Nodes.primary.ModifiablePrimary;
import Nodes.primary.RealLiteral;
import Tests.factory.VariableDeclarationFactory;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class UnaryExpressionTest {
    @Test
    public void testNotBoolean() {
        final CodeGenerator generator = new CodeGenerator();
        final UnaryExpression expression = new UnaryExpression(Sign.NOT, new BooleanLiteral(false), Type.BOOLEAN);
        final String result = expression.getLoadCode(generator);
        generator.writeToProgram(result);

        System.out.println(generator.getProgramText());

        Assertions.assertEquals("ldc 0\nineg\n", result);
    }

    @Test
    public void testMinusReal() {
        final CodeGenerator generator = new CodeGenerator();
        final UnaryExpression expression = new UnaryExpression(Sign.MINUS, new RealLiteral(777.0), Type.REAL);
        final String result = expression.getLoadCode(generator);
        generator.writeToProgram(result);

        Assertions.assertEquals("ldc 777.0\nfneg\n", result);

        System.out.println(generator.getProgramText());
    }

    @Test
    public void testMinusInteger() {
        final CodeGenerator generator = new CodeGenerator();
        final UnaryExpression expression = new UnaryExpression(Sign.MINUS, new IntegerLiteral(777), Type.INTEGER);
        final String result = expression.getLoadCode(generator);
        generator.writeToProgram(result);

        Assertions.assertEquals("ldc 777\nineg\n", result);

        System.out.println(generator.getProgramText());
    }

    @Test
    public void testModifiablePrimaryRealMinus() {
        final String identifier = "a";
        final CodeGenerator generator = new CodeGenerator();
        final VariableDeclaration modPrimVarDecBool = VariableDeclarationFactory.buildRealVarDeclaration(identifier, 32.0);
        modPrimVarDecBool.generateCode(generator);
        final ModifiablePrimary modPrim = new ModifiablePrimary(identifier);

        final UnaryExpression unaryExpression = new UnaryExpression(Sign.MINUS, modPrim, Type.IDENTIFIER);
        final String loadCode = unaryExpression.getLoadCode(generator);
        generator.writeToProgram(loadCode);

        final String result = generator.getProgramText().trim();

        System.out.println(result);

        Assertions.assertEquals("ldc 32.0\nfstore_0\nfload_0\n\nfneg", result);
    }

    @Test
    public void testMultipleModifiablePrimaryRealMinus() {
        final CodeGenerator generator = new CodeGenerator();

        final VariableDeclaration modPrimVarDecBoolRedundant = VariableDeclarationFactory.buildRealVarDeclaration("redundant", 100.0);
        modPrimVarDecBoolRedundant.generateCode(generator);

        final String identifier = "a";
        final VariableDeclaration modPrimVarDecBool = VariableDeclarationFactory.buildRealVarDeclaration(identifier, 32.0);
        modPrimVarDecBool.generateCode(generator);
        final ModifiablePrimary modPrim = new ModifiablePrimary(identifier);

        final UnaryExpression unaryExpression = new UnaryExpression(Sign.MINUS, modPrim, Type.IDENTIFIER);
        final String loadCode = unaryExpression.getLoadCode(generator);
        generator.writeToProgram(loadCode);

        final String result = generator.getProgramText().trim();

        System.out.println(result);

        Assertions.assertEquals("ldc 100.0\nfstore_0\nldc 32.0\nfstore_1\nfload_1\n\nfneg", result);
    }
}
