package Nodes.User_defined;

import Nodes.Interfaces.JasminConvertable;
import Nodes.expression.Expression;
import Nodes.jasmine.CodeGenerator;
import Nodes.statement.Declarations.TypeDeclaration;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class ArrayType implements JasminConvertable {
    public final String elementType;
    public final Expression size;

    public static ArrayType parse(ParseTree tree, MyLangParser parser) {
        String elementType = null;
        Expression size = null;

        for (int i = 0; i < tree.getChildCount(); i++) {
            ParseTree child = tree.getChild(i);

            if (child.getText().equals("array")) {
                continue;
            } else if (child.getText().equals("[")) {
                if (i + 1 < tree.getChildCount() && !tree.getChild(i + 1).getText().equals("]")) {
                    size = Expression.parse(tree.getChild(i + 1), parser);
                }
            } else if (child.getText().equals("]")) {
                continue;
            } else {
                elementType = child.getText();
            }
        }

        if (elementType == null) {
            throw new RuntimeException("Array type must have an element type.");
        }

        return new ArrayType(elementType, size);
    }

    public ArrayType(String elementType, Expression size) {
        this.elementType = elementType;
        this.size = size;
    }

//    @Override
//    public String toString(String indent) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(indent).append("Array Type: ").append(elementType);
//        if (size != null) {
//            sb.append(" [").append(size.toString("")).append("]");
//        } else {
//            sb.append(" [unspecified size]");
//        }
//        sb.append("\n");
//        return sb.toString();
//    }

    @Override
    public void generateCode(CodeGenerator generator) {
//        generator.writeComment("Array Type: " + elementType);
//        if (size != null) {
//            generator.writeComment("Array Size: " + size.toString(""));
    }
}