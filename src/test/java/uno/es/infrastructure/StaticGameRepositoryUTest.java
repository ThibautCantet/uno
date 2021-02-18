package uno.es.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uno.es.domain.Game;
import uno.es.domain.game.Deck;
import uno.es.domain.game.DeckShuffledEvent;
import uno.es.domain.game.GameId;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StaticGameRepositoryUTest {

    private StaticGameRepository staticGameRepository;

    @BeforeEach
    void setUp() {
        staticGameRepository = new StaticGameRepository();
    }

    @Test
    void find_should_return_deck() {
        // given
        GameId gameId = new GameId();
        Game expectedGame = mock(Game.class);

        // when
        final Game game = staticGameRepository.find(gameId);

        // then
        assertThat(game).isEqualTo(expectedGame);
    }

    @Test
    void save_should_save_deck_events() {
        // given
        Game game = mock(Game.class);
        final Deck deck = mock(Deck.class);
        when(game.getDeck()).thenReturn(deck);
        final DeckShuffledEvent deckShuffledEvent = mock(DeckShuffledEvent.class);
        when(game.getDeck().getGeneratedEvents()).thenReturn(singletonList(deckShuffledEvent));

        // when
        staticGameRepository.save(game);

        // then
        assertThat(staticGameRepository.getEvents()).usingFieldByFieldElementComparator().containsExactly(deckShuffledEvent);
    }
}