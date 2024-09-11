package Error_detector;

import java.util.ArrayList;

import Lexical_analyzer.Token;
import Lexical_analyzer.TokenType;

public class ErrorDetector {
    public ArrayList<LexicalError> detectErrors(ArrayList<Token> tokens) {
        ArrayList<LexicalError> lexicalErrors = new ArrayList<>();

        for (Token token : tokens) {
            if (token.getType() == TokenType.IDENTIFIER) {
                LexicalError error = getLexicalErrorOrNull(token);
                if (error != null) {
                    lexicalErrors.add(error);
                }
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

    private boolean characterIn(Character character, Character[] array) {
        for (Character character1 : array) {
            if (character == character1) {
                return true;
            }
        }
        return false;
    }

    private LexicalErrorType containsIllegalCharacters(String name) {
        Character[] notAllowedSpecial = new Character[]{'!', '"', '#', '$', '`', '@', '[', ']', '~'};
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (Character.isDigit(ch) || characterIn(ch, notAllowedSpecial)) {
                System.out.println(name);
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
