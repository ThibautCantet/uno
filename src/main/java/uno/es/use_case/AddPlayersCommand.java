package uno.es.use_case;

import uno.es.domain.ddd.Command;
import uno.es.domain.game.GameId;

public class AddPlayersCommand implements Command {
    private final int numberOfPlayers;
    private final GameId gameId;

    public AddPlayersCommand(int numberOfPlayers, GameId gameId) {
        this.numberOfPlayers = numberOfPlayers;
        this.gameId = gameId;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public GameId getGameId() {
        return gameId;
    }
}
