package uno.es.use_case;

import uno.es.domain.Game;
import uno.es.domain.ddd.QueryHandler;
import uno.es.domain.ddd.QueryResponse;
import uno.es.domain.game.GameRepository;

public class GetGameQueryHandler implements QueryHandler<QueryResponse<Game>, GetGameQuery> {

    private final GameRepository gameRepository;

    public GetGameQueryHandler(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public QueryResponse<Game> handle(GetGameQuery query) {
        final Game game = gameRepository.find(query.getGameId());
        return new QueryResponse<>(game);
    }

    @Override
    public Class listenTo() {
        return GetGameQuery.class;
    }
}
