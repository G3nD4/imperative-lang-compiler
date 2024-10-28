package main;

import Nodes.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        try {
            CharStream charStream = CharStreams.fromFileName(
                    "C:\\Users\\HUAWEI\\IdeaProjects\\imperative-lang-compiler\\src\\Tests\\Test_files\\Routine.txt");

            MyLangLexer myLangLexer = new MyLangLexer(charStream);
            CommonTokenStream tokenStream = new CommonTokenStream(myLangLexer);
            MyLangParser myLangParser = new MyLangParser(tokenStream);

            MyLangParser.ProgramContext context = myLangParser.program();

            Assignment assignment;
            TreeNode root = TreeBuilder.buildTree(context.children.getFirst(), myLangParser);

            System.out.println(root.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
