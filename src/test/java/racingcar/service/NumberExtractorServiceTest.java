package racingcar.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import model.domain.UserInputString;
import model.service.NameExtractorService;
import org.junit.jupiter.api.Test;

public class NumberExtractorServiceTest {
    NameExtractorService service = new NameExtractorService();

    @Test
    void 토큰을_가공하지_않고_CarName에_그대로_위임한다() {
        // given
        UserInputString input = new UserInputString("A B,B");

        // then
        assertThatThrownBy(() -> service.extractStringReturnRacingCars(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 영문자/숫자/완성형 한글(가-힣)만 허용합니다");
    }
}