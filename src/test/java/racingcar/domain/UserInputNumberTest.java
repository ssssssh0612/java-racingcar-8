package racingcar.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import model.domain.UserInputNumber;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserInputNumberTest {

    @Nested
    class 생성_성공 {

        @ParameterizedTest
        @ValueSource(strings = {
                "1",        // 최소 정상
                "9",
                "10",
                "12345678", // 최대 자리수(8)
                "0001",     // 리딩 제로 허용
        })
        void 숫자만_포함하고_자릿수가_1_에서_8이면_생성된다(String input) {
            assertThatCode(() -> new UserInputNumber(input)).doesNotThrowAnyException();
        }

        @Test
        void 파싱된_count가_int로_반환된다() {
            // given
            UserInputNumber n = new UserInputNumber("123");

            // when & then
            assertThat(n.getCount()).isEqualTo(123);
        }

        @Test
        void 최대_허용값_경계인_99999999_도_통과한다() {
            // given
            UserInputNumber n = new UserInputNumber("99999999");

            // when & then
            assertThat(n.getCount()).isEqualTo(99_999_999);
        }
    }


    @Nested
    class 유효성_실패_길이 {

        @Test
        void 빈_문자열이면_길이_예외() {
            assertThatThrownBy(() -> new UserInputNumber(""))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("숫자는 최소 1자리, 최대 8자리입니다");
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "100000000", // 9자리
                "123456789"  // 9자리
        })
        void 자릿수가_9이상이면_길이_예외(String input) {
            assertThatThrownBy(() -> new UserInputNumber(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("숫자는 최소 1자리, 최대 8자리입니다");
        }
    }

    @Nested
    class 유효성_실패_패턴 {

        @ParameterizedTest
        @ValueSource(strings = {
                "-1",     // 음수 기호
                "1.5",    // 소수점
                " 12",    // 앞 공백
                "12 ",    // 뒤 공백
                "1_000",  // 언더스코어
                "1,000",  // 콤마
                "abc"     // 문자
        })
        void 숫자_이외_문자가_포함되면_패턴_예외(String input) {
            assertThatThrownBy(() -> new UserInputNumber(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("숫자만 입력해주세요");
        }

        @Test
        void 영_이하면_양수_예외가_나와야_한다() {
            assertThatThrownBy(() -> new UserInputNumber("0"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("양수의 숫자만 입력해주세요");
        }
    }

}