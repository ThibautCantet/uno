package uno.es.infrastructure;

import uno.es.domain.Game;
import uno.es.domain.game.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class StaticGameRepository implements GameRepository {
    private final List<GameEvent> events = new ArrayList<>();

    @Override
    public void save(Game game) {
        events.addAll(game.getDeck().getGeneratedEvents());
    }

    @Override
    public Game find(GameId gameId) {
        int numberOfPlayersAdded = initializeNumberOfPlayersAdded(gameId);

        final List<CardDto> allSimpleDeckCardDtos = initializeCardDtos(gameId);

        final Deck deck = new Deck(gameId, allSimpleDeckCardDtos);

        return new Game(numberOfPlayersAdded, deck);
    }

    private int initializeNumberOfPlayersAdded(GameId gameId) {
        return (int) events.stream()
        .filter(gameEvent -> gameEvent.getGameId().equals(gameId))
        .filter(gameEvent -> gameEvent instanceof PlayerAdded)
        .count();
    }

    private List<CardDto> initializeCardDtos(GameId gameId) {
        final GameEvent simpleDeckCreated = events.stream()
                .filter(gameEvent -> gameEvent.getGameId().equals(gameId))
                .filter(gameEvent -> gameEvent instanceof SimpleDeckCreated)
                .findFirst().get();
        final List<CardDto> cardDtos = ((SimpleDeckCreated) simpleDeckCreated).getCardDtos();

        final List<CardDto> distributedCards = events.stream()
                .filter(gameEvent -> gameEvent instanceof CardDistributed)
                .map(gameEvent -> ((CardDistributed) gameEvent).getCard())
                .map(card -> new CardDto(card.getCartNumber(), card.getColor()))
                .collect(Collectors.toList());

        cardDtos.removeAll(distributedCards);
        return cardDtos;
    }

    List<GameEvent> getEvents() {
        return events;
    }

    public void addEvent(GameEvent event) {
        events.add(event);
    }
}
