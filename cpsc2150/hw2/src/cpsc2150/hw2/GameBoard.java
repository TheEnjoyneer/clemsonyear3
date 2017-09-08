package cpsc2150.hw2;

import java.lang.StringBuilder;

public class GameBoard {

    private static final int boardSize = 8;
    private static final char BLANK = ' ';

    private char [][] board = new char[boardSize][boardSize];

    GameBoard()
    {
        int i, j;

        for (i = 0; i < boardSize; i++)
        {
            for (j = 0; j < boardSize; j++)
            {
                board[i][j] = BLANK;
            }
        }
    }

    public boolean checkSpace(BoardPosition pos)
    {
        // returns true if the position specified in pos is available,
        // false otherwise

        return (this.board[pos.getXPos()][pos.getYPos()] == BLANK);
    }

    public void placeMarker(BoardPosition marker)
    {
        // places the character in marker on the position specified by marker

        this.board[marker.getXPos()][marker.getYPos()] = marker.getPlayer();
    }

    public boolean checkForWinner(BoardPosition lastPos)
    {
        // this function will check to see if the lastPos placed resulted in a winner.
        // It so it will return true, otherwise false.
        // Passing in the last position will help limit the possible places to check
        // for a win condition, since you can assume that any win condition that
        // did not include the most recent play made would have been caught earlier.

        return checkHorizontalWin(lastPos)
                || checkVerticalWin(lastPos)
                || checkDiagonalWin(lastPos);

    }

    private boolean checkHorizontalWin(BoardPosition lastpos)
    {
        // checks to see if the last marker placed resulted in 5 in a row horizontally.
        // Returns true if it does, otherwise false

        return false;
    }

    private boolean checkVerticalWin(BoardPosition lastpos)
    {
        // checks to see if the last marker placed resulted in 5 in a row vertically.
        // Returns true if it does, otherwise false

        return false;
    }

    private boolean checkDiagonalWin(BoardPosition lastpos)
    {
        // checks to see if the last marker placed resulted in 5 in a row diagonally.
        // Returns true if it does, otherwise false
        // Note: there are two diagonals to check

        return false;
    }

    /**
     *
     * @return toString value of a string that contains the entire board.
     */
    @Override
    public String toString()
    {
        // returns a String that contains the game board to be printed

        StringBuffer preboard = new StringBuffer();
        int i, j;

        preboard.append("  0 1 2 3 4 5 6 7 \n");

        for (i = 0; i < boardSize; i++)
        {
            preboard.append(i);
            preboard.append("|");

            for (j = 0; j < boardSize; j++)
            {
                preboard.append(this.board[i][j]);
                preboard.append("|");
            }

            preboard.append("\n");
        }

        return preboard.toString();
    }
}
