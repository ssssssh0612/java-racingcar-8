package model.domain;

import java.util.regex.Pattern;

public class UserInputNumber {
    private final int count;
    private static final String REGEX = "^[0-9]+$";
    private static final Pattern ALLOWED_NUMBER = Pattern.compile(REGEX);
    private static final int INCLUSIVE_MAX_LENGTH = 8;
    private static final int INCLUSIVE_MIN_LENGTH = 1;
    private static final int ZERO = 0;
    public UserInputNumber(String userInput) {
        validate(userInput);
        this.count = Integer.parseInt(userInput);
    }

    private static void validate(String userInput) {
        requireInputLengthBetween(userInput);
        requireInputOnlyNumber(userInput);
        requireOnlyPositiveNumber(userInput);
    }

    private static void requireInputLengthBetween(String userInput) {
        int length = userInput.length();
        if (length < INCLUSIVE_MIN_LENGTH || length > INCLUSIVE_MAX_LENGTH) {
            throw new IllegalArgumentException("숫자는 최소 1자리, 최대 8자리입니다");
        }
    }

    private static void requireInputOnlyNumber(String userInput) {
        if (!ALLOWED_NUMBER.matcher(userInput).matches()) {
            throw new IllegalArgumentException("숫자만 입력해주세요");
        }
    }

    private static void requireOnlyPositiveNumber(String userInput) {
        int number = Integer.parseInt(userInput);
        if (number == ZERO) {
            throw new IllegalArgumentException("양수의 숫자만 입력해주세요");
        }
    }

    public int getCount() {
        return count;
    }

}
