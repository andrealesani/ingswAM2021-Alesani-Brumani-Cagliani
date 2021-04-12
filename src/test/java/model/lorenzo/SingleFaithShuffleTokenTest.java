package model.lorenzo;

import Exceptions.EmptyDeckException;
import model.CardColor;
import model.CardTable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingleFaithShuffleTokenTest {

    /**
     * This method tests the token's action
     */
    @Test
    void doAction() {
        Lorenzo lorenzo = new Lorenzo(new CardTable(), null);
        ActionToken token = new SingleFaithShuffleToken(lorenzo);

        int tokenTotal = lorenzo.getActiveDeck().size();
        lorenzo.takeTurn();
        lorenzo.takeTurn();
        int previousFaith = lorenzo.getFaith();

        token.doAction();

        assertEquals (tokenTotal, lorenzo.getActiveDeck().size());
        assertEquals (previousFaith+1, lorenzo.getFaith());

        lorenzo.takeTurn();
        lorenzo.takeTurn();
        previousFaith = lorenzo.getFaith();

        token.doAction();

        assertEquals (tokenTotal, lorenzo.getActiveDeck().size());
        assertEquals (previousFaith+1, lorenzo.getFaith());
    }
}