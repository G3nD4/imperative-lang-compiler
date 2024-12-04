package Tests.unit;

import Helpers.AddIndents;
import Nodes.Program;
import Nodes.jasmine.CodeGenerator;
import main.InternalNode;
import main.antlrTree.MyLangLexer;
import main.antlrTree.MyLangParser;
import main.TreeBuilder;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

public class BeautifulTest {
    private String testAdel = "/home/adel/Desktop/compilers-project/imperative-lang-compiler/src/Tests/Test_files/Beautiful_Tests/";
    private String testKamil = "C:\\Users\\HUAWEI\\IdeaProjects\\imperative-lang-compiler\\src\\Tests\\Test_files\\Beautiful_Tests\\";

    private void runTest(String fileName) throws IOException {
//        CharStream charStream = CharStreams.fromFileName(
//                testAdel + fileName);
        CharStream charStream = CharStreams.fromFileName(
                testKamil + fileName);

        MyLangLexer myLangLexer = new MyLangLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(myLangLexer);
        MyLangParser myLangParser = new MyLangParser(tokenStream);

        MyLangParser.ProgramContext context = myLangParser.program();

        InternalNode root = (InternalNode) TreeBuilder.buildTree(context.getChild(0), myLangParser);

        final Program program = (Program) root.data;

        final CodeGenerator generator = new CodeGenerator();

        String result = program.generateInstructions(generator);
        result = AddIndents.add(result);
        System.out.println(result);

        // Or any path that you want
        try (FileWriter writer = new FileWriter("SumProgram.j")) {
            writer.write(result);
        }
    }

    @Test
    public void testPrimeNumbers() throws IOException, InterruptedException {
        runTest("prime_numbers");
    }

    @Test
    public void testBasicTest() throws IOException, InterruptedException {
        runTest("basic_test");
    }

    @Test
    public void testMaxMin() throws IOException, InterruptedException {
        runTest("max_min");
    }
}
