package racingcar.domain;

import model.domain.UserInputString;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserInputStringTest {

    private static List<String> toList(Iterator<String> it) {
        List<String> out = new ArrayList<>();
        while (it.hasNext()) {
            out.add(it.next());
        }
        return out;
    }

    @Nested
    class 생성_성공 {

        @ParameterizedTest
        @ValueSource(strings = {
                "a",
                "가",
                "ABC",
                "123",
                "a,b,c",
                "a, b, c",
                "이름1,이름2,이름3",
                "a,b-c,_x",
        })
        void 형식과_길이를_만족하면_생성된다(String input) {
            assertThatCode(() -> new UserInputString(input))
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    class 유효성_실패_길이 {

        @Test
        void 빈_문자열이면_길이_예외() {
            assertThatThrownBy(() -> new UserInputString(""))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("문자열은 1 ~ 99,999,999자여야 합니다");
        }

    }

    @Nested
    class 유효성_실패_형식 {

        @ParameterizedTest
        @ValueSource(strings = {
                ",a",
                "a,",
                "a,,b",
                ",",
        })
        void 시작_또는_끝_쉼표_혹은_연속_쉼표면_형식_예외(String input) {
            assertThatThrownBy(() -> new UserInputString(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("올바르지 않은 문자열 형식을 가집니다");
        }
    }

    @Nested
    class 이터레이터_동작 {

        @Test
        void 쉼표로_분할하고_순서를_유지한다() {
            // given
            UserInputString s = new UserInputString("a,b,c");
            List<String> tokens = toList(s.getNameIterator());

            // then
            assertThat(tokens).containsExactly("a", "b", "c");
        }
    }
}