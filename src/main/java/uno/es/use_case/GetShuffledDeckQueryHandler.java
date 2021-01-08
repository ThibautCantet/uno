package uno.es.use_case;

import uno.es.domain.ddd.QueryHandler;
import uno.es.domain.ddd.QueryResponse;
import uno.es.domain.game.Deck;
import uno.es.domain.game.DeckRepository;

public class GetShuffledDeckQueryHandler implements QueryHandler<QueryResponse<Deck>, GetShuffledDeckQuery> {

    private final DeckRepository deckRepository;

    public GetShuffledDeckQueryHandler(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    public QueryResponse<Deck> handle(GetShuffledDeckQuery query) {
        return new QueryResponse<>(deckRepository.find(query.getDeckId()));
    }

    @Override
    public Class listenTo() {
        return GetShuffledDeckQuery.class;
    }
}
