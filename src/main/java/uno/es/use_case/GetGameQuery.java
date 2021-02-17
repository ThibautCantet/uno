package uno.es.use_case;

import uno.es.domain.ddd.Query;
import uno.es.domain.game.GameId;

public class GetGameQuery implements Query {
    private final GameId gameId;

    public GetGameQuery(GameId gameId) {
        this.gameId = gameId;
    }

    public GameId getGameId() {
        return gameId;
    }
}
