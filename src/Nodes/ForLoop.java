package Nodes;

public class ForLoop extends Statement {
    private final String loopVariable;
    private final String range;
    private final boolean reverse;
    private final Block body;

    public ForLoop(String loopVariable, String range, boolean reverse, Block body) {
        this.loopVariable = loopVariable;
        this.range = range;
        this.reverse = reverse;
        this.body = body;
    }

    public String getLoopVariable() {
        return loopVariable;
    }

    public String getRange() {
        return range;
    }

    public boolean isReverse() {
        return reverse;
    }

    public Block getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "ForLoop{" +
                "loopVariable='" + loopVariable + '\'' +
                ", range='" + range + '\'' +
                ", reverse=" + reverse +
                ", body=" + body +
                '}';
    }
}
