package cpsc2150.hw2;

import java.util.*;

/**
 *
 * @invariant 0 < rows <= MAX_SIZE
 * @invariant 0 < cols <= MAX_SIZE
 * Correspondence NUM_ROWS = rows
 * Correspondence NUM_COLS = cols
 */
public class GameBoardMem implements IGameBoard {

    private int rows = 0;
    private int cols = 0;
    private int numToWin = 0;

    private List<BoardPosition> listX = new ArrayList<>();
    private List<BoardPosition> listO = new ArrayList<>();

    /**
     *
     * GameBoardMem constructor initializes the board private variables that determine the max list sizes
     * @param numOfRows int value for number of rows
     * @param numOfCols int value for number of columns
     * @param winNum int value for number of markers in a row to win
     * @requires numOfRows >= 0 && numOfCols >= 0 && winNum >= 0
     * @ensures rows, cols, and numToWin private variables are set
     */
     GameBoardMem(int numOfRows, int numOfCols, int winNum)
     {
        rows = numOfRows;
        cols = numOfCols;
        numToWin = winNum;
     }

     boolean checkSpace(BoardPosition pos)
     {
         if (pos.getPlayer() == 'X')
             return listX.contains(pos);
         else
             return listO.contains(pos);
     }

    void placeMarker(BoardPosition marker)
    {
        if (marker.getPlayer() == 'X')
            listX.add(marker);
        else
            listO.add(marker);
    }

    public boolean checkForWinner(BoardPosition lastPos)
    {
        return checkHorizontalWin(lastPos)
                || checkVerticalWin(lastPos)
                || checkDiagonalWin(lastPos);
    }

    private boolean checkHorizontalWin(BoardPosition lastPos)
    {

    }

    private boolean checkVerticalWin(BoardPosition lastPos)
    {

    }

    private boolean checkDiagonalWin(BoardPosition lastPos)
    {
        
    }

}
