package uno.es.domain.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Deck {

    private final Stack<Card> cards;
    private final DeckId deckId;
    private final List<DeckEvent> getGeneratedEvents = new ArrayList<>();

    private Deck(DeckId deckId) {
        this.deckId = deckId;
        this.cards = new Stack<>();
        addZeroCards(CartNumber.ZERO);
        addOneToNineCards();
        addOneToNineCards();
    }

    public Deck(DeckId deckId, List<CardDto> cardDtos) {
        this.deckId = deckId;
        this.cards = initializeCards(cardDtos);
    }

    private Stack<Card> initializeCards(List<CardDto> cardDtos) {
        final Stack<Card> cards = new Stack<>();
        cards.addAll(
                cardDtos.stream()
                        .map(this::convert)
                        .collect(Collectors.toList()));
        return cards;
    }

    private Card convert(CardDto cardDto) {
        return new Card(cardDto.getCartNumber(), cardDto.getColor());
    }

    private void addOneToNineCards() {
        for (int i = 1; i < 10; i++) {
            addZeroCards(CartNumber.fromValue(i));
        }
    }

    private void addZeroCards(CartNumber cartNumber) {
        final Card redCard;
        redCard = new Card(cartNumber, Color.RED);
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

    public void shuffle() {
        Random random = new Random();
        List<Card> shuffledCards = new ArrayList<>();
        for (int currentIndex = cards.size() - 1; currentIndex >= 0; currentIndex--) {
            int index = random.nextInt(currentIndex + 1);
            Card randomCard = cards.get(index);
            shuffledCards.add(randomCard);
        }
        this.cards.clear();
        this.cards.addAll(shuffledCards);

        getGeneratedEvents.add(new DeckShuffledEvent(deckId, cards));
    }

    public static Deck createNewDeck(DeckId deckId, List<CardDto> cardDtos) {
        return new Deck(deckId, cardDtos);
    }

    public static Deck createNewDeck(DeckId deckId) {
        return new Deck(deckId);
    }

    public List<DeckEvent> getGeneratedEvents() {
        return getGeneratedEvents;
    }

    public void distribute(int numberOfDistributedCardsByPlayer, int numberOfPlayers) {
        final int numberOfCardsToPop = numberOfDistributedCardsByPlayer * numberOfPlayers;

        for (int i = 0; i < numberOfCardsToPop; i++) {
            this.cards.pop();
        }
    }

    public Card pop() {
        return this.cards.pop();
    }
}
