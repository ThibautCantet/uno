package uno.es.use_case;

import uno.es.domain.ddd.Query;
import uno.es.domain.game.DeckId;

public class GetShuffledDeckQuery implements Query {
    private final DeckId deckId;

    public GetShuffledDeckQuery(DeckId deckId) {
        this.deckId = deckId;
    }

    public DeckId getDeckId() {
        return deckId;
    }
}
