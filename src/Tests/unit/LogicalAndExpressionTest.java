package Tests.unit;

import Nodes.Enums.Sign;
import Nodes.Enums.Type;
import Nodes.statement.Declarations.VariableDeclaration;
import Nodes.expression.*;
import Nodes.jasmine.CodeGenerator;
import Nodes.primary.ModifiablePrimary;
import Tests.factory.AdditiveExpressionFactory;
import Tests.factory.VariableDeclarationFactory;
import org.junit.Test;

import java.util.ArrayList;

public class LogicalAndExpressionTest {
    @Test
    public void testModPrimAndTrue() {
        final CodeGenerator generator = new CodeGenerator();

        final VariableDeclaration variableDeclaration = VariableDeclarationFactory.buildBooleanVarDeclaration("a", true);
        variableDeclaration.generateCode(generator);
        final ModifiablePrimary modifiablePrimary = new ModifiablePrimary(variableDeclaration.getIdentifier());
        final UnaryExpression unaryExpression = new UnaryExpression(Sign.PLUS, modifiablePrimary, Type.IDENTIFIER);
        final ArrayList<UnaryExpression> unaries = new ArrayList<>();
        unaries.add(unaryExpression);
        final MultiplicativeExpression multiplicativeExpression = new MultiplicativeExpression(unaries, new ArrayList<>(), Type.BOOLEAN);
        final ArrayList<MultiplicativeExpression> multExpression = new ArrayList<>();
        multExpression.add(multiplicativeExpression);
        final AdditiveExpression additiveExpression = new AdditiveExpression(multExpression, new ArrayList<>(), Type.BOOLEAN);
        final ArrayList<AdditiveExpression> additiveExpressions1 = new ArrayList<>();
        additiveExpressions1.add(additiveExpression);
        final RelationalExpression relationalExpression1 = new RelationalExpression(additiveExpressions1, new ArrayList<>(), Type.BOOLEAN);
        final ArrayList<RelationalExpression> relationalExpressions1 = new ArrayList<>();
        relationalExpressions1.add(relationalExpression1);
        final EqualityExpression equalityExpression2 = new EqualityExpression(relationalExpressions1, new ArrayList<>(), Type.BOOLEAN);

        final AdditiveExpression first = AdditiveExpressionFactory.buildOneBoolean(true);
        final ArrayList<AdditiveExpression> additiveExpressions = new ArrayList<>();
        additiveExpressions.add(first);
        final RelationalExpression relationalExpression = new RelationalExpression(additiveExpressions, new ArrayList<>(), Type.BOOLEAN);
        final ArrayList<RelationalExpression> relationalExpressions = new ArrayList<>();
        relationalExpressions.add(relationalExpression);
        final EqualityExpression equalityExpression = new EqualityExpression(relationalExpressions, new ArrayList<>(), Type.BOOLEAN);

        final ArrayList<EqualityExpression> andOperands = new ArrayList<>();
        andOperands.add(equalityExpression);
        andOperands.add(equalityExpression2);
        final LogicalAndExpression logicalAndExpression = new LogicalAndExpression(andOperands, Type.BOOLEAN);

        logicalAndExpression.generateCode(generator);

        final String result = generator.getProgramText().trim();

        System.out.println(result);
    }
}
