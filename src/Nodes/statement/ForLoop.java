package Nodes.statement;

import Nodes.*;
import Nodes.Enums.Sign;
import Nodes.Enums.Type;
import Nodes.expression.AdditiveExpression;
import Nodes.expression.MultiplicativeExpression;
import Nodes.expression.UnaryExpression;
import Nodes.jasmine.CodeGenerator;
import Nodes.primary.IntegerLiteral;
import Nodes.primary.ModifiablePrimary;
import Nodes.statement.Declarations.VariableDeclaration;
import Tests.factory.MultiplicationExpressionFactory;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class ForLoop extends Statement {
    public final String loopVariable;
    public final Range range;
    public final boolean reverse;
    public final Body body;

    public ForLoop(String loopVariable, Range range, boolean reverse, Body body) {
        this.loopVariable = loopVariable;
        this.range = range;
        this.reverse = reverse;
        this.body = body;
    }

    @Override
    public String toString(String indent) {
        IndentManager.print("For Loop:");
        IndentManager.goDown();
        IndentManager.print("loop variable: " + loopVariable);
        IndentManager.print(range.toString(""));
        IndentManager.print("reverse: " + reverse);
        IndentManager.print(body.toString(""));
        IndentManager.goUp();
        return "";
    }

    public static ForLoop parse(ParseTree tree, MyLangParser parser) {
        String loopVariable = String.valueOf(tree.getChild(1));

        // FIXME: ASSUMING that for variable is ALWAYS integer
        Program.registerVariable(loopVariable, Type.INTEGER);

        // If "reverse" exists, "range" and "loop" shifts to 1
        boolean reverseExists = String.valueOf(tree.getChild(3)).equals("reverse");
        int rangeChildIndex = reverseExists ? 4 : 3;
        int bodyChildIndex = reverseExists ? 7 : 6;
        Range range = Range.parse(tree.getChild(rangeChildIndex), parser);
        Body body = Body.parse(tree.getChild(bodyChildIndex), parser);

        return new ForLoop(loopVariable, range, reverseExists, body);
    }

    @Override
    public void generateCode(CodeGenerator generator) {
        // Declare iterator variable
        range.getStart().generateCode(generator);
        VariableDeclaration iterVar = new VariableDeclaration(loopVariable, Type.INTEGER, range.getStart());
        iterVar.generateCode(generator);

        // Generate unique labels
        String loopStart = generator.generateUniqueLabel("fl_start");
        String loopEnd = generator.generateUniqueLabel("fl_end");

        // Write loop start label
        generator.writeLabel(loopStart);

        // Generate condition code (i <= end or i >= end for reverse)
//        ModifiablePrimary iterRef = new ModifiablePrimary(loopVariable);
        range.getEnd().generateCode(generator);

        // Load iterator value
        generator.writeToProgram("iload " + generator.getVariable(loopVariable).getIndex());

        // Compare iterator with end value
        if (reverse) {
            // Normal loop: continue if i <= end
            generator.writeToProgram("if_icmpgt " + loopEnd); // Exit if i > end
        } else {
            // Reverse loop: continue if i >= end
            generator.writeToProgram("if_icmplt " + loopEnd); // Exit if i < end
        }


        // Generate body code
        body.generateCode(generator);

        // Generate iterator increment/decrement
        ModifiablePrimary iter = new ModifiablePrimary(loopVariable, generator);
        IntegerLiteral one = new IntegerLiteral(1);
        UnaryExpression oneExpr = new UnaryExpression(
                Sign.PLUS,
                one,
                Type.INTEGER
        );

        // Create i = i + 1 or i = i - 1
        final ArrayList<MultiplicativeExpression> multiplicativeExpressionArrayList1 = new ArrayList<>();
        multiplicativeExpressionArrayList1.add(MultiplicationExpressionFactory.buildFromUnary(new UnaryExpression(Sign.PLUS, iter, Type.INTEGER)));
        multiplicativeExpressionArrayList1.add(MultiplicationExpressionFactory.buildFromUnary(oneExpr));
        final ArrayList<Sign> operation = new ArrayList<>();
        operation.add(reverse ? Sign.MINUS : Sign.PLUS);
        Assignment increment = new Assignment(
                iter,
                new AdditiveExpression(
                        multiplicativeExpressionArrayList1,
                        operation,
                        Type.INTEGER
                )
        );
        increment.generateCode(generator);

        // Jump back to start
        generator.writeToProgram("goto " + loopStart);

        // Write end label
        generator.writeLabel(loopEnd);
    }
}
