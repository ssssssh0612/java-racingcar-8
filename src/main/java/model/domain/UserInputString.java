package model.domain;

import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Pattern;

public class UserInputString {
    private final String userInput;
    private static final String REGEX = "^(?!,)(?!.*,,)(.*[^,])?$";
    private static final Pattern ALLOWED_CHARS = Pattern.compile(REGEX);
    private static final int INCLUSIVE_MAX_LENGTH = 99_999_999;
    private static final int INCLUSIVE_MIN_LENGTH = 1;

    public UserInputString(String userInput) {
        validate(userInput);
        this.userInput = userInput;
    }

    private static void validate(String userInput) {
        requireInputLengthBetween(userInput);
        requireInputStringCharactersAllowed(userInput);
    }

    private static void requireInputLengthBetween(String userInput){
        int length = userInput.length();
        if(length < INCLUSIVE_MIN_LENGTH || length > INCLUSIVE_MAX_LENGTH){
            throw new IllegalArgumentException("문자열은 1 ~ 99,999,999자여야 합니다");
        }
    }

    private static void requireInputStringCharactersAllowed(String userInput){
        if (!ALLOWED_CHARS.matcher(userInput).matches()) {
            throw new IllegalArgumentException("올바르지 않은 문자열 형식을 가집니다");
        }
    }

    public Iterator<String> getNameIterator() {
        String[] arr = userInput.split(",", -1);
        return Arrays.asList(arr).iterator();
    }
}
