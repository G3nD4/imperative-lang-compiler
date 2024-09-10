package Error_detector;

import java.util.ArrayList;

import Lexical_analyzer.Token;

public class ErrorDetector {
    public ArrayList<LexicalError> detectErrors(ArrayList<Token> tokens) {
        ArrayList<LexicalError> lexicalErrors = new ArrayList<>();

        for (Token token : tokens) {
            LexicalError error = getLexicalErrorOrNull(token);
            if (error != null) {
                lexicalErrors.add(error);
            }
        }

        return lexicalErrors;
    }

    private LexicalError getLexicalErrorOrNull(Token token) {

        LexicalError error = new LexicalError(token.copy());

        addErrorIfNotNull(startWithDigit(token.getName()), error);

        addErrorIfNotNull(containsIllegalCharacters(token.getName()), error);

        return error.hasErrors() ? error : null;
    }

    private LexicalErrorType containsIllegalCharacters(String name) {
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (!(Character.isDigit(ch) || (Character.isLetter(ch) && ch <= 0x7F))) {
                return LexicalErrorType.IDENTIFYER_NAME_CONTAINS_ILLEGAL_CHARACTERS;
            }
        }
        return null;
    }

    private LexicalErrorType startWithDigit(String name) {
        if (Character.isDigit(name.charAt(0))) {
            return LexicalErrorType.IDENTIFYER_NAME_DOESNT_START_WITH_DIGIT;
        }
        return null;
    }

    private void addErrorIfNotNull(LexicalErrorType errorType, LexicalError lexicalError) {
        if (errorType != null) {
            lexicalError.addError(errorType);
        }
    }

}
