package it.polimi.ingsw.Exceptions;

/**
 * This Exception is thrown when a leader card is attempted to be activated despite the player not meeting its requirements.
 */
public class LeaderRequirementsNotMetException extends Exception{
    /**
     * Returns the message describing the type of error that occurred
     *
     * @return - the message (String) of this exception
     */
    @Override
    public String getMessage() {
        return ("Player does not fulfill all card requirements.");
    }
}