package uno.es.domain;

import uno.es.domain.game.Card;
import uno.es.domain.game.Deck;
import uno.es.domain.game.GameId;
import uno.es.domain.game.PlayerAdded;

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
            players.computeIfAbsent(i, Player::new);
        }
        this.deck = deck;
    }

    public Player getPlayerById(int playerId) {
        return getCurrentPlayer(playerId);
    }

    public void distribute(int numberOfDistributedCardsByPlayer) {
        for (int distributedCardIndex = 1; distributedCardIndex <= numberOfDistributedCardsByPlayer; distributedCardIndex++) {
            for (int player = 1; player <= numberOfPlayers; player++) {
                final Card card = this.deck.pop(player);
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
        deck.shuffle();
    }

    public GameId getId() {
        return null;
    }

    public void addPlayer(GameId gameId) {
        final int playerId = players.size() + 1;
        players.put(playerId, new Player(playerId));
        deck.getGeneratedEvents().add(new PlayerAdded(gameId));
    }
}
