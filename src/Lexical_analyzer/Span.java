package Lexical_analyzer;

public class Span {

    public Span(long lineNumber) {
        this.lineNumber = lineNumber;
    }

    private long lineNumber;
    // The beginning of the span (inclusive)
    // private int startIndex;
    // // The end of the span (inclusive)
    // private int endIndex;

    public Span(long lineNumber, int startIndex, int endIndex) {
        this.lineNumber = lineNumber;
        // this.startIndex = startIndex;
        // this.endIndex = endIndex;
    }

    public long getLineNumber() {
        return lineNumber;
    }

    // public int getStartIndex() {
    //     return startIndex;
    // }
    // public int getEndIndex() {
    //     return endIndex;
    // }
}
