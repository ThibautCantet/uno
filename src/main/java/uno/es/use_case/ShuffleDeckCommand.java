package uno.es.use_case;

import uno.es.domain.ddd.Command;
import uno.es.domain.game.DeckId;

public class ShuffleDeckCommand implements Command {
    private final DeckId deckId;

    public ShuffleDeckCommand(DeckId deckId) {
        this.deckId = deckId;
    }

    public DeckId getDeckId() {
        return deckId;
    }
}
