package uno.es.domain.game;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static uno.es.domain.game.Deck.DECK_SIZE;

class DeckUTest {

    private DeckId deckId = new DeckId();

    @Nested
    class ConstructorShould {
        @Test
        void initialize_new_aggregate_id() {
            // when
            final Deck deck = Deck.createNewDeck(deckId);

            // then
            assertThat(deck.getId()).isEqualTo(deckId);
        }

        @Test
        void initialize_all_cards_with_19_cards_of_each_color_two_same_from_one_to_nine_and_only_1_zero() throws InvalidCardException {
            // given
            Stack<Card> expectedCards = initializeCards();

            // when
            final Deck deck = Deck.createNewDeck(deckId);

            // then
            assertThat(deck.getCards().size()).isEqualTo(DECK_SIZE);
            deck.getCards().forEach(card ->
                    assertThat(card.equals(expectedCards.pop()))
            );
        }

    }

    @Test
    void shuffle_should_shuffle_cards() throws InvalidCardException {
        // given
        final Deck deck = Deck.createNewDeck(deckId);
        Stack<Card> expectedCards = initializeCards();

        // when
        deck.shuffle();

        // then
        assertThat(deck.getCards().size()).isEqualTo(DECK_SIZE);
        Boolean[] areSameCard = new Boolean[DECK_SIZE];
        Arrays.fill(areSameCard, Boolean.TRUE);
        for (int i = 0; i < deck.getCards().size() -1; i++) {
            areSameCard[i] = deck.getCards().get(i).equals(expectedCards.pop());
        }
        assertThat(Arrays.stream(areSameCard).allMatch(same -> same)).isFalse();
    }

    @Nested
    class EqualsShouldReturn {
        @Test
        void true_when_deck_contain_same_cards_in_same_order() {
            // given
            final Deck deck1 = Deck.createNewDeck(deckId);
            final Deck deck2 = Deck.createNewDeck(deckId);

            // when
            final boolean areEqual = deck1.equals(deck2);

            // then
            assertThat(areEqual).isTrue();
        }

        @Test
        void false_when_deck_contain_same_cards_in_different_order() {
            // given
            final Deck deck1 = Deck.createNewDeck(deckId);
            deck1.shuffle();
            final Deck deck2 = Deck.createNewDeck(deckId);

            // when
            final boolean areEqual = deck1.equals(deck2);

            // then
            assertThat(areEqual).isFalse();
        }
    }

    private Stack<Card> initializeCards() throws InvalidCardException {
        Stack<Card> expectedCards = new Stack<>();
        expectedCards.addAll(addZeroCards(CartNumber.ZERO));
        expectedCards.addAll(addOneToNineCards());
        expectedCards.addAll(addOneToNineCards());
        return expectedCards;
    }

    private Stack<Card> addOneToNineCards() throws InvalidCardException {
        Stack<Card> cards = new Stack<>();
        for (int i = 1; i < 10; i++) {
            cards.addAll(addZeroCards(CartNumber.fromValue(i)));
        }
        return cards;
    }

    private List<Card> addZeroCards(CartNumber cartNumber) throws InvalidCardException {
        final Card redCard = new Card(cartNumber, Color.RED);
        final Card greenCard = new Card(cartNumber, Color.GREEN);
        final Card bleuCard = new Card(cartNumber, Color.BLUE);
        final Card yellowCard = new Card(cartNumber, Color.YELLOW);
        return asList(redCard, greenCard, bleuCard, yellowCard);
    }
}