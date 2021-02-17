package uno.es.use_case;

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
        // final Deck deck = deckRepository.findNewDeck(command.getGameId());

        // deck.shuffle();

        // deckRepository.save(deck);

        return new CommandResponse<>();
    }

    @Override
    public Class listenTo() {
        return DistributeCommand.class;
    }
}
