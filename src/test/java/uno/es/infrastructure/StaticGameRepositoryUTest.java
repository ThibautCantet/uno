package uno.es.infrastructure;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uno.es.domain.Game;
import uno.es.domain.game.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static uno.es.domain.game.CartNumber.ONE;
import static uno.es.domain.game.Color.BLUE;

class StaticGameRepositoryUTest {

    private StaticGameRepository staticGameRepository;

    @BeforeEach
    void setUp() {
        staticGameRepository = new StaticGameRepository();
    }

    @Test
    void find_should_return_deck() {
        // given
        final SimpleDeckCreated simpleDeckCreated = new SimpleDeckCreated();
        staticGameRepository.addEvent(simpleDeckCreated);

        final GameId gameId = simpleDeckCreated.getGameId();

        final List<Card> initialOrderedCards = simpleDeckCreated.getCardDtos().stream()
                .map(cardDto -> new Card(cardDto.getCartNumber(), cardDto.getColor()))
                .collect(Collectors.toList());

        final DeckShuffledEvent deckShuffledEvent = new DeckShuffledEvent(gameId, initialOrderedCards);
        staticGameRepository.addEvent(deckShuffledEvent);

        final PlayerAdded player1Added = new PlayerAdded(gameId);
        final PlayerAdded player2Added = new PlayerAdded(gameId);
        final PlayerAdded player3Added = new PlayerAdded(gameId);

        staticGameRepository.addEvent(player1Added);
        staticGameRepository.addEvent(player2Added);
        staticGameRepository.addEvent(player3Added);

        final Card oneBlueCard = new Card(ONE, BLUE);
        final CardDistributed firstOneBlueCardDistributed = new CardDistributed(oneBlueCard, 1, gameId);
        final CardDistributed secondOneBlueCardDistributed = new CardDistributed(oneBlueCard, 1, gameId);
        staticGameRepository.addEvent(firstOneBlueCardDistributed);
        staticGameRepository.addEvent(secondOneBlueCardDistributed);

        // when
        final Game game = staticGameRepository.find(gameId);

        // then
        assertThat(game.getDeckCards().size()).isEqualTo(74);

        final Game expectedGame = buildExpectedGame(simpleDeckCreated, gameId);
        assertThat(game).usingRecursiveComparison().isEqualTo(expectedGame);
    }

    @NotNull
    private Game buildExpectedGame(SimpleDeckCreated simpleDeckCreated, GameId gameId) {
        List<CardDto> cardDtosExceptOneBlue = simpleDeckCreated.getCardDtos().stream()
                .filter(cardDto -> !cardDto.equals(new CardDto(ONE, BLUE)))
                .collect(Collectors.toList());

        final Deck deck = new Deck(gameId, cardDtosExceptOneBlue);
        final int numberOfPlayersAdded = 3;

        return new Game(numberOfPlayersAdded, deck);
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