package model.domain;

import java.util.Objects;

public class RacingCar {
    private final CarName name;
    private final Distance distance;
    private static final int MIN_RANDOM_TO_MOVE = 4;

    public RacingCar(CarName name, Distance distance) {
        this.name = name;
        this.distance = distance;
    }

    public RacingCar moveRacingCar(int randomNumber) {
        if (randomNumber >= MIN_RANDOM_TO_MOVE) {
            return new RacingCar(name, distance.incrementDistance());
        }
        return this;
    }

    public boolean isFartherThan(RacingCar other) {
        return this.distance.isFartherThan(other.distance);
    }

    public boolean isSameDistanceAs(RacingCar other) {
        return this.distance.isSameAs(other.distance);
    }

    public String getResultName() {
        int size = name.toString().length();
        return name.toString().substring(0, size - 3);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RacingCar other)) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name.toString() + distance.toString();
    }
}
