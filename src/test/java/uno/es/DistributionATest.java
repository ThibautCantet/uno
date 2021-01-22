package uno.es;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Etque;
import io.cucumber.java.fr.Quand;
import uno.es.domain.game.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class DistributionATest {

    private Deck deck;

    @Etque("le jeu de carte simple est mélangé")
    public void leJeuDeCarteSimpleEstMélangé(DataTable dataTable) {
        List<CardDto> cards = dataTableTransformEntries(dataTable, this::buildCardDto);
        deck = new Deck(new DeckId(), cards);
    }

    @Quand("je distribue {int} cartes à {int} joueurs")
    public void jeDistribueCartesÀJoueurs(int numberOfDistributedCardsByPlayer, int numberOfPlayers) {
        deck.distribute(numberOfDistributedCardsByPlayer, numberOfPlayers);
    }

    public CardDto buildCardDto(Map<String, String> entry) {
        try {
            return new CardDto(CartNumber.valueOf(entry.get("value")), Color.valueOf(entry.get("color")));
        } catch (InvalidCardException e) {
            return null;
        }
    }

    @Alors("il ne reste plus que les cartes suivantes")
    public void ilNeRestePlusQueLesCartesSuivantes(DataTable dataTable) {
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

    @Et("le joueur {int} a les cartes suivantes")
    public void leJoueurALesCartesSuivantes(int playerId, DataTable dataTable) {
        List<Card> expectedCards = dataTableTransformEntries(dataTable, this::buildCard);
        // assertThat(player).isEqualTo(expectedCards);
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
