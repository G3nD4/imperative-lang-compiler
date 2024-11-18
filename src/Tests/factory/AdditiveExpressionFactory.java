package Tests.factory;

import Nodes.Operation;
import Nodes.Sign;
import Nodes.Type;
import Nodes.VariableDeclaration;
import Nodes.expression.AdditiveExpression;
import Nodes.expression.Expression;
import Nodes.expression.MultiplicativeExpression;
import Nodes.expression.UnaryExpression;
import Nodes.primary.BooleanLiteral;
import Nodes.primary.IntegerLiteral;
import Nodes.primary.ModifiablePrimary;
import Nodes.primary.RealLiteral;

import java.util.ArrayList;

public class AdditiveExpressionFactory {
    public static AdditiveExpression buildOneIntegerAddition(int value) {
        ArrayList<MultiplicativeExpression> multiplicativeExpressions = new ArrayList<>();
        multiplicativeExpressions.add(MultiplicationExpressionFactory.buildOneInteger(value));

        return new AdditiveExpression(multiplicativeExpressions, new ArrayList<>(), Type.INTEGER);
    }

    public static AdditiveExpression buildTwoIntegersAddition(int value1, int value2) {
        ArrayList<MultiplicativeExpression> multiplicativeExpressions = new ArrayList<>();
        multiplicativeExpressions.add(MultiplicationExpressionFactory.buildOneInteger(value1));
        multiplicativeExpressions.add(MultiplicationExpressionFactory.buildOneInteger(value2));

        ArrayList<Sign> operations = new ArrayList<>();
        operations.add(Sign.PLUS);

        return new AdditiveExpression(multiplicativeExpressions, operations, Type.INTEGER);
    }
}
