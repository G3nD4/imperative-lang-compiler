package Nodes;

import Nodes.statement.Statement;
import main.MyLangParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class Program {
    ArrayList<Statement> statements;
    ArrayList<RoutineDeclarationStatement> routines;

    public Program(ArrayList<Statement> statements, ArrayList<RoutineDeclarationStatement> routines) {
        this.statements = statements;
        this.routines = routines;
    }

    public static Program parse(ParseTree tree, MyLangParser parser) {
        System.out.println(parser.getRuleNames()[((ParserRuleContext) tree).getRuleIndex()]);
        for (int i = 0; i < tree.getChildCount(); ++i) {
//            switch (tree.getChild(i).)
        }
        return new Program(new ArrayList<Statement>(), new ArrayList<RoutineDeclarationStatement>());
    }
}
