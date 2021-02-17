package uno.es.domain.game;

import uno.es.domain.ddd.AggregateId;

import java.util.UUID;

import static java.util.Optional.ofNullable;

public final class GameId implements AggregateId {
    private final UUID value;

    public GameId(UUID value) throws InvalidGameIdException {
        this.value = ofNullable(value).orElseThrow(InvalidGameIdException::new);
    }

    public GameId() {
        this.value = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
