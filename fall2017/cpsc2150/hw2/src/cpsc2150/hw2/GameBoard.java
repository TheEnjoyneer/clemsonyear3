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
     * @requires pos != null
     * @return True if the space requested is an open position
     */
    public boolean checkSpace(BoardPosition pos)
    {
        return (this.board[pos.getRow()][pos.getColumn()] == BLANK);
    }

    /**
     *
     * @param marker is the player's marker to place in the given position
     * @requires marker != null
     * @ensures that the marker is placed in the array of characters correctly
     */
    public void placeMarker(BoardPosition marker)
    {
        this.board[marker.getRow()][marker.getColumn()] = marker.getPlayer();
    }

    /**
     *
     * @param lastPos is the most recent position that has been set on the board
     * @requires lastPos != null
     * @return True if any of the win conditions return True
     * @ensures that all possible win conditions are checked
     */
    public boolean checkForWinner(BoardPosition lastPos)
    {
        return checkHorizontalWin(lastPos)
                || checkVerticalWin(lastPos)
                || checkDiagonalWin(lastPos);
    }

    /**
     *
     * @param lastPos is the most recent position that has been set on the board
     * @requires lastPos be a valid and initialized BoardPosition object
     * @return True if the last position is placed in a position to make 5 in a row horizontally
     * @ensures Win conditions for lastPos's row are checked
     */
    private boolean checkHorizontalWin(BoardPosition lastPos)
    {
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
     * @return True if the last position is placed in a position to make 5 in a row vertically
     * @ensures Win conditions for lastPos's column are checked
     */
    private boolean checkVerticalWin(BoardPosition lastPos)
    {
        int row, col = lastPos.getColumn();
        char marker = lastPos.getPlayer();

        for (row = 0; row < boardSize - 4; row++)
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
     * @return True if the last position is placed in a position to make 5 in a row diagonally
     * @ensures The diagonal including lastPos is checked for a win condition
     */
    private boolean checkDiagonalWin(BoardPosition lastPos)
    {
        int row = lastPos.getRow();
        int col = lastPos.getColumn();
        int i, i1 = row, i2 = row;
        int j, j1 = col, j2 = col;
        char marker = lastPos.getPlayer();

        while (i1 != 0 && j1 != 0)
        {
            i1--;
            j1--;
        }

        while (i2 != 0 &&  j2 != 7)
        {
            i2--;
            j2++;
        }

        for (i = i1; i < boardSize - 4; i++)
        {
            for (j = j1; j < boardSize - 4; j++)
            {
                if (board[i][j] == marker)
                    if (board[i+1][j+1] == marker)
                        if (board[i+2][j+2] == marker)
                            if (board[i+3][j+3] == marker)
                                if (board[i+4][j+4] == marker)
                                    return true;
            }
        }

        for (i = i2; i < boardSize - 4; i++)
        {
            for (j = j2; j > 3; j--)
            {
                if (board[i][j] == marker)
                    if (board[i+1][j-1] == marker)
                        if (board[i+2][j-2] == marker)
                            if (board[i+3][j-3] == marker)
                                if (board[i+4][j-4] == marker)
                                    return true;
            }
        }

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
