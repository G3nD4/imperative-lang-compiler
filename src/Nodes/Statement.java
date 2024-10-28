package Nodes;

import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public abstract class Statement {
    public static Statement parse(String ruleName, ParseTree tree, MyLangParser parser) {
        switch (ruleName) {
            case "assignment":
                return Assignment.parse(tree.getChild(0), parser);
            default:
                return null;
        }
    }
}