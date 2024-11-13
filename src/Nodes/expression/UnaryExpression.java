package Nodes.expression;

import Lexical_analyzer.TokenType;
import Nodes.Operation;
import Nodes.Sign;
import Nodes.Type;
import Nodes.primary.Primary;
import main.IndentManager;
import main.MyLangParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

public class UnaryExpression extends Expression {
    public Sign sign;
    public Primary primary;

    public UnaryExpression(Sign sign, Primary primary, Type type) {
        this.sign = sign;
        this.primary = primary;
        super.type = type;
    }

    public static UnaryExpression parse(ParseTree tree, MyLangParser parser) {
        Sign sign = Sign.PLUS;

        if (tree.getChildCount() == 1) {
            // we have only Primary
            final Primary _primary = Primary.parse(tree.getChild(0), parser);
            return new UnaryExpression(sign, _primary, _primary != null ? _primary.type : null);
        } else {
            // we have sign and Primary
            sign = switch (String.valueOf(tree.getChild(0))) {
                case "+" -> Sign.PLUS;
                case "-" -> Sign.MINUS;
                case "not" -> Sign.NOT;
                default -> throw new IllegalStateException("Unexpected value: " + tree.getChild(0));
            };
            final Primary _primary = Primary.parse(tree.getChild(1), parser);
            return new UnaryExpression(sign, _primary, _primary != null ? _primary.type : null );
        }
    }

    @Override
    public String toString(String indent) {
        IndentManager.print("Unary Expression:");

        IndentManager.goDown();

        IndentManager.print("sign:" + sign.toString().toLowerCase());
        IndentManager.print(primary.toString(""));

        IndentManager.goUp();

        return "";
    }
}
