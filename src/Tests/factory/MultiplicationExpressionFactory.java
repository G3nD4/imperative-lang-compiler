package Tests.factory;

import Nodes.Operation;
import Nodes.Sign;
import Nodes.Type;
import Nodes.VariableDeclaration;
import Nodes.expression.Expression;
import Nodes.expression.MultiplicativeExpression;
import Nodes.expression.UnaryExpression;
import Nodes.primary.BooleanLiteral;
import Nodes.primary.IntegerLiteral;
import Nodes.primary.ModifiablePrimary;
import Nodes.primary.RealLiteral;

import java.util.ArrayList;

public class MultiplicationExpressionFactory {
    public static MultiplicativeExpression buildOneInteger(int value) {
        IntegerLiteral int1 = new IntegerLiteral(value);
        final UnaryExpression num1 = new UnaryExpression(Sign.PLUS, int1, Type.INTEGER);
        ArrayList<UnaryExpression> operands = new ArrayList<>();
        operands.add(num1);

        return new MultiplicativeExpression(operands, new ArrayList<>(), Type.INTEGER);
    }
    public static MultiplicativeExpression buildTwoIntegersMultiplication(int value1, int value2) {
        IntegerLiteral int1 = new IntegerLiteral(value1);
        final UnaryExpression num1 = new UnaryExpression(Sign.PLUS, int1, Type.INTEGER);
        IntegerLiteral int2 = new IntegerLiteral(value2);
        final UnaryExpression num2 = new UnaryExpression(Sign.PLUS, int2, Type.INTEGER);

        ArrayList<UnaryExpression> operands = new ArrayList<>();
        operands.add(num1);
        operands.add(num2);

        ArrayList<Operation> operations = new ArrayList<>();
        operations.add(Operation.MULTIPLY);

        return new MultiplicativeExpression(operands, operations, Type.INTEGER);
    }

    public static MultiplicativeExpression buildTwoIntegersDivision(int value1, int value2) {
        IntegerLiteral int1 = new IntegerLiteral(value1);
        final UnaryExpression num1 = new UnaryExpression(Sign.PLUS, int1, Type.INTEGER);
        IntegerLiteral int2 = new IntegerLiteral(value2);
        final UnaryExpression num2 = new UnaryExpression(Sign.PLUS, int2, Type.INTEGER);

        ArrayList<UnaryExpression> operands = new ArrayList<>();
        operands.add(num1);
        operands.add(num2);

        ArrayList<Operation> operations = new ArrayList<>();
        operations.add(Operation.DIVIDE);

        return new MultiplicativeExpression(operands, operations, Type.REAL);
    }

    public static MultiplicativeExpression buildTwoIntegersModule(int value1, int value2) {
        IntegerLiteral int1 = new IntegerLiteral(value1);
        final UnaryExpression num1 = new UnaryExpression(Sign.PLUS, int1, Type.INTEGER);
        IntegerLiteral int2 = new IntegerLiteral(value2);
        final UnaryExpression num2 = new UnaryExpression(Sign.PLUS, int2, Type.INTEGER);

        ArrayList<UnaryExpression> operands = new ArrayList<>();
        operands.add(num1);
        operands.add(num2);

        ArrayList<Operation> operations = new ArrayList<>();
        operations.add(Operation.MODULO);

        return new MultiplicativeExpression(operands, operations, Type.INTEGER);
    }
}
