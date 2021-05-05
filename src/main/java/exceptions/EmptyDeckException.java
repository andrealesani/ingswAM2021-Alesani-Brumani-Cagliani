package Exceptions;

public class EmptyDeckException extends Exception{
    /**
     * Returns the message describing the type of error that occurred
     *
     * @return the message (String) of this exception
     */
    @Override
    public String getMessage() {
        return ("The selected deck is empty");
    }
}