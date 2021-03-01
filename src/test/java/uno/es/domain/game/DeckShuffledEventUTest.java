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
    private GameId gameId;

    @BeforeEach
    void setUp() throws InvalidCardException {
        gameId = new GameId();
        cards = asList(
                new Card(CartNumber.ONE, Color.RED),
                new Card(CartNumber.TWO, Color.RED)
        );
    }

    @Nested
    class ConstructorShould {
        @Test
        void bind_gameId() {
            // given
            final DeckShuffledEvent deckShuffledEvent = new DeckShuffledEvent(gameId, cards);

            // then
            assertThat(deckShuffledEvent.getGameId()).isEqualTo(gameId);
        }

        @Test
        void bind_cardNumber() {
            // given
            final DeckShuffledEvent deckShuffledEvent = new DeckShuffledEvent(gameId, cards);

            // then
            assertThat(deckShuffledEvent.getCards()).isEqualTo(cards);
        }
    }

    @Nested
    class EqualsShouldReturn {
        @Test
        void true_when_gameId_and_card_and_position_are_the_same() {
            // given
            final DeckShuffledEvent card1 = new DeckShuffledEvent(gameId, cards);
            final DeckShuffledEvent card2 = new DeckShuffledEvent(gameId, cards);

            // when
            final boolean areEqual = card1.equals(card2);

            // then
            assertThat(areEqual).isTrue();
        }

        @Nested
        class FalseWhen {
            @Test
            void gameId_are_different_and_cards_are_the_same() {
                // given
                final DeckShuffledEvent card1 = new DeckShuffledEvent(new GameId(), cards);
                final DeckShuffledEvent card2 = new DeckShuffledEvent(new GameId(), cards);

                // when
                final boolean areEqual = card1.equals(card2);

                // then
                assertThat(areEqual).isFalse();

            }
            @Test
            void cards_are_different_and_gameId_are_the_same() throws InvalidCardException {
                // given
                final DeckShuffledEvent card1 = new DeckShuffledEvent(gameId, Collections.singletonList(new Card(CartNumber.SEVEN, Color.RED)));
                final DeckShuffledEvent card2 = new DeckShuffledEvent(gameId, Collections.singletonList(new Card(CartNumber.ZERO, Color.BLUE)));

                // when
                final boolean areEqual = card1.equals(card2);

                // then
                assertThat(areEqual).isFalse();
            }
        }
    }
}
