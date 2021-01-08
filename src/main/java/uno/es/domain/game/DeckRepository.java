package uno.es.domain.game;

import java.util.List;

public interface DeckRepository {
    Deck findNewDeck(DeckId deckId);

    void save(List<CardShuffledEvent> cardShuffledEvents);
}
