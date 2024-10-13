package main;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import main.MyLangParser.ProgramContext;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        try {
            CharStream charStream = CharStreams.fromFileName("/Users/nikitadrozdov/My Compiler/imperative-lang-compiler/src/Tests/Test_files/ForLoop.txt");

            MyLangLexer myLangLexer = new MyLangLexer(charStream);
            CommonTokenStream tokenStream = new CommonTokenStream(myLangLexer);

            MyLangParser myLangParser = new MyLangParser(tokenStream);

            ProgramContext context = myLangParser.program();

            TreeNode root = TreeBuilder.buildTree(context, myLangParser);
            
            printTree(root, 0);
            
            System.out.println(((InternalNode)((InternalNode) root).getChildren().get(0)).getRuleName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printTree(TreeNode node, int indent) {
        String indentString = "  ".repeat(indent);
        if (node.isLeaf()) {
            System.out.println(indentString + "Leaf: " + ((LeafNode) node).getValue());
        } else {
            InternalNode internalNode = (InternalNode) node;
            System.out.println(indentString + "Internal: " + internalNode.getRuleName());
            for (TreeNode child : internalNode.getChildren()) {
                printTree(child, indent + 1);
            }
        }
    }

}
