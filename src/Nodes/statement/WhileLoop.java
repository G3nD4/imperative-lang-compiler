package Nodes.statement;

import Nodes.Body;
import Nodes.Interfaces.JasminConvertable;
import Nodes.expression.Expression;
import Nodes.jasmine.CodeGenerator;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class WhileLoop extends Statement implements JasminConvertable {
    public final Expression condition;
    public final Body body;

    @Override
    public String toString(String indent) {
        IndentManager.print("While Loop:");
        IndentManager.goDown();

        IndentManager.print("condition:");
        IndentManager.goDown();
        IndentManager.print(condition.toString(""));
        IndentManager.goUp();

        IndentManager.print(body.toString(""));

        IndentManager.goUp();
        return "";
    }

    public WhileLoop(Expression condition, Body body) {
        this.condition = condition;
        this.body = body;
    }

    public static WhileLoop parse(ParseTree tree, MyLangParser parser) {
        return new WhileLoop(Expression.parse(tree.getChild(1), parser), Body.parse(tree.getChild(4), parser));
    }

    @Override
    public void generateCode(CodeGenerator generator) {
        // Generate unique labels for loop
        String startLabel = generator.generateUniqueLabel("wl_start");
        String endLabel = generator.generateUniqueLabel("wl_end");

        // Write start label
        generator.writeLabel(startLabel);

        // Generate condition code
        condition.generateCode(generator);

        // If condition is false (0), jump to end
        generator.writeToProgram("ifeq " + endLabel);

        // Generate body code
        for (var statement : body.getOrderedDnS()) {
                ((JasminConvertable) statement).generateCode(generator);
        }

        // Jump back to start
        generator.writeToProgram("goto " + startLabel);

        // Write end label
        generator.writeLabel(endLabel);
    }
}
