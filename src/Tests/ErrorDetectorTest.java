package Tests;

import org.junit.jupiter.api.Test;

import Lexical_analyzer.Error_detector.*;

import Lexical_analyzer.Error_detector.ErrorDetector;

import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;

public class ErrorDetectorTest {

    @Test
    public void testContainsIllegalCharacters() throws Exception {
        ErrorDetector errorDetector = new ErrorDetector();
        Method method = ErrorDetector.class.getDeclaredMethod("containsIllegalCharacters", String.class);
        method.setAccessible(true);

        assertNull(method.invoke(errorDetector, "validName123"));
        assertEquals(LexicalErrorType.IDENTIFYER_NAME_CONTAINS_ILLEGAL_CHARACTERS, 
                method.invoke(errorDetector, "invalid!name"));
        assertEquals(LexicalErrorType.IDENTIFYER_NAME_CONTAINS_ILLEGAL_CHARACTERS, 
                method.invoke(errorDetector, "name with space"));
    }

    @Test
    public void testDoesNotStartWithDigit() throws Exception {
        ErrorDetector errorDetector = new ErrorDetector();
        Method method = ErrorDetector.class.getDeclaredMethod("startWithDigit", String.class);
        method.setAccessible(true);

        assertNull(method.invoke(errorDetector, "validName"));
        assertEquals(LexicalErrorType.IDENTIFYER_NAME_DOESNT_START_WITH_DIGIT, 
                method.invoke(errorDetector, "1invalidName"));
    }
}
