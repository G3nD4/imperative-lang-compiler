package Nodes;

import Nodes.statement.Statement;
import main.IndentManager;
import main.MyLangParser;
import main.TreeBuilder;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class Program {
    ArrayList<Statement> statements;
    ArrayList<RoutineDeclarationStatement> routineDeclarations;
    ArrayList<Declaration> declarations;
    ArrayList<Object> orderedProgram;

    public Program(ArrayList<Statement> statements, ArrayList<RoutineDeclarationStatement> routineDeclaration, ArrayList<Declaration> declarations, ArrayList<Object> orderedProgram) {
        this.statements = statements;
        this.routineDeclarations = routineDeclaration;
        this.declarations = declarations;
        this.orderedProgram = orderedProgram;
    }

    public Program() {
        statements = new ArrayList<>();
        routineDeclarations = new ArrayList<>();
        declarations = new ArrayList<>();
        orderedProgram = new ArrayList<>();
    }

    public void addRoutine(RoutineDeclarationStatement routine) {
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
            if (orderedProgram.get(i) instanceof RoutineDeclarationStatement) {
                IndentManager.print(((RoutineDeclarationStatement) orderedProgram.get(i)).toString(""));
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
            final String ruleName;
            try {
                ruleName = TreeBuilder.TreeToRule(tree.getChild(i), parser);
            } catch (Exception e) {
                System.out.println("Exception occurred during parsing the Program:\n" + e);
                continue;
            }

            switch (ruleName) {
                case "routineDeclaration":
                    program.addRoutine(RoutineDeclarationStatement.parse(tree.getChild(i), parser));
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
}
