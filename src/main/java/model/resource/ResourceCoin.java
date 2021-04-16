package model.resource;

import model.PlayerBoard;
import model.ResourceType;

/**
 * This class represents the game's coin resource and yellow marble
 */
public class ResourceCoin extends Resource {
    //CONSTRUCTORS

    /**
     * Constructor
     */
    public ResourceCoin() {
        super(ResourceType.COIN);
    }

    //PUBLIC METHODS

    /**
     * Adds a coin to the player's warehouse
     *
     * @param playerBoard the player's board
     */
    @Override
    public void addResourceFromMarket(PlayerBoard playerBoard) {
        playerBoard.addResourceToWaitingRoom(getType(), 1);
    }

    /**
     * Adds a coin to the player's strongbox
     *
     * @param playerBoard the player's board
     */
    @Override
    public void addResourceFromProduction(PlayerBoard playerBoard) {
        playerBoard.addResourceToStrongbox(ResourceType.COIN, 1);
    }


}
