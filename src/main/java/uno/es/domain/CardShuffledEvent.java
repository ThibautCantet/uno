package uno.es.domain;

import java.util.Objects;

public class CardShuffledEvent implements Event {

    private final DeckId deckId;
    private final Card card;
    private final Integer newPosition;

    public CardShuffledEvent(DeckId deckId, Card card, Integer newPosition) {
        this.deckId = deckId;
        this.card = card;
        this.newPosition = newPosition;
    }

    public DeckId getDeckId() {
        return deckId;
    }

    public Card getCard() {
        return card;
    }

    public Integer getNewPosition() {
        return newPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardShuffledEvent)) return false;
        CardShuffledEvent that = (CardShuffledEvent) o;
        return Objects.equals(deckId, that.deckId) && Objects.equals(card, that.card) && Objects.equals(newPosition, that.newPosition);
    }

    @Override
    public String toString() {
        return "CardShuffledEvent{" +
                "deckId=" + deckId +
                ", card=" + card +
                ", newPosition=" + newPosition +
                '}';
    }
}
