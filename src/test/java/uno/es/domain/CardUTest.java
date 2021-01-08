package uno.es.domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class CardUTest {

    public static final CartNumber CARD_NUMBER = CartNumber.ONE;
    private static final Color COLOR = Color.BLUE;

    @Nested
    class ConstructorShould {
        @Test
        void bind_cardNumber() throws InvalidCardException {
            // given
            final Card card = new Card(CARD_NUMBER, COLOR);

            // then
            assertThat(card.getCartNumber()).isEqualTo(CARD_NUMBER);
        }

        @Test
        void bind_color() throws InvalidCardException {
            // given
            final Card card = new Card(CARD_NUMBER, COLOR);

            // then
            assertThat(card.getColor()).isEqualTo(COLOR);
        }

        @Nested
        class ThrowInvalidCardExceptionWhen {
            @Test
            void cardNumber_is_null() {
                // given
                final Throwable throwable = catchThrowable(() -> new Card(null, COLOR));

                // then
                assertThat(throwable).isInstanceOf(InvalidCardException.class);
            }

            @Test
            void color_is_null() {
                // given
                final Throwable throwable = catchThrowable(() -> new Card(CARD_NUMBER, null));

                // then
                assertThat(throwable).isInstanceOf(InvalidCardException.class);
            }
        }
    }

    @Test
    void toString_should_serialize_cardNumber_and_color() throws InvalidCardException {
        // given
        final Card card = new Card(CARD_NUMBER, COLOR);

        // when
        final String toString = card.toString();

        // thenR
        assertThat(toString).isEqualTo("Card{cartNumber=ONE, color=BLUE}");
    }
}