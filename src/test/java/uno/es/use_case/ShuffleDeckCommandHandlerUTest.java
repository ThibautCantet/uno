package uno.es.use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import uno.es.domain.Game;
import uno.es.domain.ddd.CommandResponse;
import uno.es.domain.game.*;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ShuffleDeckCommandHandlerUTest {
    private ShuffleDeckCommandHandler shuffleDeckCommandHandler;

    private GameRepository gameRepository;
    private Game game;

    @BeforeEach
    void setUp() {
        gameRepository = mock(GameRepository.class);
        shuffleDeckCommandHandler = new ShuffleDeckCommandHandler(gameRepository);
    }

    @Nested
    class HandleShould {
        private ShuffleDeckCommand shuffleDeckCommand;

        @BeforeEach
        void setUp() throws InvalidGameIdException {
            GameId gameId = new GameId(UUID.randomUUID());
            shuffleDeckCommand = new ShuffleDeckCommand(gameId);

            game = mock(Game.class);
            when(gameRepository.find(shuffleDeckCommand.getGameId())).thenReturn(game);
        }

        @Test
        void shuffle_deck() {
            // when
            shuffleDeckCommandHandler.handle(shuffleDeckCommand);

            // then
            verify(game).shuffle();
        }

        @Test
        void save_shuffled_deck() {
            // when
            shuffleDeckCommandHandler.handle(shuffleDeckCommand);

            // then
            verify(gameRepository).save(game);
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