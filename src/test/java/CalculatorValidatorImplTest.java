import org.example.validator.CalculatorValidatorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

public class CalculatorValidatorImplTest {
    private static CalculatorValidatorImpl validator;

    @BeforeAll
    static void init() {
        validator = new CalculatorValidatorImpl();
    }

    @Test
    void shouldThrowExceptionWhenInputIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> validator.validate(null));
    }

    @Test
    void shouldThrowExceptionWhenInputIsEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.validate("      "));
    }

    @Test
    void shouldThrowExceptionWhenInputIsIncorrect() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.validate("12s+"));
    }

    @ParameterizedTest(name = "{0} should be valid input")
    @MethodSource({"getValidInputs"})
    void shouldValidateSuccessfully(String input) {
        Assertions.assertDoesNotThrow(() -> validator.validate(input));
    }

    public static List<Object[]> getValidInputs() {
        return Arrays.asList(
                new Object[]{"3 * -2 + 6"},
                new Object[]{"-13"},
                new Object[]{"-2 + -1 - -4 * 7 / -11"},
                new Object[]{"14 + 1"}
        );
    }
}
