package org.example;

import org.example.validator.CalculatorValidatorImpl;
import org.example.validator.ICalculatorValidator;

import java.util.HashMap;


public class Calculator {

    private static final HashMap<String, ActionsEnum> SYMBOLS_AND_OPERATIONS = ActionsEnum.symbolsToActionsMap();
    private static final String PLUS_MINUS_PATTERN = "\\s[" + ActionsEnum.ADD.getRegexToken() + ActionsEnum.SUB.getRegexToken() + "]\\s";
    private static final String MULTIPLY_DIVIDE_PATTERN = "\\s[" + ActionsEnum.MULTIPLY.getRegexToken() + ActionsEnum.DIVIDE.getRegexToken() + "]\\s";

    private final ICalculatorValidator validator;

    public Calculator() {
        this.validator = new CalculatorValidatorImpl();
    }

    public int calculate(String input) {
        validator.validate(input);
        return chainOperation(input, PLUS_MINUS_PATTERN);
    }

    private int chainOperation(String input, String operationPattern) {
        String[] operatorsAndOperands = input.splitWithDelimiters(operationPattern, -1);
        ActionsEnum lastAction = null;
        int result = 0;

        for (String element : operatorsAndOperands) {
            if (SYMBOLS_AND_OPERATIONS.containsKey(element.trim())) {
                lastAction = SYMBOLS_AND_OPERATIONS.get(element.trim());
            }
            else {
                Integer nextComponent = element.matches(".*" + MULTIPLY_DIVIDE_PATTERN + ".*")
                        ? chainOperation(element, MULTIPLY_DIVIDE_PATTERN)
                        : Integer.parseInt(element);

                if (lastAction != null) {
                    result = lastAction.getAction().apply(result, nextComponent);
                }
                else {
                    result = nextComponent;
                }
            }
        }

        return result;
    }
}
