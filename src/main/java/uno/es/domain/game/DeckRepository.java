package uno.es.domain.game;

import java.util.List;

public interface DeckRepository {
    Deck findNewDeck();

    void save(List<CardShuffledEvent> cardShuffledEvents);
}
