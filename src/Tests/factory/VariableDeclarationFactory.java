package Tests.factory;

import Nodes.Enums.Sign;
import Nodes.Enums.Type;
import Nodes.statement.Declarations.VariableDeclaration;
import Nodes.expression.Expression;
import Nodes.expression.UnaryExpression;
import Nodes.primary.BooleanLiteral;
import Nodes.primary.IntegerLiteral;
import Nodes.primary.RealLiteral;

public class VariableDeclarationFactory {
    public static VariableDeclaration buildRealVarDeclaration(String identifier, double value) {
        final RealLiteral realLiteral = new RealLiteral(value);
        final Expression expression = new UnaryExpression(Sign.PLUS, realLiteral, Type.REAL);
        final VariableDeclaration declaration = new VariableDeclaration(identifier, Type.REAL, expression);

        return declaration;
    }

    public static VariableDeclaration buildIntegerVarDeclaration(String identifier, int value) {
        final IntegerLiteral realLiteral = new IntegerLiteral(value);
        final Expression expression = new UnaryExpression(Sign.PLUS, realLiteral, Type.INTEGER);
        final VariableDeclaration declaration = new VariableDeclaration(identifier, Type.INTEGER, expression);

        return declaration;
    }

    public static VariableDeclaration buildBooleanVarDeclaration(String identifier, boolean value) {
        final BooleanLiteral realLiteral = new BooleanLiteral(value);
        final Expression expression = new UnaryExpression(Sign.PLUS, realLiteral, Type.BOOLEAN);
        final VariableDeclaration declaration = new VariableDeclaration(identifier, Type.BOOLEAN, expression);

        return declaration;
    }
}
