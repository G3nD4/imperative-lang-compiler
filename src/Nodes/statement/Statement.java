package Nodes.statement;

import Nodes.Interfaces.JasminConvertable;
import main.antlrTree.MyLangParser;
import main.TreeBuilder;
import org.antlr.v4.runtime.tree.ParseTree;

public abstract class Statement implements JasminConvertable {
    public static Statement parse(ParseTree tree, MyLangParser parser) {
        ParseTree child = tree.getChild(0);
        String ruleName = TreeBuilder.TreeToRule(child, parser);
        return switch (ruleName) {
            case "assignment" -> Assignment.parse(child, parser);
            case "routineCall" -> RoutineCallStatement.parse(child, parser);
            case "whileLoop" -> WhileLoop.parse(child, parser);
            case "forLoop" -> ForLoop.parse(child, parser);
            case "ifStatement" -> IfStatement.parse(child, parser);
            case "returnStatement" -> Return.parse(child, parser);
            case "printStatement" -> PrintStatement.parse(child, parser);
            default -> null;
        };
    }

    public abstract String toString(String indent);
}