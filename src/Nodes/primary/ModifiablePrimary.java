package Nodes.primary;

import Nodes.statement.Statement;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class ModifiablePrimary extends Primary {

    public ModifiablePrimary(String identifier) {
        this.identifier = identifier;
    }

    String identifier;

    public String toString(String indent) {
        IndentManager.print("Modifiable Primary identifier: " + identifier);
        return "";
    }

    public static ModifiablePrimary parse(ParseTree tree, MyLangParser parser) {
        return new ModifiablePrimary(tree.getChild(0).getText());
    }
}
