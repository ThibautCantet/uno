package uno.es.infrastructure;

import uno.es.domain.game.*;

import java.util.ArrayList;
import java.util.List;


public class StaticDeckRepository implements DeckRepository {
    private final List<DeckEvent> events = new ArrayList<>();

    @Override
    public Deck findNewDeck(DeckId deckId) {
        return Deck.createNewDeck(deckId);
    }

    @Override
    public void save(Deck deck) {
        events.addAll(deck.getGeneratedEvents());
    }

    @Override
    public Deck find(DeckId deckId) {
        return null;
    }

    List<DeckEvent> getEvents() {
        return events;
    }
}
