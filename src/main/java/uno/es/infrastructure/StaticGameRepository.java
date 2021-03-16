package uno.es.infrastructure;

import uno.es.domain.Game;
import uno.es.domain.game.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

        List<CardDto> shuffledCardDtos = cardDtos;
        final Optional<GameEvent> optionalDeckShuffledEvent = events.stream()
                .filter(gameEvent -> gameEvent.getGameId().equals(gameId))
                .filter(gameEvent -> gameEvent instanceof DeckShuffledEvent)
                .findFirst();
        if (optionalDeckShuffledEvent.isPresent()) {
            shuffledCardDtos = ((DeckShuffledEvent) optionalDeckShuffledEvent.get()).getCardDtos();
        }

        final List<CardDto> distributedCardDtos = events.stream()
                .filter(gameEvent -> gameEvent instanceof CardDistributed)
                .map(gameEvent -> ((CardDistributed) gameEvent).getCard())
                .map(card -> new CardDto(card.getCartNumber(), card.getColor()))
                .collect(Collectors.toList());

        return removeDistributedCards(shuffledCardDtos, distributedCardDtos);
    }

    private List<CardDto> removeDistributedCards(List<CardDto> shuffledCardDtos, List<CardDto> distributedCardDtos) {
        List<CardDto> result = shuffledCardDtos;
        for (CardDto distributedCardDto : distributedCardDtos) {
            for (int i = 0; i < result.size(); i++) {
                CardDto shuffledCartDto = result.get(i);
                if (shuffledCartDto.equals(distributedCardDto)) {
                    result.remove(i);
                }
            }
        }

        return result;
    }

    List<GameEvent> getEvents() {
        return events;
    }

    public void addEvent(GameEvent event) {
        events.add(event);
    }
}
