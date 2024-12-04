package Nodes.statement;

import Nodes.Enums.Type;
import Nodes.Interfaces.JasminConvertable;
import Nodes.expression.Expression;
import Nodes.jasmine.CodeGenerator;
import main.antlrTree.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class PrintStatement extends Statement implements JasminConvertable {
    private final Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    public static PrintStatement parse(ParseTree tree, MyLangParser parser) {
        ParseTree expressionNode = tree.getChild(2);
        Expression expression = Expression.parse(expressionNode, parser);
        return new PrintStatement(expression);
    }

    @Override
    public String toString(String indent) {
        return indent + "print(" + expression.toString() + ");";
    }


    @Override
    public void generateCode(CodeGenerator generator) {
        if (expression == null) {
            return;
        }
        if (expression.type == Type.INTEGER || expression.type == Type.BOOLEAN) {
            generator.writeToProgram("getstatic java/lang/System/out Ljava/io/PrintStream;\n");
            expression.generateCode(generator);
            generator.writeToProgram("invokevirtual java/io/PrintStream/println(I)V\n");
        } else if (expression.type == Type.REAL) {
            generator.writeToProgram("getstatic java/lang/System/out Ljava/io/PrintStream;\n");
            expression.generateCode(generator);
            generator.writeToProgram("invokevirtual java/io/PrintStream/println(F)V\n");
        } else {
            System.out.println("Type " + expression.type + " is not supported. [PrintStatement]");
            System.exit(1);
        }
    }
}