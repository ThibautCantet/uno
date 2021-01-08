package uno.es.domain;

import java.util.UUID;

import static java.util.Optional.ofNullable;

public final class DeckId implements AggregateId {
    private final UUID value;

    public DeckId(UUID value) throws InvalidDeckIdException {
        this.value = ofNullable(value).orElseThrow(InvalidDeckIdException::new);
    }

    public DeckId() {
        this.value = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
