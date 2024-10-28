package Nodes;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class Program {
    ArrayList<Statement> statemets;
    ArrayList<RoutineDeclarationStatement> routines;

    public Program(ArrayList<Statement> statemets, ArrayList<RoutineDeclarationStatement> routines) {
        this.statemets = statemets;
        this.routines = routines;
    }

    public static Program parse(ParseTree tree) {
        for (int i = 0; i < tree.getChildCount(); ++i) {
//            switch (tree.getChild(i).)
        }
        return new Program(new ArrayList<Statement>(), new ArrayList<RoutineDeclarationStatement>());
    }
}
