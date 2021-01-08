package uno.es.domain.ddd;

public interface Aggregate<A extends AggregateId> {
    <A extends AggregateId> A getId();
}
