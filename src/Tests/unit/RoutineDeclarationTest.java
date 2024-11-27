package Tests.unit;

import Nodes.Program;
import Nodes.RoutineDeclarationStatement;
import Nodes.jasmine.CodeGenerator;
import main.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

public class RoutineDeclarationTest {
    @Test
    public void testSimpleRoutineDeclaration() throws IOException {
        CharStream charStream = CharStreams.fromFileName(
                "/home/adel/Desktop/compilers-project/imperative-lang-compiler/src/Tests/Test_files/Routine.txt");

        MyLangLexer myLangLexer = new MyLangLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(myLangLexer);
        MyLangParser myLangParser = new MyLangParser(tokenStream);

        MyLangParser.ProgramContext context = myLangParser.program();

        InternalNode root = (InternalNode)TreeBuilder.buildTree(context.children.getFirst(), myLangParser);

        final RoutineDeclarationStatement routineDeclaration = ((Program)root.data).getRoutineDeclarations().getFirst();
        final RoutineDeclarationStatement routineDeclaration2 = ((Program)root.data).getRoutineDeclarations().get(1);

        final CodeGenerator generator = new CodeGenerator();

        routineDeclaration.generateCode(generator);
        routineDeclaration2.generateCode(generator);
        final String result = generator.getProgramText();

        System.out.println(result);
    }
}
