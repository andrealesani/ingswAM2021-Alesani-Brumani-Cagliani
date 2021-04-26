package model.lorenzo.tokens;

import Exceptions.ParametersNotValidException;
import model.lorenzo.Lorenzo;
import network.beans.TokenType;

/**
 * Represents the token that increases Lorenzo's faith by 1 and then shuffles the entire action tokens deck
 */
public class SingleFaithShuffleToken extends ActionToken {
    /**
     * This attribute stores the token's owner a.i.
     */
    private final Lorenzo lorenzo;

    //CONSTRUCTORS

    /**
     * Constructor
     *
     * @param lorenzo reference to Lorenzo instance
     */
    public SingleFaithShuffleToken(Lorenzo lorenzo) {
        super(TokenType.SingleFaith);

        if (lorenzo == null) {
            throw new ParametersNotValidException();
        }
        this.lorenzo = lorenzo;
    }

    //PUBLIC METHODS

    /**
     * Standard method in the interface that calls the class-specific method singleIncreaseShuffle()
     */
    @Override
    public void doAction() {
        singleIncreaseShuffle();
    }

    //PRIVATE METHODS

    /**
     * Increases Lorenzo's faith by 1 and then shuffles the entire action tokens deck
     */
    private void singleIncreaseShuffle() {
        lorenzo.addFaith(1);
        lorenzo.shuffleDeck();
    }
}
