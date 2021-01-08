package uno.es.infrastructure;

import uno.es.domain.game.CardShuffledEvent;
import uno.es.domain.game.Deck;
import uno.es.domain.game.DeckRepository;

import java.util.List;

public class StaticDeckRepository implements DeckRepository {
    @Override
    public Deck findNewDeck() {
        return Deck.createNewDeck();
    }

    @Override
    public void save(List<CardShuffledEvent> cardShuffledEvents) {

    }
}
