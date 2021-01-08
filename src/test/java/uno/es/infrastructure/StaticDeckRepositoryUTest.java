package uno.es.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uno.es.domain.game.Deck;
import uno.es.domain.game.DeckId;

import static org.assertj.core.api.Assertions.assertThat;

class StaticDeckRepositoryUTest {

    private StaticDeckRepository staticDeckRepository;

    @BeforeEach
    void setUp() {
        staticDeckRepository = new StaticDeckRepository();
    }

    @Test
    void findNewDeck_should_return_new_deck() {
        // given
        DeckId deckId = new DeckId();
        Deck expectedDeck =  Deck.createNewDeck(deckId);

        // when
        final Deck deck = staticDeckRepository.findNewDeck(deckId);

        // then
        assertThat(deck).isEqualTo(expectedDeck);
    }
}