package Tests.unit;

import Nodes.Enums.Sign;
import Nodes.Enums.Type;
import Nodes.statement.Declarations.VariableDeclaration;
import Nodes.expression.Expression;
import Nodes.expression.UnaryExpression;
import Nodes.jasmine.CodeGenerator;
import Nodes.primary.BooleanLiteral;
import Nodes.primary.ModifiablePrimary;
import Tests.factory.VariableDeclarationFactory;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class VariableDeclarationTest {
    @Test
    public void testInt() {
        final CodeGenerator generator = new CodeGenerator();
        final VariableDeclaration declaration = VariableDeclarationFactory.buildIntegerVarDeclaration("a", 5);
        declaration.generateCode(generator);
        String real = generator.getProgramText();
        Assertions.assertEquals("ldc 5\nistore 0\n", real);
        System.out.println(real);
    }

    @Test
    public void testReal() {
        final CodeGenerator generator = new CodeGenerator();
        final VariableDeclaration declaration = VariableDeclarationFactory.buildRealVarDeclaration("a", 5.0);
        declaration.generateCode(generator);
        String real = generator.getProgramText();
        Assertions.assertEquals("ldc 5.0\nfstore 0\n", real);
        System.out.println(real);
    }

    @Test
    public void testBoolean() {
        final CodeGenerator generator = new CodeGenerator();
        final VariableDeclaration declaration = VariableDeclarationFactory.buildBooleanVarDeclaration("a", true);
        declaration.generateCode(generator);
        String real = generator.getProgramText();
        Assertions.assertEquals("ldc 1\nistore 0\n", real);
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
        Assertions.assertEquals("ldc 1\nistore 0\niload 0\n\nistore 1\n\n", real);
        System.out.println(real);
    }
}
