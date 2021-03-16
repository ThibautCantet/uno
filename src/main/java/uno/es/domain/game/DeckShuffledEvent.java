package uno.es.domain.game;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DeckShuffledEvent implements GameEvent {

    private final List<CardDto> cardDtos;
    private final GameId gameId;

    public DeckShuffledEvent(GameId gameId, List<Card> cards) {
        this.cardDtos = cards.stream() .map(card -> new CardDto(card.getCartNumber(), card.getColor()))
                .collect(Collectors.toList());;
        this.gameId = gameId;
    }

    public List<CardDto> getCardDtos() {
        return cardDtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeckShuffledEvent)) return false;
        DeckShuffledEvent event2 = (DeckShuffledEvent) o;
        return Objects.equals(gameId, event2.gameId) && Objects.equals(cardDtos, event2.cardDtos);
    }

    @Override
    public GameId getGameId() {
        return gameId;
    }
}
