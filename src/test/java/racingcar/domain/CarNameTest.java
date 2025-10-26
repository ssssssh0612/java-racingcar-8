package racingcar.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import model.domain.CarName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CarNameTest {

    @Nested
    class 생성_성공 {

        @ParameterizedTest
        @ValueSource(strings = {
                "a",
                "A",
                "1",
                "가",
                "A1가",
                "abcde",
                "가나다라마"
        })
        void 허용된_문자와_길이면_예외가_발생하지_않는다(String input) {
            assertThatCode(() -> new CarName(input)).doesNotThrowAnyException();
        }

    }

    @Nested
    class 유효성_실패 {

        @Test
        void 빈_문자열이면_길이_예외() {
            assertThatThrownBy(() -> new CarName(""))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이름 길이는 한 글자 이상 다섯 글자 이하만 허용합니다");
        }

        @ParameterizedTest
        @ValueSource(strings = {"abcdef", "가나다라마바"})
        void 길이가_6_이상이면_길이_예외(String input) {
            assertThatThrownBy(() -> new CarName(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이름 길이는 한 글자 이상 다섯 글자 이하만 허용합니다");
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "ab cd",
                "ab_cd",
                "ab-cd",
                "abc!",
                "ㄱ",
                "ㅏ",
                "한글 ",
                "❤❤️"
        })
        void 허용되지_않은_문자가_있으면_예외(String input) {
            assertThatThrownBy(() -> new CarName(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이름은 영문자/숫자/완성형 한글(가-힣)만 허용합니다");
        }
    }

    @Nested
    class 동등성_검증 {

        @Test
        void 같은_값이면_equals와_hashCode가_같다() {
            CarName a = new CarName("Same");
            CarName b = new CarName("Same");

            assertThat(a).isEqualTo(b).isEqualTo(a);
            assertThat(a.hashCode()).isEqualTo(b.hashCode());
        }

        @Test
        void 다른_값이면_equals가_false() {
            CarName a = new CarName("Car1");
            CarName b = new CarName("Car2");
            assertThat(a).isNotEqualTo(b);
        }

        @Test
        void 대소문자는_구분된다() {
            CarName lower = new CarName("car");
            CarName upper = new CarName("Car");
            assertThat(lower).isNotEqualTo(upper);
        }
    }

    @Nested
    class toString_메서드_검증 {

        @Test
        void toString_형식은_이름_뒤에_공백포함_콜론이다() {
            assertThat(new CarName("Hello").toString()).isEqualTo("Hello : ");
        }
    }
}