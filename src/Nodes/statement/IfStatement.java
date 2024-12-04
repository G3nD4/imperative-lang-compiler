package Nodes.statement;

import Nodes.Body;
import Nodes.expression.Expression;
import Nodes.jasmine.CodeGenerator;
import main.IndentManager;
import main.antlrTree.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class IfStatement extends Statement {
    public final Expression condition;
    public final Body ifBody;
    public final ArrayList<Expression> ifElseConditions;
    public final ArrayList<Body> ifElseBodies;
    public final Body elseBody;

    public boolean hasElseBody() {
        return elseBody != null;
    }

    public static IfStatement parse(ParseTree tree, MyLangParser parser) {
        Expression condition = null;
        Body ifBody = null;
        Body elseBody = null;
        ArrayList<Expression> ifElseConditions = new ArrayList<>();
        ArrayList<Body> ifElseBodies = new ArrayList<>();


        int elseIfIndex = -100;
        for (int i = 0; i < tree.getChildCount(); ++i) {
            if (i == 1) {
                condition = Expression.parse(tree.getChild(1), parser);
            } else if (i == 4) {
                ifBody = Body.parse(tree.getChild(4), parser);
            } else if (tree.getChild(i).getText().equals("elseif")) {
                elseIfIndex = i;
            } else if (i == elseIfIndex + 1) {
                ifElseConditions.add(Expression.parse(tree.getChild(elseIfIndex + 1), parser));
            } else if (i == elseIfIndex + 4) {
                ifElseBodies.add(Body.parse(tree.getChild(elseIfIndex + 4), parser));
            } else if (tree.getChild(i).getText().equals("else")) {
                int elseBodyIndex = tree.getChildCount() - 3;
                elseBody = Body.parse(tree.getChild(elseBodyIndex), parser);
                break;
            }
        }

        return new IfStatement(condition, ifBody, ifElseConditions, ifElseBodies, elseBody);
    }

    public IfStatement(Expression condition, Body ifBody, ArrayList<Expression> ifElseConditions, ArrayList<Body> ifElseBodies, Body elseBody) {
        this.condition = condition;
        this.ifBody = ifBody;
        this.ifElseConditions = ifElseConditions == null ? new ArrayList<>() : ifElseConditions;
        this.ifElseBodies = ifElseBodies == null ? new ArrayList<>() : ifElseBodies;
        this.elseBody = elseBody;
    }

    @Override
    public String toString(String indent) {
        IndentManager.print("If Statement:");
        IndentManager.goDown();

        IndentManager.print("if condition: ");
        IndentManager.goDown();
        IndentManager.print(condition.toString(""));
        IndentManager.goUp();

        IndentManager.print("if body: " + ifBody.toString(""));
        if (!ifElseBodies.isEmpty()) {
            IndentManager.print("Additional conditions:");
            IndentManager.goDown();
            printAdditionalConditions();
            IndentManager.goUp();
        }
        if (elseBody != null) {
            IndentManager.print("else body:");
            IndentManager.goDown();
            IndentManager.print(elseBody.toString(""));
            IndentManager.goUp();
        }
        IndentManager.goUp();
        return "";
    }

    private void printAdditionalConditions() {
        for (int i = 0; i < ifElseConditions.size(); ++i) {
            IndentManager.print("condition: " + ifElseConditions.get(i).toString(""));
            IndentManager.print("body:");
            IndentManager.goDown();
            IndentManager.print(ifElseBodies.get(i).toString(""));
            IndentManager.goUp();
        }
    }

    @Override
    public void generateCode(CodeGenerator generator) {
        // Generate unique labels
        ArrayList<String> elseIfLabels = new ArrayList<>();
        for (int i = 0; i < ifElseConditions.size(); i++) {
            elseIfLabels.add(generator.generateUniqueLabel("elseif"));
        }
        String elseLabel = generator.generateUniqueLabel("else");
        String endLabel = generator.generateUniqueLabel("endif");

        // Main if condition
        condition.generateCode(generator);
        generator.writeToProgram("ifeq " + (ifElseConditions.isEmpty() ? elseLabel : elseIfLabels.getFirst()));

        // Main if body
        ifBody.generateCode(generator);
        generator.writeToProgram("goto " + endLabel);

        // Generate else-if blocks
        for (int i = 0; i < ifElseConditions.size(); i++) {
            generator.writeLabel(elseIfLabels.get(i));

            // Generate condition
            ifElseConditions.get(i).generateCode(generator);

            // Branch to next else-if or else
            String nextLabel = (i == ifElseConditions.size() - 1) ? elseLabel : elseIfLabels.get(i + 1);
            generator.writeToProgram("ifeq " + nextLabel);

            // Generate body
            ifElseBodies.get(i).generateCode(generator);
            generator.writeToProgram("goto " + endLabel);
        }

        // Generate else block
        generator.writeLabel(elseLabel);
        if (elseBody != null) {
            elseBody.generateCode(generator);
        }

        // End label
        generator.writeLabel(endLabel);
    }
}
