package Nodes.statement;

import Nodes.Interfaces.JasminConvertable;
import Nodes.expression.Expression;
import Nodes.jasmine.CodeGenerator;
import main.IndentManager;
import main.MyLangParser;
import main.TreeBuilder;
import org.antlr.v4.runtime.tree.ParseTree;

public class Return extends Statement {
    public final Expression expression;

    @Override
    public String toString(String indent) {
        IndentManager.print("Return Statement:");
        IndentManager.goDown();
        IndentManager.print("expression:");
        IndentManager.goDown();
        IndentManager.print(expression.toString(""));
        IndentManager.goUp();
        IndentManager.goUp();

        return "";
    }

    public Return(Expression expression) {
        this.expression = expression;
    }

    public static Return parse(ParseTree tree, MyLangParser parser) {
        /*
        Can be empty (just "return")
        or with expression (return expression)
        */
        if (tree.getChildCount() == 1) {
            return new Return(null);
        } else {
            return new Return(Expression.parse(tree.getChild(1), parser));
        }
    }

    @Override
    public void generateCode(CodeGenerator generator) {
        if (expression == null) {
            generator.writeToProgram("return");
            return;
        }

        expression.generateCode(generator);

        switch (expression.getType(generator)) {
            case INTEGER, BOOLEAN -> generator.writeToProgram("ireturn");
            case REAL -> generator.writeToProgram("freturn");
            default -> throw new IllegalStateException("Unsupported return type: " + expression.getType(generator));
        }
    }
}
