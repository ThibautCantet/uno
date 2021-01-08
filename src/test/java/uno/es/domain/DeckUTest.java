package uno.es.domain;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Stack;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class DeckUTest {

    @Nested
    class ConstructorShould {
        @Test
        void initialize_new_aggregate_id() throws InvalidCardException, InvalidDeckIdException {
            // when
            final Deck deck = Deck.createNewDeck();

            // then
            assertThat(deck.getId()).isInstanceOf(DeckId.class);
        }

        @Test
        void initialize_all_cards_with_19_cards_of_each_color_two_same_from_one_to_nine_and_only_1_zero() throws InvalidCardException, InvalidDeckIdException {
            // given
            Stack<Card> expectedCards = new Stack<>();
            expectedCards.addAll(addZeroCards(CartNumber.ZERO));
            expectedCards.addAll(addOneToNineCards());
            expectedCards.addAll(addOneToNineCards());

            // when
            final Deck deck = Deck.createNewDeck();

            // then
            assertThat(deck.getCards().size()).isEqualTo(76);
            deck.getCards().forEach(card ->
                    assertThat(card.equals(expectedCards.pop()))
            );
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

}