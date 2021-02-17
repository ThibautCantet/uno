package uno.es.domain;

import uno.es.domain.game.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final List<Card> cards;

    private final int playerId;

    public Player(int playerId) {
        this.playerId = playerId;
        this.cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }
}
