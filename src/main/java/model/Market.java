package model;

import Exceptions.ParametersNotValidException;
import model.resource.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the game's market
 */
//TODO rendere dimensioni matrice parametrici?
public class Market implements Observable {
    /**
     * This matrix stores the market's marbles
     */
    private final Resource[][] board = new Resource[3][4];
    /**
     * This attribute stores the marble in the market's slide
     */
    private Resource slideMarble;

    //CONSTRUCTORS

    /**
     * Constructor
     */
    //TODO togliere else/ifs?
    public Market() {
        int totalOrbs = 0;
        int orb;
        int countRS = 0, countRF = 0, countRSe = 0, countRSh = 0, countRSt = 0, countRW = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (totalOrbs < 12) {
                    orb = (int) (Math.random() * 6);
                    if ((orb == 0) && (countRS <= 1)) {
                        board[i][j] = new ResourceCoin();
                        countRS++;
                    } else if ((orb == 1) && (countRF <= 0)) {
                        board[i][j] = new ResourceFaith();
                        countRF++;
                    } else if ((orb == 2) && (countRSe <= 1)) {
                        board[i][j] = new ResourceServant();
                        countRSe++;
                    } else if ((orb == 3) && (countRSh <= 1)) {
                        board[i][j] = new ResourceShield();
                        countRSh++;
                    } else if ((orb == 4) && (countRSt <= 1)) {
                        board[i][j] = new ResourceStone();
                        countRSt++;
                    } else if ((orb == 5) && (countRW <= 3)) {
                        board[i][j] = new ResourceWhite();
                        countRW++;
                    } else {
                        j--;
                        totalOrbs--;
                    }

                    totalOrbs++;
                    notifyObservers();
                }
            }
        }
        
        if (totalOrbs == 12) {
            if (countRS == 1)
                slideMarble = new ResourceCoin();
            else if (countRF <= 0)
                slideMarble = new ResourceFaith();
            else if (countRSe <= 1)
                slideMarble = new ResourceServant();
            else if (countRSh <= 1)
                slideMarble = new ResourceShield();
            else if (countRSt <= 1)
                slideMarble = new ResourceStone();
            else if (countRW <= 3)
                slideMarble = new ResourceWhite();
        }

        notifyObservers();

    }

    //PUBLIC METHODS

    //TODO non hardcodare dimensioni market

    /**
     * Activates addResourceFromMarket methods on all resources in the selected row
     *
     * @param numScope    the number of the selected row
     * @param playerBoard the player's board
     */
    public void selectRow(int numScope, PlayerBoard playerBoard) {
        if ((numScope <= 0) || (numScope > 3) || (playerBoard == null))
            throw new ParametersNotValidException();

        int riga;

        riga = numScope - 1;
        for (int j = 0; j < 4; j++)
            board[riga][j].addResourceFromMarket(playerBoard);
        shiftRow(numScope);

        notifyObservers();
    }

    /**
     * Activates addResourceFromMarket methods on all resources in the selected column
     *
     * @param numScope    the number of the selected column
     * @param playerBoard the player's board
     */
    public void selectColumn(int numScope, PlayerBoard playerBoard) {
        if ((numScope <= 0) || (numScope > 4) || (playerBoard == null))
            throw new ParametersNotValidException();

        int col;

        col = numScope - 1;
        for (int i = 0; i < 3; i++)
            board[i][col].addResourceFromMarket(playerBoard);
        shiftColumn(numScope);

        notifyObservers();
    }

    //PRIVATE METHODS

    /**
     * Shifts the given row based on the market's rules, substituting the marble on the slide
     *
     * @param numScope the number of the selected row
     */
    private void shiftRow(int numScope) {
        if ((numScope <= 0) || (numScope > 3))
            throw new ParametersNotValidException();

        int riga;
        Resource temp;


        riga = numScope - 1;
        int j = 0;
        temp = board[riga][j];
        for (j = 0; j < 3; j++)
            board[riga][j] = board[riga][j + 1];


        board[riga][j] = slideMarble;
        slideMarble = temp;
    }

    /**
     * Shifts the given column based on the market's rules, substituting the marble on the slide
     *
     * @param numScope the number of the selected column
     */
    private void shiftColumn(int numScope) {
        if ((numScope <= 0) || (numScope > 4))
            throw new ParametersNotValidException();

        int col;
        Resource temp;


        col = numScope - 1;
        int i = 0;
        temp = board[i][col];
        for (i = 0; i < 2; i++)
            board[i][col] = board[i + 1][col];

        board[i][col] = slideMarble;
        slideMarble = temp;
    }

    //GETTERS

    /**
     * Getter
     *
     * @return the marble matrix
     */
    public Resource[][] getBoard() {
        return board;
    }

    /**
     * Getter
     *
     * @param row    the row number
     * @param column the column number
     * @return the marble in the given spot
     */
    public Resource getMarble(int row, int column) {
        if ((row < 0) || (column < 0) || (row >= 3) || (column >= 4))
            throw new ParametersNotValidException();

        return board[row][column];
    }

    /**
     * Getter
     *
     * @return the marble on the market's slide
     */
    public Resource getSlideOrb() {
        return slideMarble;
    }


    // OBSERVABLE ATTRIBUTES AND METHODS

    /**
     * List of observers that need to get updated when the object state changes
     */
    private final List<Observer> observers = new ArrayList<>();

    /**
     * This method calls the update() on every object observing this object
     */
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(this));
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
        notifyObservers();
    }

    public List<Observer> getObservers() {
        return observers;
    }
}
