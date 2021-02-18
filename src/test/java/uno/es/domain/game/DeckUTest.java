package uno.es.domain.game;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class DeckUTest {

    private final DeckId deckId = new DeckId();

    @Nested
    class CreateNewDeckShould {
        @Test
        void initialize_new_aggregate_id() {
            // when
            final Deck deck = Deck.createNewDeck(deckId, Collections.emptyList());

            // then
            assertThat(deck.getId()).isEqualTo(deckId);
        }

        @Test
        void initialize_all_cards_with_19_cards_of_each_color_two_same_from_one_to_nine_and_only_1_zero() throws InvalidCardException {
            // given
            List<CardDto> initialeCards = Collections.singletonList(new CardDto(CartNumber.ZERO, Color.BLUE));
            final Card expectedCard = new Card(CartNumber.ZERO, Color.BLUE);

            // when
            final Deck deck = Deck.createNewDeck(deckId, initialeCards);

            // then
            assertThat(deck.getCards().size()).isEqualTo(1);
            assertThat(deck.getCards()).containsExactly(expectedCard);
        }
    }

    @Nested
    class SuffleShould {
        @Test
        void shuffle_cards() throws InvalidCardException {
            // given
            List<CardDto> initialeCards = asList(
                    new CardDto(CartNumber.ZERO, Color.BLUE),
                    new CardDto(CartNumber.ONE, Color.BLUE),
                    new CardDto(CartNumber.TWO, Color.BLUE),
                    new CardDto(CartNumber.THREE, Color.BLUE),
                    new CardDto(CartNumber.FOUR, Color.BLUE)
            );
            final Deck deck = Deck.createNewDeck(deckId, initialeCards);
            Stack<Card> expectedCards = new Stack<>();
            expectedCards.addAll(asList(
                    new Card(CartNumber.ZERO, Color.BLUE),
                    new Card(CartNumber.ONE, Color.BLUE),
                    new Card(CartNumber.TWO, Color.BLUE),
                    new Card(CartNumber.THREE, Color.BLUE),
                    new Card(CartNumber.FOUR, Color.BLUE)));

            // when
            deck.shuffle();

            // then
            Boolean[] areSameCard = new Boolean[5];
            Arrays.fill(areSameCard, Boolean.TRUE);
            for (int i = 0; i < deck.getCards().size() - 1; i++) {
                areSameCard[i] = deck.getCards().get(i).equals(expectedCards.pop());
            }
            assertThat(Arrays.stream(areSameCard).allMatch(same -> same)).isFalse();
        }

        @Test
        void add_CardShuffledEvent_to_generatedEvents() {
            // given
            final Deck deck = Deck.createNewDeck(deckId, Collections.emptyList());

            // when
            deck.shuffle();

            // then
            assertThat(deck.getGeneratedEvents()).usingFieldByFieldElementComparator().containsExactly(new DeckShuffledEvent(deckId, deck.getCards()));
        }
    }

    @Nested
    class EqualsShouldReturn {
        @Test
        void true_when_deck_contain_same_cards_in_same_order() {
            // given
            List<CardDto> initialCards = Collections.singletonList(new CardDto(CartNumber.ZERO, Color.BLUE));

            final Deck deck1 = Deck.createNewDeck(deckId, initialCards);
            final Deck deck2 = Deck.createNewDeck(deckId, initialCards);

            // when
            final boolean areEqual = deck1.equals(deck2);

            // then
            assertThat(areEqual).isTrue();
        }

        @Test
        void false_when_deck_contain_same_cards_in_different_order() {
            // given
            List<CardDto> initialCards1 = Collections.singletonList(new CardDto(CartNumber.ZERO, Color.BLUE));
            final Deck deck1 = Deck.createNewDeck(deckId, initialCards1);
            List<CardDto> initialCards2 = Collections.singletonList(new CardDto(CartNumber.THREE, Color.BLUE));
            final Deck deck2 = Deck.createNewDeck(deckId, initialCards2);

            // when
            final boolean areEqual = deck1.equals(deck2);

            // then
            assertThat(areEqual).isFalse();
        }
    }

    @Test
    void distribute_should_remove_the_first_numberOfDistributedCardsByPlayer_x_numberOfPlayers_cards() {
        // given
        List<CardDto> initialeCards = asList(
                new CardDto(CartNumber.ZERO, Color.BLUE),
                new CardDto(CartNumber.ONE, Color.BLUE),
                new CardDto(CartNumber.TWO, Color.BLUE),
                new CardDto(CartNumber.THREE, Color.BLUE),
                new CardDto(CartNumber.FOUR, Color.BLUE),
                new CardDto(CartNumber.FIVE, Color.BLUE),
                new CardDto(CartNumber.SIX, Color.BLUE),
                new CardDto(CartNumber.SEVEN, Color.BLUE),
                new CardDto(CartNumber.EIGHT, Color.BLUE)
        );
        final Deck deck = Deck.createNewDeck(deckId, initialeCards);

        // when
        deck.distribute(2, 3);

        // then
        final List<Card> cards = asList(
                new Card(CartNumber.ZERO, Color.BLUE),
                new Card(CartNumber.ONE, Color.BLUE),
                new Card(CartNumber.TWO, Color.BLUE)
        );
        Stack<Card> expectedCards = new Stack<>();
        expectedCards.addAll(cards);

        assertThat(deck.getCards().size()).isEqualTo(3);
        assertThat(deck.getCards()).isEqualTo(expectedCards);
    }
}