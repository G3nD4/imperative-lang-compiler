package Nodes.statement;

import Nodes.Enums.Type;
import Nodes.expression.Expression;
import Nodes.jasmine.CodeGenerator;
import Nodes.jasmine.VariableInfo;
import Nodes.primary.ModifiablePrimary;
import main.IndentManager;
import main.MyLangParser;
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

        final Type expressionType = expression.getType(generator);
        final Type assigneeType = this.assignee.getType(generator);

        writeTypeTransformInstructionIfNeeded(generator, assigneeType, expressionType);

        // Get variable info to determine type and index
        final VariableInfo varInfo = generator.getVariable(assignee.identifier);

        // Store value based on type
        switch (varInfo.getType()) {
            case Type.BOOLEAN, Type.INTEGER ->
                    generator.writeToProgram("istore " + varInfo.getIndex());
            case Type.REAL ->
                    generator.writeToProgram("fstore " + varInfo.getIndex());
            default -> {
                System.out.println("Type " + varInfo.getType().name() + " is not supported!");
                System.exit(1);
            }
        }
    }

    public void writeTypeTransformInstructionIfNeeded(CodeGenerator generator, Type assigneeType, Type expressionType) {
        if (expressionType == assigneeType) {
            return;
        }

        if (assigneeType == Type.INTEGER) {
            if (expressionType == Type.REAL) {
                generator.writeToProgram("invokestatic java/lang/Math/round(F)I");
            }
        } else if (assigneeType == Type.REAL) {
            generator.writeToProgram("i2f");
        } else if (assigneeType == Type.BOOLEAN) {
            if (expressionType == Type.INTEGER) {
                generator.writeToProgram("dup\niconst_1");
                final String trueLabel = generator.generateUniqueLabel("int_to_bool_success");
                final String falseLabel = generator.generateUniqueLabel("int_to_bool_failure");
                generator.writeToProgram("if_icmpeq " + trueLabel);
                generator.writeToProgram("dup\niconst_0");
                generator.writeToProgram("if_icmpeq " + falseLabel);
                generator.writeToProgram("getstatic java/lang/System/out Ljava/io/PrintStream;\n" +
                        "        ldc \"Error: Only 0 or 1 can be converted to boolean.\"\n" +
                        "        invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\n" +
                        "        ldc 1\n" +
                        "        invokestatic java/lang/System/exit(I)V");
                generator.writeLabel(trueLabel);
                generator.writeLabel(falseLabel);

            } else if (expressionType == Type.REAL) {
                System.out.println("Assignment REAL to BOOLEAN is illegal! Please, check [Assignment] class.");
                System.exit(1);
            }
        }
    }
}
