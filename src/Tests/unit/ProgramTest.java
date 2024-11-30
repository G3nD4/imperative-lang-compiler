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
    @Test
    public void testSimpleProgram() throws IOException, InterruptedException {
        CharStream charStream = CharStreams.fromFileName(
                "/home/adel/Desktop/compilers-project/imperative-lang-compiler/src/Tests/Test_files/complex_assign.txt");
//        CharStream charStream = CharStreams.fromFileName(
//                "C:\\Users\\HUAWEI\\IdeaProjects\\imperative-lang-compiler\\src\\Tests\\Test_files\\Routine.txt");

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
        try (FileWriter writer = new FileWriter("MyProgram.j")) {
            writer.write(result);
        }


        // Call Jasmin to assemble the .j file into a .class file
        Process jasminProcess = new ProcessBuilder("java", "-jar", "C:\\Users\\HUAWEI\\Downloads\\jasmin-2.4.jar", "SumProgram.j")
                .inheritIO() // Ensures Jasmin output is visible
                .start();
        int jasminExitCode = jasminProcess.waitFor();
        if (jasminExitCode != 0) {
            throw new RuntimeException("Jasmin failed to assemble the .j file.");
        }

        // Execute the generated MyProgram.class file
        Process javaProcess = new ProcessBuilder("java", "SumProgram")
                .inheritIO() // Ensures program output is visible
                .start();
        int javaExitCode = javaProcess.waitFor();
        if (javaExitCode != 0) {
            throw new RuntimeException("Execution of MyProgram.class failed.");
        }
    }
}
