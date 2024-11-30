package Tests.unit;

import Nodes.Program;
import Nodes.statement.Declarations.RoutineDeclaration;
import Nodes.jasmine.CodeGenerator;
import Nodes.statement.Declarations.VariableDeclaration;
import Nodes.statement.RoutineCallStatement;
import main.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import java.io.IOException;

public class RoutineCallTest {
    @Test
    public void simpleRoutineCall() throws IOException {
        CharStream charStream = CharStreams.fromFileName(
                "/home/adel/Desktop/compilers-project/imperative-lang-compiler/src/Tests/Test_files/Routine.txt");

//        CharStream charStream = CharStreams.fromFileName(
//                "C:\\Users\\HUAWEI\\IdeaProjects\\imperative-lang-compiler\\src\\Tests\\Test_files\\Routine.txt");

        MyLangLexer myLangLexer = new MyLangLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(myLangLexer);
        MyLangParser myLangParser = new MyLangParser(tokenStream);

        MyLangParser.ProgramContext context = myLangParser.program();

        InternalNode root = (InternalNode) TreeBuilder.buildTree(context.children.getFirst(), myLangParser);

        final VariableDeclaration x = (VariableDeclaration) ((Program) root.data).getDeclarations().getFirst();
        final RoutineDeclaration routineDeclaration = ((Program) root.data).getRoutineDeclarations().getFirst();
        final RoutineCallStatement routineCall = (RoutineCallStatement) ((Program) root.data).getStatements().getFirst();

        final CodeGenerator generator = new CodeGenerator();

        x.generateCode(generator);
        routineDeclaration.generateCode(generator);
        routineCall.generateCode(generator);
        final String result = generator.getProgramText();

        System.out.println(result);
    }
}
