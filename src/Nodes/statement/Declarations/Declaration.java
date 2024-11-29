package Nodes.statement.Declarations;

import Nodes.Interfaces.JasminConvertable;
import main.MyLangParser;
import main.TreeBuilder;
import org.antlr.v4.runtime.tree.ParseTree;

public abstract class Declaration implements JasminConvertable {
    // TODO: if ruleName == 'variable declaration' : varDec.parse
    // TODO: else if rule name == 'type declaration' : typeDec.parse
    // TODO: else : sout(error), System.exit(1);

    public static Declaration parse(ParseTree tree, MyLangParser parser) {
        ParseTree child = tree.getChild(0);
        String ruleName = TreeBuilder.TreeToRule(child, parser);

        return switch (ruleName) {
            case "variableDeclaration" -> VariableDeclaration.parse(child, parser);
            case "typeDeclaration" -> TypeDeclaration.parse(child, parser);
            default -> throw new IllegalStateException("Unexpected value: " + ruleName);
        };
    }

    public abstract String toString(String indent);
}
