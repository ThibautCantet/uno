package uno.es.use_case;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.fr.*;
import uno.es.domain.Game;
import uno.es.domain.Player;
import uno.es.domain.ddd.QueryResponse;
import uno.es.domain.game.*;
import uno.es.infrastructure.StaticGameRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class DistributionATest {

    private int numberOfPlayers;
    private final StaticGameRepository deckRepository = new StaticGameRepository();
    private final DistributeCommandHandler distributeCommandHandler = new DistributeCommandHandler(deckRepository);
    private final GameId gameId = new GameId();
    private final DeckId deckId = new DeckId();

    @Etantdonné("une partie avec {int} joueurs")
    public void unePartieAvecJoueurs(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    @Et("le jeu de carte simple est neuf trié")
    public void leJeuDeCarteSimpleEstNeufTrié() {
        SimpleDeckCreated simpleDeckCreated = new SimpleDeckCreated();
        deckRepository.addEvent(simpleDeckCreated);
    }

    @Etque("le jeu de carte simple est mélangé")
    public void leJeuDeCarteSimpleEstMélangé(DataTable dataTable) {
        List<Card> cards = dataTableTransformEntries(dataTable, this::buildCard);
        DeckShuffledEvent deckShuffledEvent = new DeckShuffledEvent(gameId, cards);
        deckRepository.addEvent(deckShuffledEvent);
    }

    @Quand("je distribue {int} cartes")
    public void jeDistribueCartes(int numberOfDistributedCardsByPlayer) {
        distributeCommandHandler.handle(new DistributeCommand(gameId, numberOfPlayers, numberOfDistributedCardsByPlayer));
        // game.distribute(numberOfDistributedCardsByPlayer);
    }

    @Alors("il ne reste plus que les cartes suivantes")
    public void ilNeRestePlusQueLesCartesSuivantes(DataTable dataTable) {
        GetGameQueryHandler getGameQueryHandler = new GetGameQueryHandler(deckRepository);
        final QueryResponse<Game> queryResponse = getGameQueryHandler.handle(new GetGameQuery(gameId));

        List<Card> expectedCards = dataTableTransformEntries(dataTable, this::buildCard);

        final Game game = queryResponse.getValue();
        assertThat(game.getDeckCards()).isEqualTo(expectedCards);
        //verify(deckRepository).save(game.getDeck());
    }

    public Card buildCard(Map<String, String> entry) {
        return new Card(CartNumber.valueOf(entry.get("value")), Color.valueOf(entry.get("color")));
    }

    @Et("le joueur {int} a les cartes suivantes")
    public void leJoueurALesCartesSuivantes(int playerId, DataTable dataTable) {
        GetGameQueryHandler getGameQueryHandler = new GetGameQueryHandler(deckRepository);
        final QueryResponse<Game> queryResponse = getGameQueryHandler.handle(new GetGameQuery(gameId));
        final Game game = queryResponse.getValue();

        List<Card> expectedCards = dataTableTransformEntries(dataTable, this::buildCard);
        Player player = game.getPlayerById(playerId);
        assertThat(player.getCards()).containsOnlyElementsOf(expectedCards);
    }

    private <T> List<T> dataTableTransformEntries(DataTable dataTable, Function<Map<String, String>, T> transformFunction) {
        final List<T> transformResults = new ArrayList<>();
        final List<Map<String, String>> dataTableEntries = dataTable.asMaps(String.class, String.class);
        dataTableEntries.forEach(mapEntry -> {
            transformResults.add(transformFunction.apply(mapEntry));
        });
        return transformResults;
    }
}
