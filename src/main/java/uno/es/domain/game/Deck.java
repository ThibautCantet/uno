package uno.es.domain.game;

import java.util.*;

import static java.util.Arrays.asList;

public class Deck {

    public static final int DECK_SIZE = 76;

    private final Stack<Card> cards;
    private final DeckId deckId;

    private Deck(DeckId deckId) {
        this.deckId = deckId;
        cards = new Stack<>();
        addZeroCards(CartNumber.ZERO);
        addOneToNineCards();
        addOneToNineCards();

    }

    private void addOneToNineCards() {
        for (int i = 1; i < 10; i++) {
            addZeroCards(CartNumber.fromValue(i));
        }
    }

    private void addZeroCards(CartNumber cartNumber) {
        final Card redCard;
        try {
            redCard = new Card(cartNumber, Color.RED);
            final Card greenCard = new Card(cartNumber, Color.GREEN);
            final Card bleuCard = new Card(cartNumber, Color.BLUE);
            final Card yellowCard = new Card(cartNumber, Color.YELLOW);
            cards.addAll(asList(redCard, greenCard, bleuCard, yellowCard));
        } catch (InvalidCardException e) {

        }
    }

    public Stack<Card> getCards() {
        return cards;
    }

    public DeckId getId() {
        return deckId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deck)) return false;
        Deck deck = (Deck) o;
        for (int i = 0; i < this.cards.size(); i++) {
            if (!this.cards.get(i).equals(deck.getCards().get(i))) {
                return false;
            }
        }
        return true;
    }

    public DeckShuffledEvent shuffle() {
        Random random = new Random();
        List<Card> shuffledCards = new ArrayList<>();
        for (int currentIndex = cards.size() - 1; currentIndex >= 0; currentIndex--) {
            int index = random.nextInt(currentIndex + 1);
            Card randomCard = cards.get(index);
            shuffledCards.add(randomCard);
        }
        this.cards.clear();
        this.cards.addAll(shuffledCards);
        return new DeckShuffledEvent(deckId, cards);
    }

    public static Deck createNewDeck(DeckId deckId) {
        return new Deck(deckId);
    }
}
