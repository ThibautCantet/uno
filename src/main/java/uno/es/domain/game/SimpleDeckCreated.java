package uno.es.domain.game;

import java.util.ArrayList;
import java.util.List;

public class SimpleDeckCreated implements DeckEvent {
    private final List<CardDto> cardDtos;

    public SimpleDeckCreated() {
        cardDtos = initializeCards();
    }

    private List<CardDto> initializeCards() {
        final List<CardDto> cards;
        cards = new ArrayList<>();
        try {
            cards.add(new CardDto(CartNumber.ZERO, Color.BLUE));
            cards.add(new CardDto(CartNumber.ONE, Color.BLUE));
            cards.add(new CardDto(CartNumber.ONE, Color.BLUE));
            cards.add(new CardDto(CartNumber.TWO, Color.BLUE));
            cards.add(new CardDto(CartNumber.TWO, Color.BLUE));
            cards.add(new CardDto(CartNumber.THREE, Color.BLUE));
            cards.add(new CardDto(CartNumber.THREE, Color.BLUE));
            cards.add(new CardDto(CartNumber.FOUR, Color.BLUE));
            cards.add(new CardDto(CartNumber.FOUR, Color.BLUE));
            cards.add(new CardDto(CartNumber.FIVE, Color.BLUE));
            cards.add(new CardDto(CartNumber.FIVE, Color.BLUE));
            cards.add(new CardDto(CartNumber.SIX, Color.BLUE));
            cards.add(new CardDto(CartNumber.SIX, Color.BLUE));
            cards.add(new CardDto(CartNumber.SEVEN, Color.BLUE));
            cards.add(new CardDto(CartNumber.SEVEN, Color.BLUE));
            cards.add(new CardDto(CartNumber.EIGHT, Color.BLUE));
            cards.add(new CardDto(CartNumber.EIGHT, Color.BLUE));
            cards.add(new CardDto(CartNumber.NINE, Color.BLUE));
            cards.add(new CardDto(CartNumber.NINE, Color.BLUE));
            cards.add(new CardDto(CartNumber.ZERO, Color.RED));
            cards.add(new CardDto(CartNumber.ONE, Color.RED));
            cards.add(new CardDto(CartNumber.ONE, Color.RED));
            cards.add(new CardDto(CartNumber.TWO, Color.RED));
            cards.add(new CardDto(CartNumber.TWO, Color.RED));
            cards.add(new CardDto(CartNumber.THREE, Color.RED));
            cards.add(new CardDto(CartNumber.THREE, Color.RED));
            cards.add(new CardDto(CartNumber.FOUR, Color.RED));
            cards.add(new CardDto(CartNumber.FOUR, Color.RED));
            cards.add(new CardDto(CartNumber.FIVE, Color.RED));
            cards.add(new CardDto(CartNumber.FIVE, Color.RED));
            cards.add(new CardDto(CartNumber.SIX, Color.RED));
            cards.add(new CardDto(CartNumber.SIX, Color.RED));
            cards.add(new CardDto(CartNumber.SEVEN, Color.RED));
            cards.add(new CardDto(CartNumber.SEVEN, Color.RED));
            cards.add(new CardDto(CartNumber.EIGHT, Color.RED));
            cards.add(new CardDto(CartNumber.EIGHT, Color.RED));
            cards.add(new CardDto(CartNumber.NINE, Color.RED));
            cards.add(new CardDto(CartNumber.NINE, Color.RED));
            cards.add(new CardDto(CartNumber.ZERO, Color.YELLOW));
            cards.add(new CardDto(CartNumber.ONE, Color.YELLOW));
            cards.add(new CardDto(CartNumber.ONE, Color.YELLOW));
            cards.add(new CardDto(CartNumber.TWO, Color.YELLOW));
            cards.add(new CardDto(CartNumber.TWO, Color.YELLOW));
            cards.add(new CardDto(CartNumber.THREE, Color.YELLOW));
            cards.add(new CardDto(CartNumber.FOUR, Color.YELLOW));
            cards.add(new CardDto(CartNumber.FOUR, Color.YELLOW));
            cards.add(new CardDto(CartNumber.FOUR, Color.YELLOW));
            cards.add(new CardDto(CartNumber.FIVE, Color.YELLOW));
            cards.add(new CardDto(CartNumber.FIVE, Color.YELLOW));
            cards.add(new CardDto(CartNumber.SIX, Color.YELLOW));
            cards.add(new CardDto(CartNumber.SIX, Color.YELLOW));
            cards.add(new CardDto(CartNumber.SEVEN, Color.YELLOW));
            cards.add(new CardDto(CartNumber.SEVEN, Color.YELLOW));
            cards.add(new CardDto(CartNumber.EIGHT, Color.YELLOW));
            cards.add(new CardDto(CartNumber.EIGHT, Color.YELLOW));
            cards.add(new CardDto(CartNumber.NINE, Color.YELLOW));
            cards.add(new CardDto(CartNumber.NINE, Color.YELLOW));
            cards.add(new CardDto(CartNumber.ZERO, Color.GREEN));
            cards.add(new CardDto(CartNumber.ONE, Color.GREEN));
            cards.add(new CardDto(CartNumber.ONE, Color.GREEN));
            cards.add(new CardDto(CartNumber.TWO, Color.GREEN));
            cards.add(new CardDto(CartNumber.TWO, Color.GREEN));
            cards.add(new CardDto(CartNumber.THREE, Color.GREEN));
            cards.add(new CardDto(CartNumber.THREE, Color.GREEN));
            cards.add(new CardDto(CartNumber.FOUR, Color.GREEN));
            cards.add(new CardDto(CartNumber.FOUR, Color.GREEN));
            cards.add(new CardDto(CartNumber.FIVE, Color.GREEN));
            cards.add(new CardDto(CartNumber.FIVE, Color.GREEN));
            cards.add(new CardDto(CartNumber.SIX, Color.GREEN));
            cards.add(new CardDto(CartNumber.SIX, Color.GREEN));
            cards.add(new CardDto(CartNumber.SEVEN, Color.GREEN));
            cards.add(new CardDto(CartNumber.SEVEN, Color.GREEN));
            cards.add(new CardDto(CartNumber.EIGHT, Color.GREEN));
            cards.add(new CardDto(CartNumber.EIGHT, Color.GREEN));
            cards.add(new CardDto(CartNumber.NINE, Color.GREEN));
            cards.add(new CardDto(CartNumber.NINE, Color.GREEN));
        } catch (InvalidCardException e) {
            e.printStackTrace();
        }
        return cards;
    }

    public List<CardDto> getCardDtos() {
        return cardDtos;
    }
}
