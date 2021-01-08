package uno.es.domain;

public interface Aggregate<A extends AggregateId> {
    <A extends AggregateId> A getId();
}
