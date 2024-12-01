package Tests.unit;

import Helpers.AddIndents;
import Nodes.Program;
import Nodes.jasmine.CodeGenerator;
import main.InternalNode;
import main.MyLangLexer;
import main.MyLangParser;
import main.TreeBuilder;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

public class ProgramTest {
    private String testAdel = "/home/adel/Desktop/compilers-project/imperative-lang-compiler/src/Tests/Test_files/";
    private String testKamil = "C:\\Users\\HUAWEI\\IdeaProjects\\imperative-lang-compiler\\src\\Tests\\Test_files\\";

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
    public void testAssignment() throws IOException, InterruptedException {
        runTest("Assignment.txt");
    }

    @Test
    public void testComplexAssign() throws IOException, InterruptedException {
        runTest("complex_assign.txt");
    }

    @Test
    public void testComplexCode() throws IOException, InterruptedException {
        runTest("complex_code.txt");
    }

    @Test
    public void testForLoop() throws IOException, InterruptedException {
        runTest("ForLoop.txt");
    }

    @Test
    public void testIfStatement() throws IOException, InterruptedException {
        runTest("IfStatement.txt");
    }

    @Test
    public void testNestedIf() throws IOException, InterruptedException {
        runTest("NestedIf.txt");
    }

    @Test
    public void testPriorityTest() throws IOException, InterruptedException {
        runTest("priority_test.txt");
    }

    @Test
    public void testRoutine() throws IOException, InterruptedException {
        runTest("Routine.txt");
    }

    @Test
    public void testRoutinRout() throws IOException, InterruptedException {
        runTest("RoutInRout.txt");
    }

    @Test
    public void testTypeDeclaration() throws IOException, InterruptedException {
        runTest("TypeDeclaration.txt");
    }

    @Test
    public void testVariableDeclaration() throws IOException, InterruptedException {
        runTest("VariableDeclaration.txt");
    }

    @Test
    public void testWhileLoop() throws IOException, InterruptedException {
        runTest("WhileLoop.txt");
    }

    @Test
    public void testRoutineCallPrimary() throws IOException {
        runTest("RoutineCallPrimary");
    }
}
