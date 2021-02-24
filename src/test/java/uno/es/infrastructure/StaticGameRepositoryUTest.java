package uno.es.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uno.es.domain.Game;
import uno.es.domain.game.*;

import java.util.List;
import java.util.UUID;

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
    void find_should_return_deck() throws InvalidDeckIdException {
        // given
        final SimpleDeckCreated simpleDeckCreated = new SimpleDeckCreated();
        final List<CardDto> cardDtos = simpleDeckCreated.getCardDtos();

        final GameId gameId = simpleDeckCreated.getGameId();

        final DeckId deckId = new DeckId(UUID.randomUUID());
        final Deck deck = new Deck(gameId, deckId, cardDtos);

        final Game expectedGame = new Game(3, deck);

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