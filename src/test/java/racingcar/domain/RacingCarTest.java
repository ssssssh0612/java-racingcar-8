package racingcar.domain;

import model.domain.CarName;
import model.domain.Distance;
import model.domain.RacingCar;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RacingCarTest {

    private static CarName makeName(String name) { return new CarName(name); }
    private static Distance makeDistance(int distance) { return new Distance(distance); }

    @Nested
    class 이동_로직 {

        @ParameterizedTest(name = "random={0} → expectMove={1}")
        @CsvSource({
                "0,false",
                "1,false",
                "2,false",
                "3,false",
                "4,true",
                "5,true",
                "9,true"
        })
        void 난수_임계값_기준으로_전진_여부가_결정된다(int random, boolean move) {
            // given
            RacingCar car = new RacingCar(makeName("Alpha"), makeDistance(0));

            // when
            RacingCar next = car.moveRacingCar(random);

            // then
            assertThat(next == car).isEqualTo(!move);
            assertThat(next.isFartherThan(car)).isEqualTo(move);
            assertThat(next.isSameDistanceAs(car)).isEqualTo(!move);
        }

        @Test
        void 전진해도_원본은_불변이며_새_인스턴스만_증가한다() {
            // given
            RacingCar car = new RacingCar(makeName("Beta"), makeDistance(2));

            // when
            RacingCar movedCar = car.moveRacingCar(4);

            // then
            assertThat(movedCar).isNotSameAs(car);
            assertThat(car.toString()).isEqualTo("Beta : --");
            assertThat(movedCar.toString()).isEqualTo("Beta : ---");
        }

        @Test
        void 정지하면_자기자신을_반환한다() {
            // given
            RacingCar car = new RacingCar(makeName("Gamma"), makeDistance(3));

            // when
            RacingCar same = car.moveRacingCar(0);

            // then
            assertThat(same).isSameAs(car);
        }
    }

    @Nested
    class 비교_메서드 {

        @ParameterizedTest
        @CsvSource({
                "5,3,true,false",   // a > b
                "3,5,false,false",  // a < b
                "4,4,false,true"    // a == b
        })
        void isFartherThan_isSameDistanceAs_관계_검증(int a, int b, boolean expectFarther, boolean expectSame) {
            // given
            RacingCar A = new RacingCar(makeName("A"), makeDistance(a));
            RacingCar B = new RacingCar(makeName("B"), makeDistance(b));

            // then
            assertThat(A.isFartherThan(B)).isEqualTo(expectFarther);
            assertThat(A.isSameDistanceAs(B)).isEqualTo(expectSame);
            assertThat(B.isFartherThan(A)).isEqualTo(!expectFarther && !expectSame && b > a);
            assertThat(B.isSameDistanceAs(A)).isEqualTo(expectSame);
        }
    }

    @Nested
    class 표현_및_동등성 {

        @Test
        void getResultName은_CarName_원본을_반환한다() {
            // given
            RacingCar car = new RacingCar(makeName("Alpha"), makeDistance(2));

            // then
            assertThat(car.getResultName()).isEqualTo("Alpha");
        }

        @Test
        void toString은_이름_공백_콜론_공백_뒤에_거리_하이픈을_표현한다() {
            // given
            RacingCar car = new RacingCar(makeName("Neo"), makeDistance(3));

            // then
            assertThat(car.toString()).isEqualTo("Neo : ---");
        }

        @Test
        void equals_hashCode는_이름만으로_결정된다() {
            // given
            RacingCar a = new RacingCar(makeName("Rex"), makeDistance(1));
            RacingCar b = new RacingCar(makeName("Rex"), makeDistance(5));
            RacingCar c = new RacingCar(makeName("Lex"), makeDistance(1));

            // when & then
            assertThat(a).isEqualTo(b);
            assertThat(a.hashCode()).isEqualTo(b.hashCode());
            assertThat(a).isNotEqualTo(c);
        }
    }
}
