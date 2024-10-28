package main;

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

    @Override
    public String toString() {
        return "Block{" +
                "statements=" + statements +
                '}';
    }
}