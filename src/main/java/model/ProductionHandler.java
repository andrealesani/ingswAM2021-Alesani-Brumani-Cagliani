package model;

import model.resource.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class was made to simplify the interaction between PlayerBoard and Production classes during a single turn.
 * It holds the information about every Production the player decides to activate during his turn so that all the
 * Productions can be activated at the same time at the end of the turn
 */
public class ProductionHandler {
    private List<Production> productions = new ArrayList<>();
    private Map<Resource, Integer> currentCost= new HashMap<>();

    public void addProduction(Production production){
        productions.add(production);
    }

    public void removeProduction(Production production){
        productions.remove(production);
    }

    /**
     * Marks the specified Production as selected. At the end of the turn, only selected Productions will be activated
     * @param i
     */
    public void selectProduction(int i){
        productions.get(i).select();
    }

    /**
     * Returns the amount of the specified Resource that is needed to activate the selected Productions
     * @param resource
     * @return
     */
    public int getTotalCost(Resource resource){
        //TODO
        return 0;
    }

    /**
     * Activates the selected Productions. In order to do this, the method removes all the input Resources and adds all
     * the output Resources specified in the selected Productions to the specified PlayerBoard
     * @param playerBoard
     */
    public void activateTransaction(PlayerBoard playerBoard){
        //TODO
    }
}