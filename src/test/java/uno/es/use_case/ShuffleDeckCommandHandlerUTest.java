package uno.es.use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import uno.es.domain.ddd.CommandResponse;
import uno.es.domain.game.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ShuffleDeckCommandHandlerUTest {
    private ShuffleDeckCommandHandler shuffleDeckCommandHandler;

    private DeckRepository deckRepository;
    private Deck deck;

    @BeforeEach
    void setUp() {
        deckRepository = mock(DeckRepository.class);
        shuffleDeckCommandHandler = new ShuffleDeckCommandHandler(deckRepository);
    }

    @Nested
    class HandleShould {
        private ShuffleDeckCommand shuffleDeckCommand;

        @BeforeEach
        void setUp() throws InvalidDeckIdException {
            DeckId deckId = new DeckId(UUID.randomUUID());
            shuffleDeckCommand = new ShuffleDeckCommand(deckId);

            deck = Deck.createNewDeck(deckId);
            when(deckRepository.findNewDeck(shuffleDeckCommand.getDeckId())).thenReturn(deck);
        }

        @Test
        void shuffle_deck() {
            // given
            Deck spyedDeck = mock(Deck.class);
            when(deckRepository.findNewDeck(shuffleDeckCommand.getDeckId())).thenReturn(spyedDeck);
            when(spyedDeck.getCards()).thenReturn(deck.getCards());

            // when
            shuffleDeckCommandHandler.handle(shuffleDeckCommand);

            // then
            verify(spyedDeck).shuffle();
        }

        @Test
        void save_shuffled_deck() {
            // given
            Deck spyedDeck = mock(Deck.class);
            when(spyedDeck.getId()).thenReturn(deck.getId());
            when(deckRepository.findNewDeck(shuffleDeckCommand.getDeckId())).thenReturn(spyedDeck);

            // when
            shuffleDeckCommandHandler.handle(shuffleDeckCommand);

            // then
            verify(deckRepository).save(spyedDeck);
        }

        @Test
        void return_null_asValue() {
            // when
            CommandResponse<Void> result = shuffleDeckCommandHandler.handle(shuffleDeckCommand);

            // then
            assertThat(result.getValue()).isNull();
        }
    }

    @Nested
    class ListenToShouldReturn {

        @Test
        void shuffleDeckCommand() {
            // when
            Class result = shuffleDeckCommandHandler.listenTo();

            // then
            assertThat(result).isEqualTo(ShuffleDeckCommand.class);
        }
    }
}