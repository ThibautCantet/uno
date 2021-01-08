package uno.es.infrastructure;

import uno.es.domain.game.CardShuffledEvent;
import uno.es.domain.game.Deck;
import uno.es.domain.game.DeckId;
import uno.es.domain.game.DeckRepository;

import java.util.List;

public class StaticDeckRepository implements DeckRepository {
    @Override
    public Deck findNewDeck(DeckId deckId) {
        return Deck.createNewDeck(deckId);
    }

    @Override
    public void save(List<CardShuffledEvent> cardShuffledEvents) {

    }
}
