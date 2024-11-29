package Tests.factory;

import Nodes.Enums.Sign;
import Nodes.Enums.Type;
import Nodes.expression.AdditiveExpression;
import Nodes.expression.MultiplicativeExpression;

import java.util.ArrayList;

public class AdditiveExpressionFactory {
    public static AdditiveExpression buildOneIntegerAddition(int value) {
        ArrayList<MultiplicativeExpression> multiplicativeExpressions = new ArrayList<>();
        multiplicativeExpressions.add(MultiplicationExpressionFactory.buildOneInteger(value));

        return new AdditiveExpression(multiplicativeExpressions, new ArrayList<>(), Type.INTEGER);
    }

    public static AdditiveExpression buildOneBoolean(boolean value) {
        ArrayList<MultiplicativeExpression> multiplicativeExpressions = new ArrayList<>();
        multiplicativeExpressions.add(MultiplicationExpressionFactory.buildOneBoolean(value));

        return new AdditiveExpression(multiplicativeExpressions, new ArrayList<>(), Type.BOOLEAN);
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
