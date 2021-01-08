package uno.es.domain.game;

public interface DeckRepository {
    Deck findNewDeck(DeckId deckId);

    void save(Deck deck);

    Deck find(DeckId deckId);
}
