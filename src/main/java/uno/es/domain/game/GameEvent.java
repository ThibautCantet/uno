package uno.es.domain.game;

import uno.es.domain.ddd.Event;

public interface GameEvent extends Event {
    GameId getGameId();
}
