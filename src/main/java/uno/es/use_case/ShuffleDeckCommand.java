package uno.es.use_case;

import uno.es.domain.Command;
import uno.es.domain.DeckId;

public class ShuffleDeckCommand implements Command {
    private final DeckId deckId;

    public ShuffleDeckCommand(DeckId deckId) {
        this.deckId = deckId;
    }

    public DeckId getDeckId() {
        return deckId;
    }
}
