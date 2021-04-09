package model.resource;

import model.PlayerBoard;
import model.ResourceType;

public class ResourceWhite extends Resource {
    private final ResourceType type;

    public ResourceWhite() {
        this.type = ResourceType.WHITEORB;
    }

    @Override
    public void addResourceFromMarket(PlayerBoard playerBoard) {
        playerBoard.addWhiteMarble();
    }

    @Override
    public void addResourceFromProduction(PlayerBoard playerBoard) {
        playerBoard.addWhiteMarble();
    }
}