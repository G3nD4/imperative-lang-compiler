package main;

import java.util.ArrayList;

public class LexicalError {

    public LexicalError(Token token) {
        this.token = token;
        this.errors = new ArrayList<>();
    }

    private Token token;
    private ArrayList<LexicalErrorType> errors;

    public void addError(LexicalErrorType error) {
        errors.add(error);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
