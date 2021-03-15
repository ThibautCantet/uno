package uno.es.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uno.es.domain.Game;
import uno.es.domain.game.*;

import java.util.List;

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
        staticGameRepository.addEvent(new SimpleDeckCreated());

        final SimpleDeckCreated simpleDeckCreated = new SimpleDeckCreated();
        staticGameRepository.addEvent(simpleDeckCreated);

        final GameId gameId = simpleDeckCreated.getGameId();

        final PlayerAdded player1Added = new PlayerAdded(gameId);
        final PlayerAdded player2Added = new PlayerAdded(gameId);
        final PlayerAdded player3Added = new PlayerAdded(gameId);

        staticGameRepository.addEvent(player1Added);
        staticGameRepository.addEvent(player2Added);
        staticGameRepository.addEvent(player3Added);

        final CartNumber one = CartNumber.ONE;
        final Color blue = Color.BLUE;
        final CardDistributed oneBlueCardDistributed = new CardDistributed(new Card(one, blue), 1, gameId);
        staticGameRepository.addEvent(oneBlueCardDistributed);

        // when
        final Game game = staticGameRepository.find(gameId);

        // then
        final List<CardDto> cardDtosExceptOneBlueCard = simpleDeckCreated.getCardDtos();
        cardDtosExceptOneBlueCard.remove(new CardDto(one, blue));

        final Deck deck = new Deck(gameId, cardDtosExceptOneBlueCard);
        final int numberOfPlayersAdded = 3;

        final Game expectedGame = new Game(numberOfPlayersAdded, deck);
        assertThat(game).usingRecursiveComparison().isEqualTo(expectedGame);
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