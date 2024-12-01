package Nodes.User_defined;

import Nodes.Body;
import Nodes.Interfaces.JasminConvertable;
import Nodes.expression.Expression;
import Nodes.jasmine.CodeGenerator;
import Nodes.statement.Declarations.TypeDeclaration;
import Nodes.statement.Declarations.VariableDeclaration;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

public class RecordType implements JasminConvertable {
    public final String name;
    public final List<VariableDeclaration> fields;

    public static RecordType parse(ParseTree tree, MyLangParser parser) {
        String name = null;
        List<VariableDeclaration> fields = new ArrayList<>();

        for (int i = 0; i < tree.getChildCount(); i++) {
            ParseTree child = tree.getChild(i);

            if (child.getText().equals("record")) {
                continue;
            } else if (child instanceof MyLangParser.VariableDeclarationContext) {
                fields.add(VariableDeclaration.parse(child, parser));
            } else if (child.getText().equals("type")) {
                name = tree.getChild(i + 1).getText();
            } else if (child.getText().equals("end")) {
                break;
            }
        }

        if (name == null) {
            throw new RuntimeException("Record type must have a name.");
        }

        return new RecordType(name, fields);
    }

    public RecordType(String name, List<VariableDeclaration> fields) {
        this.name = name;
        this.fields = fields;
    }

//    @Override
//    public String toString(String indent) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(indent).append("Record Type: ").append(name).append("\n");
//        sb.append(indent).append("{\n");
//        IndentManager.goDown();
//        for (VariableDeclaration field : fields) {
//            sb.append(field.toString(IndentManager.getIndent()));
//        }
//        IndentManager.goUp();
//        sb.append(indent).append("}\n");
//        IndentManager.print(sb);
//        return "";
//    }

    @Override
    public void generateCode(CodeGenerator generator) {
//        generator.writeComment("Record Type: " + name);
//        for (VariableDeclaration field : fields) {
//            field.generateCode(generator);
//        }
    }
}