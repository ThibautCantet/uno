package uno.es.domain.game;

import static java.util.Optional.ofNullable;

public final class CardDto {
    private final CartNumber cartNumber;
    private final Color color;

    public CardDto(CartNumber cartNumber, Color color) throws InvalidCardException {
        this.cartNumber = ofNullable(cartNumber).orElseThrow(InvalidCardException::new);
        this.color = ofNullable(color).orElseThrow(InvalidCardException::new);;
    }

    public CartNumber getCartNumber() {
        return cartNumber;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "CardDto{" +
                "cartNumber=" + cartNumber +
                ", color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardDto)) return false;
        CardDto card = (CardDto) o;
        return cartNumber == card.cartNumber && color == card.color;
    }
}
