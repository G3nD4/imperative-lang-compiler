package main;

public class Statement {
    private String content; // This could be a more complex structure

    public Statement(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Statement{" +
                "content='" + content + '\'' +
                '}';
    }
}
