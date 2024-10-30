package Nodes;

import Nodes.statement.Statement;
import main.MyLangParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

public class VariableDeclaration extends Declaration {
    private String identifier;
    private String type;
    private String value;

    public VariableDeclaration(String identifier, String type, String initialValue) {
        this.identifier = identifier;
        this.type = type;
        this.value = initialValue;
    }

    public static VariableDeclaration parse(ParseTree tree, MyLangParser parser) {
        VariableDeclaration var = new VariableDeclaration("", "", "");

        var.identifier = tree.getChild(1).getText();
        var.type = !tree.getChild(2).getText().equals(":") ? null : tree.getChild(3).getText();
        int valueIndex = var.type == null ? 3 : 5;
        var.value = tree.getChild(valueIndex) == null ? null : tree.getChild(valueIndex).getText();

        // SEMANTIC ERROR CHECK
        if (var.value == null && var.type == null) {
            System.out.println("Variable must have at least type or value!");
            System.out.println("Error occurred on line: " + ((ParserRuleContext) tree).start.getLine() + " at character: " + ((ParserRuleContext) tree).start.getCharPositionInLine());
            System.exit(1);
        }

        return var;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "VariableDeclaration{" +
                "identifier=" + identifier +
                ", type='" + type + '\'' +
                ", initialValues=" + value +
                '}';
    }
}
