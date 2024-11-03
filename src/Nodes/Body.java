package Nodes;

import Nodes.statement.Statement;
import main.MyLangParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

public class Body {
    private final List<Declaration> declarations = new ArrayList<>();
    private final List<Statement> statements = new ArrayList<>();

    public void addDeclaration(Declaration declaration) {
        declarations.add(declaration);
    }

    public void addStatement(Statement statement) {
        statements.add(statement);
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


    @Override
    public String toString() {
        return "Block{" +
                "statements=" + statements +
                '}';
    }
}