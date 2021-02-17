package uno.es.infrastructure;

import uno.es.domain.Game;
import uno.es.domain.game.*;

import java.util.ArrayList;
import java.util.List;


public class StaticGameRepository implements GameRepository {
    private final List<DeckEvent> events = new ArrayList<>();

    @Override
    public Deck findNewDeck(DeckId deckId) {
        return Deck.createNewDeck(deckId);
    }

    @Override
    public void save(Game game) {
        events.addAll(game.getDeck().getGeneratedEvents());
    }

    @Override
    public Game find(GameId gameId) {
        return null;
    }

    List<DeckEvent> getEvents() {
        return events;
    }

    public void addEvent(DeckEvent event) {
        events.add(event);
    }
}
