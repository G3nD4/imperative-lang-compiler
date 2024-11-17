package Tests.unit;

import Nodes.Operation;
import Nodes.Sign;
import Nodes.Type;
import Nodes.expression.AdditiveExpression;
import Nodes.expression.MultiplicativeExpression;
import Nodes.expression.UnaryExpression;
import Nodes.jasmine.CodeGenerator;
import Nodes.primary.IntegerLiteral;
import Nodes.primary.RealLiteral;
import org.junit.Test;

import java.util.ArrayList;

public class AdditiveExpressionTest {
    @Test
    public void addTwoMultiplicativeExpressions() {
        final CodeGenerator generator = new CodeGenerator();

        final MultiplicativeExpression first = getFirstMultiplicativeExpr(generator);
        final MultiplicativeExpression second = getSecondMultiplicativeExpr(generator);

        final ArrayList<MultiplicativeExpression> operands = new ArrayList<>();
        operands.add(first);
        operands.add(second);

        final ArrayList<Sign> operations = new ArrayList<>();
        operations.add(Sign.PLUS);

        final AdditiveExpression additiveExpression = new AdditiveExpression(operands, operations, Type.REAL);
        additiveExpression.generateCode(generator);

        final String result = generator.getProgramText().trim();

        System.out.println(result);
    }

    private MultiplicativeExpression getFirstMultiplicativeExpr(CodeGenerator generator) {

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
        final MultiplicativeExpression result = new MultiplicativeExpression(operands, operations, Type.REAL);

        return result;
    }

    private MultiplicativeExpression getSecondMultiplicativeExpr(CodeGenerator generator) {

        // UnaryExpressions
        UnaryExpression num1 = new UnaryExpression(Sign.PLUS, new RealLiteral(9.5), Type.REAL);
        UnaryExpression num2 = new UnaryExpression(Sign.PLUS, new IntegerLiteral(8), Type.INTEGER);

        // Operands
        ArrayList<UnaryExpression> operands = new ArrayList<>();
        operands.add(num1);
        operands.add(num2);

        // Operations
        ArrayList<Operation> operations = new ArrayList<>();
        operations.add(Operation.MODULO);

        // MultiplicativeExpression
        final MultiplicativeExpression result = new MultiplicativeExpression(operands, operations, Type.REAL);

        return result;
    }
}
