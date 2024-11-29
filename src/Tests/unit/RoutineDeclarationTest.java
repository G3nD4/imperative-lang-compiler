package Tests.unit;

import Nodes.Program;
import Nodes.statement.Declarations.RoutineDeclaration;
import Nodes.jasmine.CodeGenerator;
import main.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import java.io.IOException;

public class RoutineDeclarationTest {
    @Test
    public void testSimpleRoutineDeclaration() throws IOException {
//        CharStream charStream = CharStreams.fromFileName(
//                "/home/adel/Desktop/compilers-project/imperative-lang-compiler/src/Tests/Test_files/Routine.txt");

        CharStream charStream = CharStreams.fromFileName(
                "C:\\Users\\HUAWEI\\IdeaProjects\\imperative-lang-compiler\\src\\Tests\\Test_files\\Routine.txt");

        MyLangLexer myLangLexer = new MyLangLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(myLangLexer);
        MyLangParser myLangParser = new MyLangParser(tokenStream);

        MyLangParser.ProgramContext context = myLangParser.program();

        InternalNode root = (InternalNode)TreeBuilder.buildTree(context.children.getFirst(), myLangParser);

        final RoutineDeclaration routineDeclaration = ((Program)root.data).getRoutineDeclarations().getFirst();
//        final RoutineDeclaration routineDeclaration2 = ((Program)root.data).getRoutineDeclarations().get(1);

        final CodeGenerator generator = new CodeGenerator();

        routineDeclaration.generateCode(generator);
//        routineDeclaration2.generateCode(generator);
        final String result = generator.getProgramText();

        System.out.println(result);
    }
}
