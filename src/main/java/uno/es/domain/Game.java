package uno.es.domain;

import uno.es.domain.game.Card;
import uno.es.domain.game.Deck;

import java.util.HashMap;
import java.util.List;

public class Game {
    private final int numberOfPlayers;
    private final Deck deck;
    private final HashMap<Integer, Player> players;

    public Game(int numberOfPlayers, Deck deck) {
        this.numberOfPlayers = numberOfPlayers;
        this.players = new HashMap<>();
        for (int i = 1; i <= numberOfPlayers; i++) {
            players.computeIfAbsent(1, Player::new);
        }
        this.deck = deck;
    }

    public Player getPlayerById(int playerId) {
        return players.get(playerId);
    }

    public void distribute(int numberOfDistributedCardsByPlayer) {
        for (int distributedCardIndex = 1; distributedCardIndex <= numberOfDistributedCardsByPlayer; distributedCardIndex++) {
            for (int player = 0; player < numberOfPlayers; player++) {
                final Card card = this.deck.pop();

            }
        }
    }

    public List<Card> getDeckCards() {
        return deck.getCards();
    }
}
