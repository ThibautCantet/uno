package uno.es;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etque;
import io.cucumber.java.fr.Quand;
import uno.es.domain.game.SimpleDeckCreated;
import uno.es.domain.game.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckATest {

    private Deck deck;
    private SimpleDeckCreated simpleDeckCreated;
    private GameId gameId;

    @Etque("le jeu de carte simple est neuf")
    public void leJeuDeCarteSimpleEstNeuf() {
        simpleDeckCreated = new SimpleDeckCreated();
        gameId = simpleDeckCreated.getGameId();
    }

    @Quand("je récupère le paquet de cartes")
    public void jeRécupèreLePaquetDeCarte() {
        deck = Deck.createNewDeck(new DeckId(), simpleDeckCreated.getCardDtos(), gameId);
    }

    @Alors("il doit être trié par couleur et ordre croissant")
    public void ilDoitÊtreTriéParCouleurEtOrdreCroissant(DataTable dataTable) {
        List<Card> expectedCards = dataTableTransformEntries(dataTable, this::buildCard);
        assertThat(deck.getCards()).isEqualTo(expectedCards);
    }

    public Card buildCard(Map<String, String> entry) {
        try {
            return new Card(CartNumber.valueOf(entry.get("value")), Color.valueOf(entry.get("color")));
        } catch (InvalidCardException e) {
            return null;
        }
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
