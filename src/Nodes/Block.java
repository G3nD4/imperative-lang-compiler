package Nodes;

import main.MyLangParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

public class Block {
    private List<Statement> statements = new ArrayList<>();

    public void addStatement(Statement statement) {
        statements.add(statement);
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public static Block parse(ParseTree tree, MyLangParser parser) {
        Block block = new Block();
        for (int i = 0; i < tree.getChildCount(); i++) {
            ParseTree child = tree.getChild(i);
            if (child instanceof ParserRuleContext) {
                String ruleName = parser.getRuleNames()[((ParserRuleContext) child).getRuleIndex()];
                switch (ruleName) {
                    case "declaration":
                        block.addStatement(VariableDeclaration.parse(child.getChild(0), parser)); // Assuming it returns Statement
                        break;
                    case "statement":
                        block.addStatement(Statement.parse(parser.getRuleNames()[((ParserRuleContext) tree.getChild(0).getChild(0)).getRuleIndex()], tree.getChild(0), parser));
                        Assignment.parse(tree, parser);
                }
            }
        }
        return block;
    }


    @Override
    public String toString() {
        return "Block{" +
                "statements=" + statements +
                '}';
    }
}