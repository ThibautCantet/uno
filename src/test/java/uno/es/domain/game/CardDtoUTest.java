package uno.es.domain.game;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class CardDtoUTest {

    public static final CartNumber CARD_NUMBER = CartNumber.ONE;
    private static final Color COLOR = Color.BLUE;

    @Nested
    class ConstructorShould {
        @Test
        void bind_cardNumber() throws InvalidCardException {
            // given
            final CardDto cardDto = new CardDto(CARD_NUMBER, COLOR);

            // then
            assertThat(cardDto.getCartNumber()).isEqualTo(CARD_NUMBER);
        }

        @Test
        void bind_color() throws InvalidCardException {
            // given
            final CardDto cardDto = new CardDto(CARD_NUMBER, COLOR);

            // then
            assertThat(cardDto.getColor()).isEqualTo(COLOR);
        }

        @Nested
        class ThrowInvalidCardExceptionWhen {
            @Test
            void cardNumber_is_null() {
                // given
                final Throwable throwable = catchThrowable(() -> new CardDto(null, COLOR));

                // then
                assertThat(throwable).isInstanceOf(InvalidCardException.class);
            }

            @Test
            void color_is_null() {
                // given
                final Throwable throwable = catchThrowable(() -> new CardDto(CARD_NUMBER, null));

                // then
                assertThat(throwable).isInstanceOf(InvalidCardException.class);
            }
        }
    }

    @Test
    void toString_should_serialize_cardNumber_and_color() throws InvalidCardException {
        // given
        final CardDto cardDto = new CardDto(CARD_NUMBER, COLOR);

        // when
        final String toString = cardDto.toString();

        // thenR
        assertThat(toString).isEqualTo("CardDto{cartNumber=ONE, color=BLUE}");
    }

    @Nested
    class EqualsShouldReturn {
        @Test
        void true_when_cardNumber_and_color_are_the_same() throws InvalidCardException {
            // given
            final CardDto cardDto1 = new CardDto(CARD_NUMBER, COLOR);
            final CardDto cardDto2 = new CardDto(CARD_NUMBER, COLOR);

            // when
            final boolean areEqual = cardDto1.equals(cardDto2);

            // then
            assertThat(areEqual).isTrue();
        }

        @Nested
        class FalseWhen {
            @Test
            void cardNumber_are_different_and_color_are_the_same() throws InvalidCardException {
                // given
                final CardDto cardDto1 = new CardDto(CartNumber.ONE, COLOR);
                final CardDto cardDto2 = new CardDto(CartNumber.TWO, COLOR);

                // when
                final boolean areEqual = cardDto1.equals(cardDto2);

                // then
                assertThat(areEqual).isFalse();
            }

            @Test
            void cardNumber_are_the_same_and_color_different() throws InvalidCardException {
                // given
                final CardDto cardDto1 = new CardDto(CARD_NUMBER, Color.RED);
                final CardDto cardDto2 = new CardDto(CARD_NUMBER, Color.GREEN);

                // when
                final boolean areEqual = cardDto1.equals(cardDto2);

                // then
                assertThat(areEqual).isFalse();
            }
        }
    }
}