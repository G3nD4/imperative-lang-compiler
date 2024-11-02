package Nodes.statement;

import Nodes.Assignment;
import main.MyLangParser;
import main.TreeBuilder;
import org.antlr.v4.runtime.tree.ParseTree;

public abstract class Statement {
    public static Statement parse(ParseTree tree, MyLangParser parser) {
        String ruleName = TreeBuilder.TreeToRule(tree.getChild(0), parser);
        switch (ruleName) {
            case "assignment":
                return Assignment.parse(tree.getChild(0), parser);
            default:
                return null;
        }
    }
}