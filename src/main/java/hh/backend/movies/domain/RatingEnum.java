package hh.backend.movies.domain;

public enum RatingEnum {
    Zero(0),
    One(1),
    Two(2),
    Three(3),
    Four(4),
    Five(5);

    private final int value;

    RatingEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
