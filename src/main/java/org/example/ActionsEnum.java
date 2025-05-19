package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public enum ActionsEnum {
    ADD("+", "+", Integer::sum),
    SUB("-", "\\-", (Integer a, Integer b) -> a - b),
    MULTIPLY("*", "*", (Integer a, Integer b) -> a * b),
    DIVIDE("/", "/", (Integer a, Integer b) -> a / b);

    private final String symbol;
    private final String regexToken;
    private final BiFunction<Integer, Integer, Integer> action;

    ActionsEnum(String symbol, String regexToken, BiFunction<Integer, Integer, Integer> action) {
        this.symbol = symbol;
        this.regexToken = regexToken;
        this.action = action;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getRegexToken() {
        return regexToken;
    }

    public BiFunction<Integer, Integer, Integer> getAction() {
        return action;
    }

    public static HashMap<String, ActionsEnum> symbolsToActionsMap() {
        return (HashMap<String, ActionsEnum>) Arrays.stream(ActionsEnum.values())
                .collect(Collectors.toMap(
                        ActionsEnum::getSymbol,
                        (ActionsEnum elem) -> elem
                ));
    }

    public static String getRegexTokens() {
        return Arrays.stream(ActionsEnum.values())
                .map(ActionsEnum::getRegexToken)
                .collect(Collectors.joining(""));
    }
}
