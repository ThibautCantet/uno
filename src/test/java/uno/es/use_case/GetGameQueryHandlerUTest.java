package uno.es.use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import uno.es.domain.Game;
import uno.es.domain.ddd.QueryResponse;
import uno.es.domain.game.*;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetGameQueryHandlerUTest {
    private GetGameQueryHandler getGameQueryHandler;

    private GameRepository gameRepository;

    @BeforeEach
    void setUp() {
        gameRepository = mock(GameRepository.class);
        getGameQueryHandler = new GetGameQueryHandler(gameRepository);
    }

    @Nested
    class HandleShould {
        private GetGameQuery getGameQuery;

        @BeforeEach
        void setUp() throws InvalidGameIdException {
            GameId gameId = new GameId(UUID.randomUUID());
            getGameQuery = new GetGameQuery(gameId);

            final Game game = mock(Game.class);//Deck.createNewDeck(gameId);
            //game.shuffle();

            when(gameRepository.find(gameId)).thenReturn(game);
        }

        @Test
        void return_game() {
            // given
            Game expectedGame = mock(Game.class);
            when(gameRepository.find(getGameQuery.getGameId())).thenReturn(expectedGame);

            // when
            final QueryResponse<Game> game = getGameQueryHandler.handle(getGameQuery);

            // then
            assertThat(game.getValue()).isEqualTo(expectedGame);
        }
    }

    @Nested
    class ListenToShouldReturn {

        @Test
        void getShuffledDeckQuery() {
            // when
            Class result = getGameQueryHandler.listenTo();

            // then
            assertThat(result).isEqualTo(GetGameQuery.class);
        }
    }
}