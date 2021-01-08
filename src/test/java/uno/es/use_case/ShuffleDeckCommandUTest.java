package uno.es.use_case;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import uno.es.domain.DeckId;

import static org.assertj.core.api.Assertions.assertThat;

class ShuffleDeckCommandUTest {

    @Nested
    class ConstructorShould {
        @Test
        void bind_deckId() {
            // given
            final DeckId deckId = new DeckId();

            // when
            ShuffleDeckCommand shuffleDeckCommand  = new ShuffleDeckCommand(deckId);

            // then
            assertThat(shuffleDeckCommand.getDeckId()).isEqualTo(deckId);
        }
    }
}