package main;

import Nodes.Enums.Operation;
import Nodes.Enums.Type;
import Nodes.Program;
import Nodes.expression.MultiplicativeExpression;
import Nodes.expression.UnaryExpression;
import Nodes.primary.IntegerLiteral;
import Nodes.primary.ModifiablePrimary;
import Nodes.primary.Primary;
import Nodes.primary.RealLiteral;
import Nodes.statement.Declarations.Declaration;
import Nodes.statement.Declarations.VariableDeclaration;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Objects;

public class ConstantFoldingOptimizer implements Optimizer {
    @Override
    public void optimize(Program program) {
        for (Declaration declaration : program.getDeclarations()) {
            if (declaration instanceof VariableDeclaration variableDeclaration) {
                if (variableDeclaration.getExpression() instanceof MultiplicativeExpression multiplicativeExpression
                        && multiplicativeExpression.getOperands().size() > 1) {
                    foldConstant(multiplicativeExpression);
                    variableDeclaration.setExpression(multiplicativeExpression);
                }
            }
        }
    }

    private void foldConstant(MultiplicativeExpression multiplicativeExpression) {
        ArrayList<Operation> operations = multiplicativeExpression.getOperations();
        ArrayList<UnaryExpression> operands = multiplicativeExpression.getOperands();

        ArrayList<UnaryExpression> newOperands = new ArrayList<>();
        UnaryExpression cumulativeUnary = null;
        for (int i = 0; i < operands.size(); i++) {
            UnaryExpression unaryExpression = operands.get(i);
            if (!(unaryExpression.primary instanceof ModifiablePrimary)) {
                if (cumulativeUnary == null) {
                    cumulativeUnary = unaryExpression;
                    if (unaryExpression.primary instanceof IntegerLiteral unExprPrimary) {
                        // Need to change type of cumulativeUnary to RealLiteral
                        RealLiteral realLiteral = new RealLiteral((unExprPrimary.getValue()).doubleValue());
                        cumulativeUnary = new UnaryExpression(unaryExpression.sign, realLiteral, Type.REAL);
                    }
                } else {
                    // Perform operation for cumulativeUnary unaryExpr
                    switch (operations.get(i - 1)) {
                        case MULTIPLY:
                            cumulativeUnary.setNumericalLiteralValue(cumulativeUnary.getNumericalLiteralValue().doubleValue()
                                    * unaryExpression.getNumericalLiteralValue().doubleValue());
                            break;
                        case DIVIDE:
                            cumulativeUnary.setNumericalLiteralValue(cumulativeUnary.getNumericalLiteralValue().doubleValue()
                                    / unaryExpression.getNumericalLiteralValue().doubleValue());
                            break;
                        case MODULO:
                            cumulativeUnary.setNumericalLiteralValue(cumulativeUnary.getNumericalLiteralValue().doubleValue()
                                    % unaryExpression.getNumericalLiteralValue().doubleValue());
                            break;
                    }
                }
            } else {
                return;
            }
        }

        if (multiplicativeExpression.type == Type.INTEGER) {
            if (cumulativeUnary.primary instanceof RealLiteral realPrim) {
                IntegerLiteral realLiteral = new IntegerLiteral((realPrim.getValue()).intValue());
                assert cumulativeUnary != null;
                cumulativeUnary = new UnaryExpression(cumulativeUnary.sign, realLiteral, cumulativeUnary.type);
                newOperands.add(cumulativeUnary);
            }
        } else {
            newOperands.add(cumulativeUnary);
        }
        multiplicativeExpression.setOperands(newOperands);
        multiplicativeExpression.setOperations(new ArrayList<>());
    }
}
