package uno.es.use_case;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import uno.es.domain.game.DeckId;
import uno.es.domain.game.GameId;

import static org.assertj.core.api.Assertions.assertThat;

class ShuffleDeckCommandUTest {

    @Nested
    class ConstructorShould {
        @Test
        void bind_deckId() {
            // given
            final GameId gameId = new GameId();

            // when
            ShuffleDeckCommand shuffleDeckCommand  = new ShuffleDeckCommand(gameId);

            // then
            assertThat(shuffleDeckCommand.getGameId()).isEqualTo(gameId);
        }
    }
}