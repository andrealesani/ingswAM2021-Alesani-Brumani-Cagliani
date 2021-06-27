package network;

/**
 * This enum contains all possible types of messages sent by the server to the client
 */
public enum ServerMessageType {
    //View update messages
    /**
     * The game status data
     */
    GAME,
    /**
     * The player board data for one player
     */
    PLAYERBOARD,
    /**
     * The market data
     */
    MARKET,
    /**
     * The card table data
     */
    CARDTABLE,
    /**
     * The lorenzo data
     */
    LORENZO,
    /**
     * The strongbox data for one player
     */
    STRONGBOX,
    /**
     * The warehouse data for one player
     */
    WAREHOUSE,
    /**
     * The waiting room data for one player
     */
    WAITINGROOM,
    /**
     * The productions data for one player
     */
    PRODUCTIONHANDLER,

    //Informative messages
    /**
     * Informative messages
     */
    INFO,
    /**
     * Error messages
     */
    ERROR,

    //Event signaling messages
    /**
     * Signals the beginning of the game
     */
    GAME_START,
    /**
     * Signals the wait for more players to join
     */
    WAIT_PLAYERS,
    /**
     * Signals the registration of the client's username
     */
    SET_USERNAME,
    /**
     * Signals the end of the game
     */
    GAME_END,
    /**
     * Signals the connection of a player to the game
     */
    PLAYER_CONNECTED,
    /**
     * Signals the disconnection of a player from the game
     */
    PLAYER_DISCONNECTED,
}
