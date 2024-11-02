package Nodes.primary;

import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public abstract interface Primary {
    public static Primary parse(ParseTree tree, MyLangParser parser) {
        return null;
    }
}
