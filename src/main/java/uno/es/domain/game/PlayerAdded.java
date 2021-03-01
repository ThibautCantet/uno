package uno.es.domain.game;

public class PlayerAdded implements GameEvent {
    private final GameId gameId;

    public PlayerAdded(GameId gameId) {
        this.gameId = gameId;
    }

    @Override
    public GameId getGameId() {
        return gameId;
    }
}
