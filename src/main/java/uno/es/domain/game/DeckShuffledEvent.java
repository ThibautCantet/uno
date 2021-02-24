package uno.es.domain.game;

import java.util.List;
import java.util.Objects;

public class DeckShuffledEvent implements DeckEvent {

    private final DeckId deckId;
    private final List<Card> cards;
    private final GameId gameId;

    public DeckShuffledEvent(GameId gameId, DeckId deckId, List<Card> cards) {
        this.deckId = deckId;
        this.cards = cards;
        this.gameId = gameId;
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

    @Override
    public GameId getGameId() {
        return gameId;
    }
}
