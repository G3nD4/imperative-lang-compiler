package main;

import Nodes.Program;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.ParserRuleContext;
import main.antlrTree.MyLangParser;


public class TreeBuilder {

    public static TreeNode buildTree(ParseTree tree, MyLangParser parser) {
        if (tree.getChildCount() == 0) {
            return new InternalNode(tree.getText());
        } else {
            InternalNode node = new InternalNode(parser.getRuleNames()[((ParserRuleContext) tree).getRuleIndex()]);
            if (((ParserRuleContext) tree).parent.parent == null) {
                node.data = Program.parse(((ParserRuleContext) tree).parent, parser);
            }
            return node;
        }
    }

    public static String TreeToRule(ParseTree tree, MyLangParser parser) {
        final ParserRuleContext context = (ParserRuleContext)tree;
        return parser.getRuleNames()[((ParserRuleContext) tree).getRuleIndex()];
    }
}
