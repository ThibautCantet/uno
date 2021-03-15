package uno.es.use_case;

import uno.es.domain.Game;
import uno.es.domain.ddd.CommandHandler;
import uno.es.domain.ddd.CommandResponse;
import uno.es.infrastructure.StaticGameRepository;

public class AddPlayersCommandHandler implements CommandHandler<CommandResponse<Void>, AddPlayersCommand> {
    private final StaticGameRepository gameRepository;

    public AddPlayersCommandHandler(StaticGameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public CommandResponse<Void> handle(AddPlayersCommand command) {
        final Game game = gameRepository.find(command.getGameId());

        for (int i = 0; i < command.getNumberOfPlayers(); i++) {
            game.addPlayer(command.getGameId());
        }

        gameRepository.save(game);

        return new CommandResponse<>();
    }

    @Override
    public Class listenTo() {
        return null;
    }
}
