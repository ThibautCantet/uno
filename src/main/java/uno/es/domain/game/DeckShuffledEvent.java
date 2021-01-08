package uno.es.domain.game;

import uno.es.domain.ddd.Event;

import java.util.List;
import java.util.Objects;

public class DeckShuffledEvent implements Event {

    private final DeckId deckId;
    private final List<Card> cards;

    public DeckShuffledEvent(DeckId deckId, List<Card> cards) {
        this.deckId = deckId;
        this.cards = cards;
    }

    public DeckId getDeckId() {
        return deckId;
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeckShuffledEvent)) return false;
        DeckShuffledEvent event2 = (DeckShuffledEvent) o;
        return Objects.equals(deckId, event2.deckId) && Objects.equals(cards, event2.cards);
    }
}
