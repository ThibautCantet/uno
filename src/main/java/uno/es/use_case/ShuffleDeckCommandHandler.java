package uno.es.use_case;

import uno.es.domain.Game;
import uno.es.domain.ddd.CommandHandler;
import uno.es.domain.ddd.CommandResponse;
import uno.es.domain.game.GameRepository;

public class ShuffleDeckCommandHandler implements CommandHandler<CommandResponse<Void>, ShuffleDeckCommand> {

    private final GameRepository gameRepository;

    public ShuffleDeckCommandHandler(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public CommandResponse<Void> handle(ShuffleDeckCommand command) {
        final Game game = gameRepository.find(command.getGameId());

        game.shuffle();

        gameRepository.save(game);

        return new CommandResponse<>();
    }

    @Override
    public Class listenTo() {
        return ShuffleDeckCommand.class;
    }
}
