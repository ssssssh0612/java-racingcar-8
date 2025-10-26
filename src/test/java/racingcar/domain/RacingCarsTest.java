package racingcar.domain;

import model.domain.CarName;
import model.domain.Distance;
import model.domain.RacingCar;
import model.domain.RacingCars;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RacingCarsTest {

    private static CarName makeCarName(String name) {
        return new CarName(name);
    }

    private static Distance makeDistance(int distance) {
        return new Distance(distance);
    }

    private static RacingCar makeRacingCar(String name, int distance) {
        return new RacingCar(makeCarName(name), makeDistance(distance));
    }

    @Nested
    class 생성_검증 {

        @Test
        void 자동차가_2대_미만이면_예외() {
            // given
            List<RacingCar> one = List.of(makeRacingCar("A", 1));

            // then
            assertThatThrownBy(() -> new RacingCars(one))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("자동차는 2대 이상 존재해야 합니다");
        }

        @Test
        void 이름이_중복되면_예외() {
            // given
            List<RacingCar> dup = List.of(
                    makeRacingCar("A", 1),
                    makeRacingCar("A", 3)
            );
            // then
            assertThatThrownBy(() -> new RacingCars(dup))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("중복된 자동차 이름이 존재합니다");
        }

        @Test
        void 방어적_복사로_외부_리스트_수정_영향_없음() {
            // given
            List<RacingCar> racingCars = new ArrayList<>
                    (List.of(makeRacingCar("A", 1),
                            makeRacingCar("B", 2)));

            // when
            RacingCars testRacingCars = new RacingCars(racingCars);
            racingCars.add(makeRacingCar("C", 1));

            // then
            String s = testRacingCars.toString();
            long lineCount = s.chars().filter(ch -> ch == '\n').count();
            assertThat(lineCount).isEqualTo(2);
            assertThat(s).isEqualTo("A : -\nB : --\n");
        }
    }

    @Nested
    class 우승자_선정 {

        @Test
        void 최장거리_단일_우승자() {
            // given
            RacingCar a = makeRacingCar("A", 1);
            RacingCar b = makeRacingCar("B", 3);
            RacingCar c = makeRacingCar("C", 2);
            RacingCars cars = new RacingCars(List.of(a, b, c));

            // when
            RacingCar winner = cars.findWinner();

            // then
            assertThat(winner).isEqualTo(b);
            assertThat(winner.toString()).isEqualTo("B : ---");
        }

        @Test
        void 동일_최장거리면_findWinner는_첫_등장자를_반환한다() {
            // given
            RacingCar a = makeRacingCar("A", 3);
            RacingCar b = makeRacingCar("B", 3);
            RacingCars cars = new RacingCars(List.of(a, b));

            // then
            RacingCar winner = cars.findWinner();
            assertThat(winner).isEqualTo(a);
        }
    }

    @Nested
    class 문자열_표현 {

        @Test
        void 각_차량을_개행으로_이어붙이고_마지막에_개행을_붙인다() {
            // given
            RacingCars cars = new RacingCars(List.of(
                    makeRacingCar("A", 1),
                    makeRacingCar("B", 3)));

            // then
            assertThat(cars.toString()).isEqualTo("A : -\nB : ---\n");
        }
    }
}