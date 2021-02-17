package uno.es.domain.game;

import uno.es.domain.Game;

public interface GameRepository {
    Deck findNewDeck(DeckId deckId);

    void save(Game game);

    Game find(GameId gameId);
}
