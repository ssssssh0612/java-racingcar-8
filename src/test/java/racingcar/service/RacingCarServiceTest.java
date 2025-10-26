package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import model.domain.CarName;
import model.domain.Distance;
import model.domain.RacingCar;
import model.domain.RacingCars;
import model.domain.UserInputNumber;
import model.service.RacingCarsService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import view.OutputView;

import static org.assertj.core.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RacingCarsServiceMinimalTest {

    static class RecordingOutputView extends OutputView {
        final List<String> log = new ArrayList<>();

        @Override
        public void showOutput(Object o) {
            log.add(String.valueOf(o));
        }

        List<String> snapshot() {
            return List.copyOf(log);
        }
    }

    private static RacingCars sampleRacingCars() {
        return new RacingCars(List.of(new RacingCar(new CarName("A"), new Distance(1)),
                new RacingCar(new CarName("B"), new Distance(1))));
    }

    @Test
    void count가_2면_헤더2줄_후_스냅샷_3번_찍고_최종상태를_반환한다() {
        RecordingOutputView recordingOutputView = new RecordingOutputView();
        RacingCarsService racingCarsService = new RacingCarsService(recordingOutputView);

        // given
        RacingCars racingCars = sampleRacingCars();
        UserInputNumber count = new UserInputNumber("3");

        // when
        RacingCars result = racingCarsService.moveAndLogSnapshots(count, racingCars);

        // then
        List<String> log = recordingOutputView.snapshot();
        assertThat(log).hasSize(2 + 3);
        assertThat(log.get(0)).isEqualTo("");
        assertThat(log.get(1)).isEqualTo("실행 결과");
        assertThat(log.getLast()).isEqualTo(result.toString());
    }
}