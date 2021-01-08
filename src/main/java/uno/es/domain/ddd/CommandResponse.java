package uno.es.domain.ddd;

public class CommandResponse<T> {
    private final T value;

    public CommandResponse() {
        this.value = null;
    }

    public CommandResponse(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
