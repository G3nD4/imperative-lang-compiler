package Nodes;

import Nodes.expression.Expression;
import Nodes.jasmine.CodeGenerator;
import Nodes.jasmine.VariableInfo;
import Nodes.primary.ModifiablePrimary;
import Nodes.statement.Statement;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

public class Assignment extends Statement {
    public ModifiablePrimary assignee;
    public Expression expression;

    public Assignment(ModifiablePrimary assignee, Expression expression) {
        this.assignee = assignee;
        this.expression = expression;
    }

    public static Assignment parse(ParseTree tree, MyLangParser parser) {
        final ModifiablePrimary assignee = ModifiablePrimary.parse(tree.getChild(0), parser);
        final Expression expression = Expression.parse(tree.getChild(2), parser);
        return new Assignment(assignee, expression);
    }

    @Override
    public String toString(String indent) {
        IndentManager.print("Assignment:");
        IndentManager.goDown();
        IndentManager.print("assignee:");
        IndentManager.goDown();
        IndentManager.print(assignee.toString(""));
        IndentManager.goUp();
        IndentManager.print(expression.toString(""));
        IndentManager.goUp();

        return "";
    }

    @Override
    public void generateCode(CodeGenerator generator) {
        // Generate code for the expression
        expression.generateCode(generator);

        // Get variable info to determine type and index
        final VariableInfo varInfo = generator.getVariable(assignee.identifier);

        // Store value based on type
        switch (varInfo.getType()) {
            case Type.BOOLEAN, Type.INTEGER ->
                    generator.writeToProgram("istore_" + varInfo.getIndex());
            case Type.REAL ->
                    generator.writeToProgram("fstore_" + varInfo.getIndex());
            default -> {
                System.out.println("Type " + varInfo.getType().name() + " is not supported!");
                System.exit(1);
            }
        }
    }
}
