package uno.es.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uno.es.domain.Game;
import uno.es.domain.game.Deck;
import uno.es.domain.game.DeckId;
import uno.es.domain.game.DeckShuffledEvent;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class StaticGameRepositoryUTest {

    private StaticGameRepository staticDeckRepository;

    @BeforeEach
    void setUp() {
        staticDeckRepository = new StaticGameRepository();
    }

    @Test
    void findNewDeck_should_return_new_deck() {
        // given
        DeckId deckId = new DeckId();
        Deck expectedDeck =  Deck.createNewDeck(deckId);

        // when
        final Deck deck = staticDeckRepository.findNewDeck(deckId);

        // then
        assertThat(deck).isEqualTo(expectedDeck);
    }

    @Test
    void save_should_save_deck_events() {
        // given
        Game game = mock(Game.class);
        final DeckShuffledEvent deckShuffledEvent = mock(DeckShuffledEvent.class);
        when(game.getDeck().getGeneratedEvents()).thenReturn(singletonList(deckShuffledEvent));

        // when
        staticDeckRepository.save(game);

        // then
        assertThat(staticDeckRepository.getEvents()).usingFieldByFieldElementComparator().containsExactly(deckShuffledEvent);
    }
}