package Nodes.expression;

import Lexical_analyzer.TokenType;
import Nodes.Operation;
import Nodes.Sign;
import Nodes.primary.Primary;
import main.MyLangParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

public class UnaryExpression extends Expression {
    public Sign sign;
    public Primary primary;

    public UnaryExpression(Sign sign, Primary primary) {
        this.sign = sign;
        this.primary = primary;
    }

    public static UnaryExpression parse(ParseTree tree, MyLangParser parser) {
        Primary primary;
        Sign sign = Sign.PLUS;

        if (tree.getChildCount() == 1) {
            // we have only Primary
            return new UnaryExpression(sign, Primary.parse(tree.getChild(0), parser));
        } else {
            // we have sign and Primary
            sign = switch (String.valueOf(tree.getChild(0))) {
                case "+" -> Sign.PLUS;
                case "-" -> Sign.MINUS;
                case "not" -> Sign.NOT;
                default -> throw new IllegalStateException("Unexpected value: " + tree.getChild(0));
            };
            return new UnaryExpression(sign, Primary.parse(tree.getChild(1), parser));
        }
    }
}
