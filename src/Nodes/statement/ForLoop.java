package Nodes.statement;

import Nodes.Body;
import Nodes.Range;
import main.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;

public class ForLoop extends Statement {
    public final String loopVariable;
    public final Range range;
    public final boolean reverse;
    public final Body body;

    public ForLoop(String loopVariable, Range range, boolean reverse, Body body) {
        this.loopVariable = loopVariable;
        this.range = range;
        this.reverse = reverse;
        this.body = body;
    }

    @Override
    public String toString() {
        return "ForLoop{" +
                "loopVariable='" + loopVariable + '\'' +
                ", range='" + range.toString() + '\'' +
                ", reverse=" + reverse +
                ", body=" + body +
                '}';
    }

    public static ForLoop parse(ParseTree tree, MyLangParser parser) {
        String loopVariable = String.valueOf(tree.getChild(1));

        // If "reverse" exists, "range" and "loop" shifts to 1
        boolean reverseExists = String.valueOf(tree.getChild(3)).equals("reverse");
        int rangeChildIndex = reverseExists ? 4 : 3;
        int bodyChildIndex = reverseExists ? 7 : 6;
        Range range = Range.parse(tree.getChild(rangeChildIndex), parser);
        Body body = Body.parse(tree.getChild(bodyChildIndex), parser);

        return new ForLoop(loopVariable, range, reverseExists, body);
    }
}
