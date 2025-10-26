package model.domain;

import java.util.regex.Pattern;
import java.util.Objects;

public class CarName {
    private static final int INCLUSIVE_NAME_MIN_LENGTH = 1;
    private static final int INCLUSIVE_NAME_MAX_LENGTH = 5;
    private static final String REGEX = "^[0-9a-zA-Z가-힣]+$";
    private static final Pattern NAME_ALLOWED_CHARS = Pattern.compile(REGEX);
    private final String name;

    public CarName(String name) {
        validate(name);
        this.name = name;
    }

    private static void validate(String name) {
        requireLengthBetween(name);
        requireNameCharactersAllowed(name);
    }

    private static void requireLengthBetween(String name) {
        int length = name.length();
        if (length < INCLUSIVE_NAME_MIN_LENGTH || length > INCLUSIVE_NAME_MAX_LENGTH) {
            throw new IllegalArgumentException("이름 길이는 한 글자 이상 다섯 글자 이하만 허용합니다");
        }
    }

    private static void requireNameCharactersAllowed(String name) {
        if (!NAME_ALLOWED_CHARS.matcher(name).matches()) {
            throw new IllegalArgumentException("이름은 영문자/숫자/완성형 한글(가-힣)만 허용합니다");
        }
    }

    @Override
    public String toString() {
        return name + " : ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarName carName = (CarName) o;
        return Objects.equals(name, carName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
