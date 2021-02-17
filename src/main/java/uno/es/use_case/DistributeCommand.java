package uno.es.use_case;

import uno.es.domain.ddd.Command;
import uno.es.domain.game.GameId;

public class DistributeCommand implements Command {
    private final GameId gameId;
    private final int numberOfPlayers;
    private final int numberOfDistributedCardsByPlayer;

    public DistributeCommand(GameId gameId, int numberOfPlayers, int numberOfDistributedCardsByPlayer) {
        this.gameId = gameId;
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfDistributedCardsByPlayer = numberOfDistributedCardsByPlayer;
    }

    public GameId getGameId() {
        return gameId;
    }
}
