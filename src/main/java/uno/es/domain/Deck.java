package uno.es.domain;

import java.util.Stack;

import static java.util.Arrays.asList;

public class Deck {
    private final Stack<Card> cards;
    private final DeckId deckId;

    private Deck(DeckId deckId) throws InvalidCardException {
        this.deckId = deckId;
        cards = new Stack<>();
        addZeroCards(CartNumber.ZERO);
        addOneToNineCards();
        addOneToNineCards();

    }

    private void addOneToNineCards() throws InvalidCardException {
        for (int i = 1; i < 10; i++) {
            addZeroCards(CartNumber.fromValue(i));
        }
    }

    private void addZeroCards(CartNumber cartNumber) throws InvalidCardException {
        final Card redCard = new Card(cartNumber, Color.RED);
        final Card greenCard = new Card(cartNumber, Color.GREEN);
        final Card bleuCard = new Card(cartNumber, Color.BLUE);
        final Card yellowCard = new Card(cartNumber, Color.YELLOW);
        cards.addAll(asList(redCard, greenCard, bleuCard, yellowCard));
    }

    public Stack<Card> getCards() {
        return cards;
    }

    public DeckId getId() {
        return deckId;
    }



    public static Deck createNewDeck() throws InvalidCardException, InvalidDeckIdException {
        return new Deck(new DeckId());
    }
}
