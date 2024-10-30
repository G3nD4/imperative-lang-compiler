package main;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.ParserRuleContext;
import Nodes.RoutineDeclarationStatement;

public class TreeBuilder {

    public static TreeNode buildTree(ParseTree tree, MyLangParser parser) {
        if (tree.getChildCount() == 0) {
            // TODO: parse
            return new InternalNode(tree.getText());
        } else {
            InternalNode node = new InternalNode(parser.getRuleNames()[((ParserRuleContext) tree).getRuleIndex()]);
//            if (((ParserRuleContext) tree).parent.parent == null) {
//                node.data = Program.parse(((ParserRuleContext) tree).parent, parser);
//            }
            switch(node.ruleName) {
                case "routineDeclaration":
                    node.data = RoutineDeclarationStatement.parse(tree, parser);
            }


            for (int i = 0; i < tree.getChildCount(); i++) {
                node.addChild(buildTree(tree.getChild(i), parser));
            }

            return node;
        }
    }
}
