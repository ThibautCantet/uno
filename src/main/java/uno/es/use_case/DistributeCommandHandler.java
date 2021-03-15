package uno.es.use_case;

import uno.es.domain.Game;
import uno.es.domain.ddd.CommandHandler;
import uno.es.domain.ddd.CommandResponse;
import uno.es.domain.game.GameRepository;

public class DistributeCommandHandler implements CommandHandler<CommandResponse<Void>, DistributeCommand> {

    private final GameRepository gameRepository;

    public DistributeCommandHandler(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public CommandResponse<Void> handle(DistributeCommand command) {
        final Game game = gameRepository.find(command.getGameId());

        game.distribute(command.getNumberOfDistributedCardsByPlayer());

        gameRepository.save(game);

        return new CommandResponse<>();
    }

    @Override
    public Class listenTo() {
        return DistributeCommand.class;
    }
}
