package uno.es.domain;

import uno.es.domain.game.Card;
import uno.es.domain.game.DeckEvent;

import java.util.List;

public class SimpleDeckCreated implements DeckEvent {
    private final List<Card> cards;

    public SimpleDeckCreated() {
        cards = null;
    }

    public List<Card> getCards() {
        return cards;
    }
}
