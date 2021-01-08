package uno.es.domain;

import static java.util.Optional.ofNullable;

public final class Card {
    private final CartNumber cartNumber;
    private final Color color;

    public Card(CartNumber cartNumber, Color color) throws InvalidCardException {
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
        return "Card{" +
                "cartNumber=" + cartNumber +
                ", color=" + color +
                '}';
    }
}
