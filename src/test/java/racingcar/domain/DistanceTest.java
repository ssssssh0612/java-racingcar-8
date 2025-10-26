package racingcar.domain;

import model.domain.Distance;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DistanceTest {

    @Nested
    class 문자열_표현 {

        @ParameterizedTest
        @ValueSource(ints = {1, 3, 5})
        void 값이_n이면_toString은_하이픈_n개다(int n) {
            // given
            Distance d = new Distance(n);

            // then
            assertThat(d.toString())
                    .isEqualTo("-".repeat(n))
                    .hasSize(n);
        }
    }

    @Nested
    class 증가_연산과_불변성 {

        @Test
        void incrementDistance는_새_인스턴스를_반환하고_값은_1_증가한다() {
            // given
            Distance d0 = new Distance(0);
            Distance d1 = d0.incrementDistance();

            // then
            assertThat(d1).isNotSameAs(d0);

            assertThat(d0.toString()).hasSize(0);
            assertThat(d1.toString()).hasSize(1);

            assertThat(d1.isFartherThan(d0)).isTrue();
            assertThat(d0.isFartherThan(d1)).isFalse();
            assertThat(d1.isSameAs(d0)).isFalse();
        }

        @Test
        void 여러번_증가해도_매번_새_인스턴스를_반환한다() {
            // given
            Distance d0 = new Distance(0);
            Distance d1 = d0.incrementDistance();
            Distance d2 = d1.incrementDistance();

            // then
            assertThat(d1).isNotSameAs(d0);
            assertThat(d2).isNotSameAs(d1).isNotSameAs(d0);
            assertThat(d0.toString()).hasSize(0);
            assertThat(d1.toString()).hasSize(1);
            assertThat(d2.toString()).hasSize(2);
        }
    }

    @Nested
    class 비교_연산 {

        @ParameterizedTest
        @CsvSource({
                "5,3,true,false",   // distanceA > distanceB
                "3,5,false,false",  // distanceA < distanceB
                "4,4,false,true"    // distanceA == distanceB
        })
        void isFartherThan과_isSameAs의_관계(int a, int b, boolean expectFarther, boolean expectSame) {
            // given
            Distance A = new Distance(a);
            Distance B = new Distance(b);

            // then
            assertThat(A.isFartherThan(B)).isEqualTo(expectFarther);
            assertThat(A.isSameAs(B)).isEqualTo(expectSame);
        }

    }
}
