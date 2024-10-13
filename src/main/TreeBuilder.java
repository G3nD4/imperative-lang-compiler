package main;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.ParserRuleContext;

public class TreeBuilder {

    public static TreeNode buildTree(ParseTree tree, MyLangParser parser) {
        if (tree.getChildCount() == 0) {
            return new LeafNode(tree.getText());
        } else {
            InternalNode node = new InternalNode(parser.getRuleNames()[((ParserRuleContext) tree).getRuleIndex()]);

            for (int i = 0; i < tree.getChildCount(); i++) {
                node.addChild(buildTree(tree.getChild(i), parser));
            }

            return node;
        }
    }
}
