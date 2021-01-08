package uno.es.use_case;

import uno.es.domain.ddd.CommandHandler;
import uno.es.domain.game.CardShuffledEvent;
import uno.es.domain.ddd.CommandResponse;
import uno.es.domain.game.Deck;
import uno.es.domain.game.DeckRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShuffleDeckCommandHandler implements CommandHandler<CommandResponse<Void>, ShuffleDeckCommand> {

    private final DeckRepository deckRepository;

    public ShuffleDeckCommandHandler(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    @Override
    public CommandResponse<Void> handle(ShuffleDeckCommand command) {
        final Deck deck = deckRepository.findNewDeck();

        deck.shuffle();

        final List<CardShuffledEvent> events = generateEvent(deck);

        deckRepository.save(events);

        return new CommandResponse<>();
    }

    private List<CardShuffledEvent> generateEvent(Deck deck) {
        return IntStream
            .range(0, deck.getCards().size())
            .mapToObj(index -> new CardShuffledEvent(deck.getId(), deck.getCards().get(index), index + 1))
            .collect(Collectors.toList());
    }

    @Override
    public Class listenTo() {
        return ShuffleDeckCommand.class;
    }
}
