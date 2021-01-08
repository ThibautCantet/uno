package uno.es.infrastructure;

import uno.es.domain.game.*;


public class StaticDeckRepository implements DeckRepository {
    @Override
    public Deck findNewDeck(DeckId deckId) {
        return Deck.createNewDeck(deckId);
    }

    @Override
    public void save(Deck deck) {

    }

    @Override
    public Deck find(DeckId deckId) {
        return null;
    }
}
