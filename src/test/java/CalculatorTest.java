import org.example.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

public class CalculatorTest {

    private static Calculator calculator;

    @BeforeAll
    static void init() {
        calculator = new Calculator();
    }

    @Test
    void shouldCreateCalculatorInstance() {
        Assertions.assertNotNull(calculator);
    }

    @Test
    void shouldCallCalculateMethod() {
        Assertions.assertDoesNotThrow(() -> calculator.calculate("2"));
    }

    @Test
    void methodShouldReturnSimpleValue() {
        Assertions.assertEquals(5, calculator.calculate("2 + 3"));
    }

    @Test
    void methodShouldReturnNumberIfOnlyNumberWasGiven() {
        Assertions.assertEquals(2, calculator.calculate("2"));
        Assertions.assertEquals(-2, calculator.calculate("-2"));
    }

    @Test
    void methodShouldSupportPlusOperator() {
        Assertions.assertEquals(7, calculator.calculate("2 + 5"));
    }

    @Test
    void methodShouldSupportMinusOperator() {
        Assertions.assertEquals(-1, calculator.calculate("2 - 3"));
    }

    @Test
    void methodShouldSupportStarOperator() {
        Assertions.assertEquals(6, calculator.calculate("2 * 3"));
    }

    @Test
    void methodShouldSupportDivideOperator() {
        Assertions.assertEquals(-3, calculator.calculate("9 / -3"));
    }

    @ParameterizedTest(name = "{0} should be calculated as: {1}")
    @MethodSource({"getOperationsWithResult"})
    void shouldCalculateProperly(String input, int result) {
        Assertions.assertEquals(calculator.calculate(input), result);
    }

    public static List<Object[]> getOperationsWithResult() {
        return Arrays.asList(
                new Object[]{"3 * -2 + 6", 0},
                new Object[]{"-13 * -2 / 6 - -5", 9},
                new Object[]{"11 - -5 + 6 * -3 * 2 / -4 + -3", 22},
                new Object[]{"-3 * -2 + -6 / 2 - 3 * -3", 12}
        );
    }
}
