package uno.es.domain.game;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import uno.es.domain.game.DeckId;
import uno.es.domain.game.InvalidDeckIdException;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class DeckIdUTest {

    public static final UUID DECK_ID_UUID = UUID.randomUUID();

    @Nested
    class Constructor {
        @Test
        void can_take_no_parameter() {
            // when
            final DeckId deckId = new DeckId();

            // then
            assertThat(UUID.fromString(deckId.toString())).isNotNull();
        }

        @Test
        void can_take_uuid_as_parameter() throws InvalidDeckIdException {
            // when
            final DeckId deckId = new DeckId(DECK_ID_UUID);

            // then
            assertThat(deckId.toString()).isEqualTo(DECK_ID_UUID.toString());
        }

        @Nested
        class ThrowInvalidDeckIdExceptionWhen {
            @Test
            void cardNumber_is_null() {
                // given
                final Throwable throwable = catchThrowable(() -> new DeckId(null));

                // then
                assertThat(throwable).isInstanceOf(InvalidDeckIdException.class);
            }
        }
    }

    @Test
    void toString_should_serialize_uuid() throws InvalidDeckIdException {
        // given
        final DeckId deckId = new DeckId(DECK_ID_UUID);

        // when
        final String toString = deckId.toString();

        // thenR
        assertThat(toString).isEqualTo(DECK_ID_UUID.toString());
    }
}