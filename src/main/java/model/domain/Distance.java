package model.domain;

public class Distance {
    private final int value;
    public Distance(int distance) {
        this.value = distance;
    }

    public Distance incrementDistance() {
        return new Distance(value + 1);
    }

    public boolean isFartherThan(Distance other) {
        return this.value > other.value;
    }

    public boolean isSameAs(Distance other) {
        return this.value == other.value;
    }

    @Override
    public String toString() {
        return "-".repeat(value);
    }
}
