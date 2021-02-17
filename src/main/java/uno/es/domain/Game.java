package uno.es.domain;

import uno.es.domain.game.Card;
import uno.es.domain.game.Deck;
import uno.es.domain.game.GameId;

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
            players.computeIfAbsent(i, playerId -> new Player(playerId));
        }
        this.deck = deck;
    }

    public Player getPlayerById(int playerId) {
        return getCurrentPlayer(playerId);
    }

    public void distribute(int numberOfDistributedCardsByPlayer) {
        for (int distributedCardIndex = 1; distributedCardIndex <= numberOfDistributedCardsByPlayer; distributedCardIndex++) {
            for (int player = 1; player <= numberOfPlayers; player++) {
                final Card card = this.deck.pop();
                getCurrentPlayer(player).addCard(card);
            }
        }
    }

    private Player getCurrentPlayer(int player) {
        return players.get(player);
    }

    public List<Card> getDeckCards() {
        return deck.getCards();
    }

    public Deck getDeck() {
        return deck;
    }

    public void shuffle() {

    }

    public GameId getId() {
        return null;
    }
}
