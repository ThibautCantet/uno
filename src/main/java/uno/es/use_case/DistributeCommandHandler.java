package uno.es.use_case;

import uno.es.domain.ddd.CommandHandler;
import uno.es.domain.ddd.CommandResponse;
import uno.es.domain.game.Deck;
import uno.es.domain.game.DeckRepository;

public class DistributeCommandHandler implements CommandHandler<CommandResponse<Void>, DistributeCommand> {

    private final DeckRepository deckRepository;

    public DistributeCommandHandler(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    @Override
    public CommandResponse<Void> handle(DistributeCommand command) {
        // final Deck deck = deckRepository.findNewDeck(command.getDeckId());

        // deck.shuffle();

        // deckRepository.save(deck);

        return new CommandResponse<>();
    }

    @Override
    public Class listenTo() {
        return DistributeCommand.class;
    }
}
