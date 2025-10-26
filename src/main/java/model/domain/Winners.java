package model.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Winners {
    private final List<RacingCar> winners;

    public Winners(List<RacingCar> racingCars) {
        this.winners = List.copyOf(racingCars);
    }

    public String toResult() {
        return winners.stream()
                .map(RacingCar::getResultName)
                .collect(Collectors.joining(", ", "최종 우승자 : ", ""));
    }
}
