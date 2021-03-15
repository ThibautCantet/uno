package uno.es.domain.game;

public class CardDistributed implements GameEvent {
    private final GameId gameId;
    private final Card card;

    public CardDistributed(Card card, int playerId, GameId gameId) {
        this.card = card;
        this.gameId = gameId;
    }

    @Override
    public GameId getGameId() {
        return gameId;
    }

    public Card getCard() {
        return card;
    }
}
