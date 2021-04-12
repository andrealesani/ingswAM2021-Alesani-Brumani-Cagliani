package model.resource;

import Exceptions.*;
import model.PlayerBoard;
import model.ResourceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceWhiteTest {

    @Test
    void addResourceFromMarketNoConversion() {
        PlayerBoard playerBoard = new PlayerBoard(null, null, 3, null);
        ResourceWhite white = new ResourceWhite();

        white.addResourceFromMarket(playerBoard);
        assertEquals(0, playerBoard.leftInWaitingRoom());

    }

    @Test
    void addResourceFromMarketWithConversion() throws DepotNotPresentException, WrongResourceTypeException, NotEnoughSpaceException, BlockedResourceException, NotEnoughResourceException {
        PlayerBoard playerBoard = new PlayerBoard(null, null, 3, null);
        playerBoard.addMarbleConversion(ResourceType.SHIELD);
        ResourceWhite white = new ResourceWhite();

        white.addResourceFromMarket(playerBoard);
        assertEquals(1, playerBoard.leftInWaitingRoom());

        playerBoard.sendResourceToDepot(1, ResourceType.SHIELD, 1 );
        assertEquals(1, playerBoard.getNumOfResource(ResourceType.SHIELD));

    }

    @Test
    void addResourceFromMarketMultipleConversion() throws DepotNotPresentException, WrongResourceTypeException, NotEnoughSpaceException, BlockedResourceException, NotEnoughResourceException, ConversionNotAvailableException {
        PlayerBoard playerBoard = new PlayerBoard(null, null, 3, null);
        playerBoard.addMarbleConversion(ResourceType.SHIELD);
        playerBoard.addMarbleConversion(ResourceType.COIN);
        ResourceWhite white = new ResourceWhite();

        white.addResourceFromMarket(playerBoard);
        white.addResourceFromMarket(playerBoard);
        assertEquals(2, playerBoard.leftInWaitingRoom());

        playerBoard.chooseMarbleConversion(ResourceType.SHIELD, 1);
        assertEquals(2, playerBoard.leftInWaitingRoom());

        playerBoard.sendResourceToDepot(1, ResourceType.SHIELD, 1 );
        assertEquals(1, playerBoard.getNumOfResource(ResourceType.SHIELD));

        playerBoard.chooseMarbleConversion(ResourceType.COIN, 1);
        assertEquals(1, playerBoard.leftInWaitingRoom());

        playerBoard.sendResourceToDepot(2, ResourceType.COIN, 1 );
        assertEquals(1, playerBoard.getNumOfResource(ResourceType.COIN));

    }
}