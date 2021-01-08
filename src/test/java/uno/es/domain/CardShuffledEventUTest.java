package uno.es.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardShuffledEventShuffledEventUTest {
    private static final Integer NEW_POSITION = 42;

    private Card card;
    private DeckId deckId;

    @BeforeEach
    void setUp() throws InvalidCardException {
        deckId = new DeckId();
        card = new Card(CartNumber.ONE, Color.RED);
    }

    @Nested
    class ConstructorShould {
        @Test
        void bind_deckId() {
            // given
            final CardShuffledEvent cardShuffledEvent = new CardShuffledEvent(deckId, card, NEW_POSITION);

            // then
            assertThat(cardShuffledEvent.getDeckId()).isEqualTo(deckId);
        }

        @Test
        void bind_cardNumber() {
            // given
            final CardShuffledEvent cardShuffledEvent = new CardShuffledEvent(deckId, card, NEW_POSITION);

            // then
            assertThat(cardShuffledEvent.getCard()).isEqualTo(card);
        }

        @Test
        void bind_newPosition() {
            // given
            final CardShuffledEvent cardShuffledEvent = new CardShuffledEvent(deckId, card, NEW_POSITION);

            // then
            assertThat(cardShuffledEvent.getNewPosition()).isEqualTo(NEW_POSITION);
        }
    }

    @Nested
    class EqualsShouldReturn {
        @Test
        void true_when_deckId_and_card_and_position_are_the_same() {
            // given
            final CardShuffledEvent card1 = new CardShuffledEvent(deckId, card, NEW_POSITION);
            final CardShuffledEvent card2 = new CardShuffledEvent(deckId, card, NEW_POSITION);

            // when
            final boolean areEqual = card1.equals(card2);

            // then
            assertThat(areEqual).isTrue();
        }

        @Nested
        class FalseWhen {
            @Test
            void deckId_are_different_and_card_and_position_are_the_same() {
                // given
                final CardShuffledEvent card1 = new CardShuffledEvent(new DeckId(), card, NEW_POSITION);
                final CardShuffledEvent card2 = new CardShuffledEvent(new DeckId(), card, NEW_POSITION);

                // when
                final boolean areEqual = card1.equals(card2);

                // then
                assertThat(areEqual).isFalse();

            }
            @Test
            void card_are_different_and_deckId_and_position_are_the_same() throws InvalidCardException {
                // given
                final CardShuffledEvent card1 = new CardShuffledEvent(deckId, new Card(CartNumber.SEVEN, Color.RED), NEW_POSITION);
                final CardShuffledEvent card2 = new CardShuffledEvent(deckId, new Card(CartNumber.ZERO, Color.BLUE), NEW_POSITION);

                // when
                final boolean areEqual = card1.equals(card2);

                // then
                assertThat(areEqual).isFalse();
            }

            @Test
            void position_are_different_and_deckId_and_card_are_the_same() {
                // given
                final CardShuffledEvent card1 = new CardShuffledEvent(deckId, card, 1);
                final CardShuffledEvent card2 = new CardShuffledEvent(deckId, card, 2);

                // when
                final boolean areEqual = card1.equals(card2);

                // then
                assertThat(areEqual).isFalse();
            }
        }
    }
}
