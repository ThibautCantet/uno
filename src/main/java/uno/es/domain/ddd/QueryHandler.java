package uno.es.domain.ddd;

public interface QueryHandler<R extends QueryResponse, Q extends Query> {

    R handle(Q query);

    Class listenTo();
}
