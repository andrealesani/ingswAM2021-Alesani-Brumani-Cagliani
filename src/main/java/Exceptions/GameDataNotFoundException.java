package Exceptions;

/**
 * This Exception is thrown when a non-existent save game file is attempted to be accessed
 */
public class GameDataNotFoundException extends Exception {
    /**
     * Returns the message describing the type of error that occurred
     *
     * @return the message (String) of this exception
     */
    @Override
    public String getMessage() {
        return ("Couldn't find the specified file");
    }
}
