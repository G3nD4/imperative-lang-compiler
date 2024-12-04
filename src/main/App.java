package main;

import ErrorsFounder.KeywordsListener;
import Helpers.AddIndents;
import Nodes.Program;
import Nodes.jasmine.CodeGenerator;
import Optimizers.ConstantFoldingOptimizer;
import Optimizers.RoutineDeadCodeOptimizer;
import main.antlrTree.MyLangLexer;
import main.antlrTree.MyLangParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.util.*;

public class App {

    public static void main(String[] args) {
        try {
//            CharStream charStream = CharStreams.fromFileName(
//                    "/home/adel/Desktop/compilers-project/imperative-lang-compiler/src/Tests/Test_files/ForLoop.txt");

            CharStream charStream = CharStreams.fromFileName(
                    "C:\\Users\\HUAWEI\\IdeaProjects\\imperative-lang-compiler\\src\\Tests\\Test_files\\Routine.txt");
            MyLangLexer myLangLexer = new MyLangLexer(charStream);
            CommonTokenStream tokenStream = new CommonTokenStream(myLangLexer);
            MyLangParser myLangParser = new MyLangParser(tokenStream);
            MyLangParser.ProgramContext context = myLangParser.program();

            // Check for unexpected "return" (not inside a routine)
            KeywordsListener keywordUsageListener = new KeywordsListener();
            ParseTreeWalker.DEFAULT.walk(keywordUsageListener, context);

            // Parsing ANTLR tree
            TreeNode root = TreeBuilder.buildTree(context.children.getFirst(), myLangParser);

            // Optimizations
            RoutineDeadCodeOptimizer routineDeadCodeOptimizer = new RoutineDeadCodeOptimizer();
            routineDeadCodeOptimizer.optimize((Program) ((InternalNode) root).data);

            ConstantFoldingOptimizer constantFoldingOptimizer = new ConstantFoldingOptimizer();
            constantFoldingOptimizer.optimize((Program) ((InternalNode) root).data);

            // Code generation
            final Program program = (Program) ((InternalNode) root).data;
            final CodeGenerator generator = new CodeGenerator();
            String result = program.generateInstructions(generator);
            result = AddIndents.add(result);
            System.out.println(result);
            System.out.println("\n\n\nDone");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



