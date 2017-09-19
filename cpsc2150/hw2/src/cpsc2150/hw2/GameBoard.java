package cpsc2150.hw2;

import java.lang.StringBuffer;

public class GameBoard {

    private static final int boardSize = 8;
    private static final char BLANK = ' ';

    private char [][] board = new char[boardSize][boardSize];

    /**
     * GameBoard constructor initializes the board to all BLANK characters
     */
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

    /**
     *
     * @param pos is the position in the board to check
     * @requires that pos is a valid and initialized BoardPosition object
     * @return a boolean that is true if the position is open and false otherwise
     */
    public boolean checkSpace(BoardPosition pos)
    {
        return (this.board[pos.getRow()][pos.getColumn()] == BLANK);
    }

    /**
     *
     * @param marker is the player's marker to place in the given position
     * @requires marker be a valid and initialized BoardPosition object
     * @ensures that the marker is placed in the array of characters correctly
     */
    public void placeMarker(BoardPosition marker)
    {
        this.board[marker.getRow()][marker.getColumn()] = marker.getPlayer();
    }

    /**
     *
     * @param lastPos is the most recent position that has been set on the board
     * @requires lastPos be a valid and initialized BoardPosition object
     * @return a boolean as to whether or not the game has been won
     * @ensures that all possible win conditions are checked
     */
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

    /**
     *
     * @param lastPos is the most recent position that has been set on the board
     * @requires lastPos be a valid and initialized BoardPosition object
     * @return a boolean as to whether or not the game was won horizontally
     * @ensures that every row is checked for a win condition
     */
    private boolean checkHorizontalWin(BoardPosition lastPos)
    {
        // checks to see if the last marker placed resulted in 5 in a row horizontally.
        // Returns true if it does, otherwise false
        int col, row = lastPos.getRow();
        char marker = lastPos.getPlayer();

        for (col = 0; col < boardSize - 4; col++)
        {
            if (board[row][col] == marker)
                if (board[row][col+1] == marker)
                    if (board[row][col+2] == marker)
                        if (board[row][col+3] == marker)
                            if (board[row][col+4] == marker)
                                return true;
        }

        return false;
    }

    /**
     *
     * @param lastPos is the most recent position that has been set on the board
     * @requires lastPos be a valid and initialized BoardPosition object
     * @return a boolean as to whether or not the game was won vertically
     * @ensures every column is checked for a win condition
     */
    private boolean checkVerticalWin(BoardPosition lastPos)
    {
        // checks to see if the last marker placed resulted in 5 in a row vertically.
        // Returns true if it does, otherwise false
        int row, col = lastPos.getColumn();
        char marker = lastPos.getPlayer();

        for (col = 0; col < boardSize - 4; col++)
        {
            if (board[row][col] == marker)
                if (board[row+1][col] == marker)
                    if (board[row+2][col] == marker)
                        if (board[row+3][col] == marker)
                            if (board[row+4][col] == marker)
                                return true;
        }

        return false;
    }

    /**
     *
     * @param lastPos is the most recent position that has been set on the board
     * @requires lastPos be a valid and initialized BoardPosition object
     * @return a boolean as to whether or not the game was won diagonally
     * @ensures every diagonal is checked for a win condition
     */
    private boolean checkDiagonalWin(BoardPosition lastPos)
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
