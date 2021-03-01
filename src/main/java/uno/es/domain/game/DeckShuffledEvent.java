package uno.es.domain.game;

import java.util.List;
import java.util.Objects;

public class DeckShuffledEvent implements GameEvent {

    private final List<Card> cards;
    private final GameId gameId;

    public DeckShuffledEvent(GameId gameId, List<Card> cards) {
        this.cards = cards;
        this.gameId = gameId;
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeckShuffledEvent)) return false;
        DeckShuffledEvent event2 = (DeckShuffledEvent) o;
        return Objects.equals(gameId, event2.gameId) && Objects.equals(cards, event2.cards);
    }

    @Override
    public GameId getGameId() {
        return gameId;
    }
}
