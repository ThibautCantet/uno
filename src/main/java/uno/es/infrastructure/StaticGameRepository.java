package uno.es.infrastructure;

import uno.es.domain.Game;
import uno.es.domain.game.*;

import java.util.ArrayList;
import java.util.List;


public class StaticGameRepository implements GameRepository {
    private final List<GameEvent> events = new ArrayList<>();

    @Override
    public void save(Game game) {
        events.addAll(game.getDeck().getGeneratedEvents());
    }

    @Override
    public Game find(GameId gameId) {
        int numberOfPlayersAdded = (int) events.stream()
        .filter(gameEvent -> gameEvent.getGameId().equals(gameId))
        .filter(gameEvent -> gameEvent instanceof PlayerAdded)
        .count();

        final GameEvent simpleDeckCreated = events.stream()
                .filter(gameEvent -> gameEvent.getGameId().equals(gameId))
                .filter(gameEvent -> gameEvent instanceof SimpleDeckCreated)
                .findFirst().get();
        final List<CardDto> cardDtos = ((SimpleDeckCreated) simpleDeckCreated).getCardDtos();
        final Deck deck = new Deck(gameId, cardDtos);

        return new Game(numberOfPlayersAdded, deck);
    }

    List<GameEvent> getEvents() {
        return events;
    }

    public void addEvent(GameEvent event) {
        events.add(event);
    }
}
