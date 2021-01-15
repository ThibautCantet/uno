package uno.es.domain.game;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleDeckCreatedUTest {

    @Nested
    class ConstructorShould {

        @Test
        void initialize_all_cards_with_19_cards_of_each_color_two_same_from_one_to_nine_and_only_1_zero() throws InvalidCardException {
            // when
            final SimpleDeckCreated deck = new SimpleDeckCreated();

            // then
            assertThat(deck.getCardDtos()).containsExactly(
                    new CardDto(CartNumber.ZERO, Color.BLUE),
                    new CardDto(CartNumber.ONE, Color.BLUE),
                    new CardDto(CartNumber.ONE, Color.BLUE),
                    new CardDto(CartNumber.TWO, Color.BLUE),
                    new CardDto(CartNumber.TWO, Color.BLUE),
                    new CardDto(CartNumber.THREE, Color.BLUE),
                    new CardDto(CartNumber.THREE, Color.BLUE),
                    new CardDto(CartNumber.FOUR, Color.BLUE),
                    new CardDto(CartNumber.FOUR, Color.BLUE),
                    new CardDto(CartNumber.FIVE, Color.BLUE),
                    new CardDto(CartNumber.FIVE, Color.BLUE),
                    new CardDto(CartNumber.SIX, Color.BLUE),
                    new CardDto(CartNumber.SIX, Color.BLUE),
                    new CardDto(CartNumber.SEVEN, Color.BLUE),
                    new CardDto(CartNumber.SEVEN, Color.BLUE),
                    new CardDto(CartNumber.EIGHT, Color.BLUE),
                    new CardDto(CartNumber.EIGHT, Color.BLUE),
                    new CardDto(CartNumber.NINE, Color.BLUE),
                    new CardDto(CartNumber.NINE, Color.BLUE),
                    new CardDto(CartNumber.ZERO, Color.RED),
                    new CardDto(CartNumber.ONE, Color.RED),
                    new CardDto(CartNumber.ONE, Color.RED),
                    new CardDto(CartNumber.TWO, Color.RED),
                    new CardDto(CartNumber.TWO, Color.RED),
                    new CardDto(CartNumber.THREE, Color.RED),
                    new CardDto(CartNumber.THREE, Color.RED),
                    new CardDto(CartNumber.FOUR, Color.RED),
                    new CardDto(CartNumber.FOUR, Color.RED),
                    new CardDto(CartNumber.FIVE, Color.RED),
                    new CardDto(CartNumber.FIVE, Color.RED),
                    new CardDto(CartNumber.SIX, Color.RED),
                    new CardDto(CartNumber.SIX, Color.RED),
                    new CardDto(CartNumber.SEVEN, Color.RED),
                    new CardDto(CartNumber.SEVEN, Color.RED),
                    new CardDto(CartNumber.EIGHT, Color.RED),
                    new CardDto(CartNumber.EIGHT, Color.RED),
                    new CardDto(CartNumber.NINE, Color.RED),
                    new CardDto(CartNumber.NINE, Color.RED),
                    new CardDto(CartNumber.ZERO, Color.YELLOW),
                    new CardDto(CartNumber.ONE, Color.YELLOW),
                    new CardDto(CartNumber.ONE, Color.YELLOW),
                    new CardDto(CartNumber.TWO, Color.YELLOW),
                    new CardDto(CartNumber.TWO, Color.YELLOW),
                    new CardDto(CartNumber.THREE, Color.YELLOW),
                    new CardDto(CartNumber.FOUR, Color.YELLOW),
                    new CardDto(CartNumber.FOUR, Color.YELLOW),
                    new CardDto(CartNumber.FOUR, Color.YELLOW),
                    new CardDto(CartNumber.FIVE, Color.YELLOW),
                    new CardDto(CartNumber.FIVE, Color.YELLOW),
                    new CardDto(CartNumber.SIX, Color.YELLOW),
                    new CardDto(CartNumber.SIX, Color.YELLOW),
                    new CardDto(CartNumber.SEVEN, Color.YELLOW),
                    new CardDto(CartNumber.SEVEN, Color.YELLOW),
                    new CardDto(CartNumber.EIGHT, Color.YELLOW),
                    new CardDto(CartNumber.EIGHT, Color.YELLOW),
                    new CardDto(CartNumber.NINE, Color.YELLOW),
                    new CardDto(CartNumber.NINE, Color.YELLOW),
                    new CardDto(CartNumber.ZERO, Color.GREEN),
                    new CardDto(CartNumber.ONE, Color.GREEN),
                    new CardDto(CartNumber.ONE, Color.GREEN),
                    new CardDto(CartNumber.TWO, Color.GREEN),
                    new CardDto(CartNumber.TWO, Color.GREEN),
                    new CardDto(CartNumber.THREE, Color.GREEN),
                    new CardDto(CartNumber.THREE, Color.GREEN),
                    new CardDto(CartNumber.FOUR, Color.GREEN),
                    new CardDto(CartNumber.FOUR, Color.GREEN),
                    new CardDto(CartNumber.FIVE, Color.GREEN),
                    new CardDto(CartNumber.FIVE, Color.GREEN),
                    new CardDto(CartNumber.SIX, Color.GREEN),
                    new CardDto(CartNumber.SIX, Color.GREEN),
                    new CardDto(CartNumber.SEVEN, Color.GREEN),
                    new CardDto(CartNumber.SEVEN, Color.GREEN),
                    new CardDto(CartNumber.EIGHT, Color.GREEN),
                    new CardDto(CartNumber.EIGHT, Color.GREEN),
                    new CardDto(CartNumber.NINE, Color.GREEN),
                    new CardDto(CartNumber.NINE, Color.GREEN)
            );
        }

    }

}