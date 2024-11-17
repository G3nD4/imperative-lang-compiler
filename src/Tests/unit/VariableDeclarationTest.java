package Tests.unit;

import Nodes.Sign;
import Nodes.Type;
import Nodes.VariableDeclaration;
import Nodes.expression.Expression;
import Nodes.expression.UnaryExpression;
import Nodes.jasmine.CodeGenerator;
import Nodes.primary.BooleanLiteral;
import Nodes.primary.IntegerLiteral;
import Nodes.primary.ModifiablePrimary;
import Nodes.primary.RealLiteral;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class VariableDeclarationTest {
    @Test
    public void testInt() {
        final CodeGenerator generator = new CodeGenerator();
        final IntegerLiteral integerLiteral = new IntegerLiteral(5);
        final Expression expression = new UnaryExpression(Sign.PLUS, integerLiteral, Type.INTEGER);
        final VariableDeclaration declaration = new VariableDeclaration("a", Type.INTEGER, expression);
        declaration.generateCode(generator);
        String real = generator.getProgramText();
        Assertions.assertEquals("iconst_5\nistore_0\n", real);
        System.out.println(real);
    }

    @Test
    public void testReal() {
        final CodeGenerator generator = new CodeGenerator();
        final RealLiteral realLiteral = new RealLiteral(5.0);
        final Expression expression = new UnaryExpression(Sign.PLUS, realLiteral, Type.REAL);
        final VariableDeclaration declaration = new VariableDeclaration("a", Type.REAL, expression);
        declaration.generateCode(generator);
        String real = generator.getProgramText();
        Assertions.assertEquals("fconst_5.0\nfstore_0\n", real);
        System.out.println(real);
    }

    @Test
    public void testBoolean() {
        final CodeGenerator generator = new CodeGenerator();
        final BooleanLiteral booleanLiteral = new BooleanLiteral(Boolean.TRUE);
        final Expression expression = new UnaryExpression(Sign.PLUS, booleanLiteral, Type.BOOLEAN);
        final VariableDeclaration declaration = new VariableDeclaration("a", Type.BOOLEAN, expression);
        declaration.generateCode(generator);
        String real = generator.getProgramText();
        Assertions.assertEquals("iconst_1\nistore_0\n", real);
        System.out.println(real);
    }

    @Test
    public void testModifiablePrimary() {
        final CodeGenerator generator = new CodeGenerator();
        final BooleanLiteral booleanLiteral = new BooleanLiteral(Boolean.TRUE);
        final Expression modPrimExpression = new UnaryExpression(Sign.PLUS, booleanLiteral, Type.BOOLEAN);
        final VariableDeclaration modPrimDeclaration = new VariableDeclaration("a", Type.BOOLEAN, modPrimExpression);
        modPrimDeclaration.generateCode(generator);
        final ModifiablePrimary modifiablePrimary = new ModifiablePrimary(modPrimDeclaration.getIdentifier());

        final String firstVarDeclaration = generator.getProgramText();

        final Expression expression = new UnaryExpression(Sign.PLUS, modifiablePrimary, Type.BOOLEAN);
        final VariableDeclaration declaration = new VariableDeclaration("b", Type.BOOLEAN, expression);
        declaration.generateCode(generator);
        String real = generator.getProgramText();
        Assertions.assertEquals("iconst_1\nistore_0\niload_0\n\nistore_1\n\n", real);
        System.out.println(real);
    }
}
