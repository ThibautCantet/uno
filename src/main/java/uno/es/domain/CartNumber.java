package uno.es.domain;

public enum CartNumber {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    HEIGHT(8),
    NINE(9);

    private final int value;

    CartNumber(int value) {
        this.value = value;
    }

    public static CartNumber fromValue(int value) {
        for (CartNumber cartNumber : CartNumber.values()) {
            if (cartNumber.value == value) {
                return cartNumber;
            }
        }
        return null;
    }
}
