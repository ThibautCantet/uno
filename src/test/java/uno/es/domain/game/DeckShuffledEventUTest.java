package uno.es.domain.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class DeckShuffledEventUTest {
    private List<Card> cards;
    private DeckId deckId;

    @BeforeEach
    void setUp() throws InvalidCardException {
        deckId = new DeckId();
        cards = asList(
                new Card(CartNumber.ONE, Color.RED),
                new Card(CartNumber.TWO, Color.RED)
        );
    }

    @Nested
    class ConstructorShould {
        @Test
        void bind_deckId() {
            // given
            final DeckShuffledEvent deckShuffledEvent = new DeckShuffledEvent(deckId, cards);

            // then
            assertThat(deckShuffledEvent.getDeckId()).isEqualTo(deckId);
        }

        @Test
        void bind_cardNumber() {
            // given
            final DeckShuffledEvent deckShuffledEvent = new DeckShuffledEvent(deckId, cards);

            // then
            assertThat(deckShuffledEvent.getCards()).isEqualTo(cards);
        }
    }

    @Nested
    class EqualsShouldReturn {
        @Test
        void true_when_deckId_and_card_and_position_are_the_same() {
            // given
            final DeckShuffledEvent card1 = new DeckShuffledEvent(deckId, cards);
            final DeckShuffledEvent card2 = new DeckShuffledEvent(deckId, cards);

            // when
            final boolean areEqual = card1.equals(card2);

            // then
            assertThat(areEqual).isTrue();
        }

        @Nested
        class FalseWhen {
            @Test
            void deckId_are_different_and_cards_are_the_same() {
                // given
                final DeckShuffledEvent card1 = new DeckShuffledEvent(new DeckId(), cards);
                final DeckShuffledEvent card2 = new DeckShuffledEvent(new DeckId(), cards);

                // when
                final boolean areEqual = card1.equals(card2);

                // then
                assertThat(areEqual).isFalse();

            }
            @Test
            void cards_are_different_and_deckId_are_the_same() throws InvalidCardException {
                // given
                final DeckShuffledEvent card1 = new DeckShuffledEvent(deckId, Collections.singletonList(new Card(CartNumber.SEVEN, Color.RED)));
                final DeckShuffledEvent card2 = new DeckShuffledEvent(deckId, Collections.singletonList(new Card(CartNumber.ZERO, Color.BLUE)));

                // when
                final boolean areEqual = card1.equals(card2);

                // then
                assertThat(areEqual).isFalse();
            }
        }
    }
}
