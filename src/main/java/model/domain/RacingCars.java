package model.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class RacingCars {
    private final List<RacingCar> racingCars;
    private final static int REQUIRED_RACING_CAR_SIZE = 2;
    private static final int RANGE_START = 0;
    private static final int RANGE_END = 9;
    public RacingCars(List<RacingCar> racingCars) {
        validate(racingCars);
        this.racingCars = List.copyOf(racingCars);
    }

    private static void validate(List<RacingCar> values) {
        requireAtLeastTwoRacingCars(values);
        requireDistinctRacingCarNames(values);
    }

    private static void requireAtLeastTwoRacingCars(List<RacingCar> values) {
        int size = values.size();
        if (size < REQUIRED_RACING_CAR_SIZE) {
            throw new IllegalArgumentException("자동차는 2대 이상 존재해야 합니다");
        }
    }

    private static void requireDistinctRacingCarNames(List<RacingCar> values) {
        if (new HashSet<>(values).size() != values.size()) {
            throw new IllegalArgumentException("중복된 자동차 이름이 존재합니다");
        }
    }

    public RacingCars moveRacingCars() {
        List<RacingCar> newRacingCars = new ArrayList<>();
        for (RacingCar racingCar : racingCars) {
            newRacingCars.add(racingCar.moveRacingCar(Randoms.pickNumberInRange(RANGE_START, RANGE_END)));
        }
        return new RacingCars(newRacingCars);
    }

    public RacingCar findWinner() {
        RacingCar winner = racingCars.getFirst();
        for (RacingCar racingCar : racingCars) {
            if (racingCar.isFartherThan(winner)) {
                winner = racingCar;
            }
        }
        return winner;
    }

    public Winners findWinners() {
        RacingCar winner = findWinner();
        List<RacingCar> winners = new ArrayList<>();

        for (RacingCar car : racingCars) {
            if (car.isSameDistanceAs(winner)) {
                winners.add(car);
            }
        }
        return new Winners(winners);
    }

    @Override
    public String toString() {
        return racingCars.stream().
                map(Object::toString).
                collect(Collectors.joining("\n", "", "\n"));
    }

}
