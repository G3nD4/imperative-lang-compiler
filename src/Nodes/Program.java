package Nodes;

import Nodes.Interfaces.JasmineInstructionsGeneratable;
import Nodes.jasmine.CodeGenerator;
import Nodes.statement.Declarations.Declaration;
import Nodes.statement.Declarations.RoutineDeclaration;
import Nodes.statement.Statement;
import main.IndentManager;
import main.MyLangParser;
import main.TreeBuilder;
import org.antlr.v4.codegen.model.decl.Decl;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import java.util.ArrayList;

public class Program implements JasmineInstructionsGeneratable {
    ArrayList<Statement> statements;
    ArrayList<RoutineDeclaration> routineDeclarations;
    ArrayList<Declaration> declarations;
    ArrayList<Object> orderedProgram;

    public Program(ArrayList<Statement> statements, ArrayList<RoutineDeclaration> routineDeclaration, ArrayList<Declaration> declarations, ArrayList<Object> orderedProgram) {
        this.statements = statements;
        this.routineDeclarations = routineDeclaration;
        this.declarations = declarations;
        this.orderedProgram = orderedProgram;
    }

    public ArrayList<RoutineDeclaration> getRoutineDeclarations() {
        return routineDeclarations;
    }

    public ArrayList<Statement> getStatements() {
        return statements;
    }

    public ArrayList<Declaration> getDeclarations() {
        return declarations;
    }


    public Program() {
        statements = new ArrayList<>();
        routineDeclarations = new ArrayList<>();
        declarations = new ArrayList<>();
        orderedProgram = new ArrayList<>();
    }

    public void addRoutine(RoutineDeclaration routine) {
        routineDeclarations.add(routine);
        orderedProgram.add(routine);
    }

    public void addStatement(Statement statement) {
        statements.add(statement);
        orderedProgram.add(statement);
    }

    public void addDeclaration(Declaration declaration) {
        declarations.add(declaration);
        orderedProgram.add(declaration);
    }

    @Override
    public String toString() {
        IndentManager.print("Program:");
        IndentManager.goDown();
        for (int i = 0; i < orderedProgram.size(); ++i) {
            if (orderedProgram.get(i) instanceof RoutineDeclaration) {
                IndentManager.print(((RoutineDeclaration) orderedProgram.get(i)).toString(""));
            }
            if (orderedProgram.get(i) instanceof Declaration) {
                IndentManager.print(((Declaration) orderedProgram.get(i)).toString(""));
            }
            if (orderedProgram.get(i) instanceof Statement) {
                IndentManager.print(((Statement) orderedProgram.get(i)).toString(""));
            }
        }

        return "";
    }

    public static Program parse(ParseTree tree, MyLangParser parser) {
        Program program = new Program();
        for (int i = 0; i < tree.getChildCount(); ++i) {
            String ruleName = "";
            try {
                final ParseTree parseTree = tree.getChild(i);
                if (!(parseTree instanceof TerminalNodeImpl)) {
                    ruleName = TreeBuilder.TreeToRule(parseTree, parser);
                }
            } catch (Exception e) {
                System.out.println("Exception occurred during parsing the Program:\n" + e);
                continue;
            }

            switch (ruleName) {
                case "routineDeclaration":
                    program.addRoutine(RoutineDeclaration.parse(tree.getChild(i), parser));
                    break;
                case "declaration":
                    program.addDeclaration(Declaration.parse(tree.getChild(i), parser));
                    break;
                case "statement":
                    program.addStatement(Statement.parse(tree.getChild(i), parser));
                    break;
            }
        }
        return program;
    }

    @Override
    public String generateInstructions(CodeGenerator generator) {
        for (Object object : orderedProgram) {
            if (object instanceof RoutineDeclaration) {
                ((RoutineDeclaration) object).generateCode(generator);
            }
        }

        final String body = generator.getProgramText();
        final String header = ".class public SumProgram\n.super java/lang/Object\n";
        final String constructor = ".method public <init>()V\n" +
                "    aload_0\n" +
                "    invokespecial java/lang/Object/<init>()V\n" +
                "    return\n" +
                ".end method\n";
        final String programCode = header + constructor + body;

        return programCode;
    }
}
