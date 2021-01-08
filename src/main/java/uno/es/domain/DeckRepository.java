package uno.es.domain;

import java.util.List;

public interface DeckRepository {
    Deck findNewDeck();

    void save(List<CardShuffledEvent> cardShuffledEvents);
}
