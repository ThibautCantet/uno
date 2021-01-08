package uno.es.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uno.es.domain.game.Deck;

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
        Deck expectedDeck =  Deck.createNewDeck();

        // when
        final Deck deck = staticDeckRepository.findNewDeck();

        // then
        assertThat(deck).isEqualTo(expectedDeck);
    }
}