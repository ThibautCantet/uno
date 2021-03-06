package uno.es.use_case;

import uno.es.domain.ddd.Command;
import uno.es.domain.game.GameId;

public class ShuffleDeckCommand implements Command {
    private final GameId gameId;

    public ShuffleDeckCommand(GameId gameId) {
        this.gameId = gameId;
    }

    public GameId getGameId() {
        return gameId;
    }
}
