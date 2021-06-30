package it.polimi.ingsw.model.resource;

import it.polimi.ingsw.Exceptions.*;
import it.polimi.ingsw.model.PlayerBoard;
import it.polimi.ingsw.model.resource.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ResourceShieldTest {

    @Test
    void equals() {
        assertTrue(new ResourceShield().equals(new ResourceShield()));
        assertFalse(new ResourceShield().equals(new ResourceJolly()));
    }

    @Test
    void removeList() {
        ArrayList<Resource> list = new ArrayList<>();
        list.add(new ResourceShield());
        list.add(new ResourceFaith());
        list.remove(new ResourceShield());

        assertTrue(list.get(0) instanceof ResourceFaith);
    }

    @Test
    public void addResourceFromMarket() throws DepotNotPresentException, WrongResourceInsertionException, NotEnoughSpaceException, BlockedResourceException, NotEnoughResourceException {
        PlayerBoard playerBoard = new PlayerBoard();
        ResourceShield shield = new ResourceShield();

        shield.addResourceFromMarket(playerBoard);

        playerBoard.sendResourceToDepot(1, ResourceType.SHIELD, 1);
        assertEquals(1, playerBoard.getNumOfResource(ResourceType.SHIELD));

    }

}