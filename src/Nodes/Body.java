package Nodes;

import Nodes.Interfaces.JasminConvertable;
import Nodes.jasmine.CodeGenerator;
import Nodes.statement.Declarations.Declaration;
import Nodes.statement.Declarations.VariableDeclaration;
import Nodes.statement.Statement;
import main.IndentManager;
import main.antlrTree.MyLangParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

public class Body implements JasminConvertable {
    private final List<Declaration> declarations = new ArrayList<>();
    private final List<Statement> statements = new ArrayList<>();
    /*
     Contains declarations and statements in the right order.
     */
    private ArrayList<Object> orderedDnS = new ArrayList<>();

    public void addDeclaration(Declaration declaration) {
        declarations.add(declaration);
        orderedDnS.add(declaration);
    }

    public void addStatement(Statement statement) {
        statements.add(statement);
        orderedDnS.add(statement);
    }

    public ArrayList<Object> getOrderedDnS() {
        return orderedDnS;
    }

    public void setDns(ArrayList<Object> newOrderedDnS) {
        orderedDnS.clear();
        statements.clear();
        declarations.clear();
        for (Object object: newOrderedDnS) {
            if (object instanceof Statement statement) {
                addStatement(statement);
            } else if (object instanceof Declaration declaration) {
                addDeclaration(declaration);
            }
        }
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public static Body parse(ParseTree tree, MyLangParser parser) {
        Body body = new Body();
        for (int i = 0; i < tree.getChildCount(); i++) {
            ParseTree child = tree.getChild(i);
            if (child instanceof ParserRuleContext) {
                String ruleName = parser.getRuleNames()[((ParserRuleContext) child).getRuleIndex()];
                switch (ruleName) {
                    case "declaration":
                        body.addDeclaration(VariableDeclaration.parse(child.getChild(0), parser));
                        break;
                    case "statement":
                        body.addStatement(Statement.parse(child, parser));
                        break;
                }
            }
        }
        return body;
    }

    public String toString(String indent) {
        IndentManager.print("Body:");
        IndentManager.goDown();
        for (final Object obj : orderedDnS) {
            if (obj instanceof Declaration) {
                IndentManager.print(((Declaration)obj).toString(""));
            } else {
                IndentManager.print(((Statement)obj).toString(""));
            }
        }
        IndentManager.goUp();

        return "";
    }

    @Override
    public void generateCode(CodeGenerator generator) {
        for (var statement : orderedDnS) {
            ((JasminConvertable) statement).generateCode(generator);
        }
    }
}
