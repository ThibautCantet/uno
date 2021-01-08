package uno.es.domain.ddd;

public class QueryResponse<T> {
    private final T value;

    public QueryResponse(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

}
