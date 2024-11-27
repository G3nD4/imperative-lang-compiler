package Tests.unit;

import Nodes.Program;
import Nodes.RoutineDeclarationStatement;
import Nodes.jasmine.CodeGenerator;
import main.InternalNode;
import main.MyLangLexer;
import main.MyLangParser;
import main.TreeBuilder;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import java.io.IOException;

public class ProgramTest {
    @Test
    public void testSimpleProgram() throws IOException {
        CharStream charStream = CharStreams.fromFileName(
                "/home/adel/Desktop/compilers-project/imperative-lang-compiler/src/Tests/Test_files/Routine.txt");

        MyLangLexer myLangLexer = new MyLangLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(myLangLexer);
        MyLangParser myLangParser = new MyLangParser(tokenStream);

        MyLangParser.ProgramContext context = myLangParser.program();

        InternalNode root = (InternalNode) TreeBuilder.buildTree(context.getChild(0), myLangParser);

        final Program program = (Program)root.data;

        final CodeGenerator generator = new CodeGenerator();

        final String result = program.generateInstructions(generator);

        System.out.println(result);
    }
}
