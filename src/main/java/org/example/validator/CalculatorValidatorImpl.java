package org.example.validator;

import org.example.ActionsEnum;
import java.util.regex.Pattern;

public class CalculatorValidatorImpl implements ICalculatorValidator {
    private static final String SYMBOLS_PATTERN = "\\s*[" + ActionsEnum.getRegexTokens() + "]\\s*";
    private static final String SIGNED_INTEGER_PATTERN = "-?[0-9]+";

    public void validate(String input) {
        Pattern pattern = Pattern.compile("^(\\s*" + SIGNED_INTEGER_PATTERN + ")(" + SYMBOLS_PATTERN + SIGNED_INTEGER_PATTERN + ")*$");
        if (!pattern.matcher(input).matches()) {
            throw new IllegalArgumentException("Invalid input: " + input);
        }
    }
}
