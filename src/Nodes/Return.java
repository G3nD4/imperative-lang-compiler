package Nodes;

public class Return extends Statement {
    public final String expresn;

    public String getExpresn() {
        return expresn;
    }

    @Override
    public String toString() {
        return "Return{" +
                "expresn='" + expresn + '\'' +
                '}';
    }

    public Return(String expression) {
        this.expresn = expression;
    }
}
