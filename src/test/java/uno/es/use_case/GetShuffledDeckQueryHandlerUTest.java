package uno.es.use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import uno.es.domain.ddd.QueryResponse;
import uno.es.domain.game.*;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetShuffledDeckQueryHandlerUTest {
    private GetShuffledDeckQueryHandler getShuffledDeckQueryHandler;

    private DeckRepository deckRepository;

    @BeforeEach
    void setUp() {
        deckRepository = mock(DeckRepository.class);
        getShuffledDeckQueryHandler = new GetShuffledDeckQueryHandler(deckRepository);
    }

    @Nested
    class HandleShould {
        private GetShuffledDeckQuery getShuffledDeckQuery;

        @BeforeEach
        void setUp() throws InvalidDeckIdException {
            DeckId deckId = new DeckId(UUID.randomUUID());
            getShuffledDeckQuery = new GetShuffledDeckQuery(deckId);

            final Deck deck = Deck.createNewDeck(deckId);
            deck.shuffle();

            when(deckRepository.find(deckId)).thenReturn(deck);
        }

        @Test
        void return_deck() {
            // given
            Deck expectedDeck = mock(Deck.class);
            when(deckRepository.find(getShuffledDeckQuery.getDeckId())).thenReturn(expectedDeck);

            // when
            final QueryResponse<Deck> deck = getShuffledDeckQueryHandler.handle(getShuffledDeckQuery);

            // then
            assertThat(deck.getValue()).isEqualTo(expectedDeck);
        }
    }

    @Nested
    class ListenToShouldReturn {

        @Test
        void getShuffledDeckQuery() {
            // when
            Class result = getShuffledDeckQueryHandler.listenTo();

            // then
            assertThat(result).isEqualTo(GetShuffledDeckQuery.class);
        }
    }
}