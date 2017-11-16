package cpsc2150.hw2;

import java.lang.StringBuffer;


// Make sure to come in here and add contracts/correspondences
public class GameBoardFast implements IGameBoard {

    private static int rows = 0;
    private static int cols = 0;
    private static int numToWin = 0;
    private static final char BLANK = ' ';


    private char [][] board = new char[this.rows][this.cols];

    /**
     * GameBoard constructor initializes the board to all BLANK characters
     * @requires numOfRows >= 0 && numOfCols >= 0 && winNum >= 0
     */
    GameBoardFast(int numOfRows, int numOfCols, int winNum)
    {
        int i, j;

        this.rows = numOfRows;
        this.cols = numOfCols;
        this.numToWin = winNum;

        for (i = 0; i < this.rows; i++)
        {
            for (j = 0; j < this.cols; j++)
            {
                board[i][j] = BLANK;
            }
        }
    }

    /**
     *
     * @param pos is the position in the board to check
     * @requires pos != null and pos contains Row and Col values
     * @return True if the space requested is an open position
     *         returns False otherwise
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
        int i, j, checkWin = 0, col = lastPos.getColumn(), row = lastPos.getRow();
        char marker = lastPos.getPlayer();

        if (col >= this.numToWin && col < (this.cols - this.numToWin - 1))
        {
            for (j = col - this.numToWin, i = 0; j < col + this.numToWin; j++, i++)
            {
                if (board[row][j] == marker)
                {
                    checkWin++;
                }
                else
                {
                    checkWin = 0;
                    i = 0;
                }

                if (checkWin == this.numToWin && i == this.numToWin)
                    return true;

            }
        }
        else
        {
            checkWin = 0;

            for (j = 0, i = 0; j < col + this.numToWin; j++, i++)
            {
                if (board[row][j] == marker)
                {
                    checkWin++;
                }
                else
                {
                    checkWin = 0;
                    i = 0;
                }

                if (checkWin == this.numToWin && i == this.numToWin)
                    return true;
            }

            checkWin = 0;

            for (j = col - this.numToWin - 1, i = 0; j < this.cols - 1; j++, i++)
            {
                if (board[row][j] == marker)
                {
                    checkWin++;
                }
                else
                {
                    checkWin = 0;
                    i = 0;
                }

                if (checkWin == this.numToWin && i == this.numToWin)
                    return true;
            }
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

        for (row = 0; row < this.rows - numToWin - 1; row++)
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

        for (i = i1; i < this.rows - numToWin - 1; i++)
        {
            for (j = j1; j < this.cols - numToWin - 1; j++)
            {
                if (board[i][j] == marker)
                    if (board[i+1][j+1] == marker)
                        if (board[i+2][j+2] == marker)
                            if (board[i+3][j+3] == marker)
                                if (board[i+4][j+4] == marker)
                                    return true;
            }
        }

        for (i = i2; i < this.rows - numToWin - 1; i++)
        {
            // Check for what the j needs to be greater than, used to be j>3
            for (j = j2; j > numToWin - 2; j--)
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

        for (i = 0; i < this.rows; i++)
        {
            preboard.append(i);
            preboard.append("|");

            for (j = 0; j < this.cols; j++)
            {
                preboard.append(this.board[i][j]);
                preboard.append("|");
            }

            preboard.append("\n");
        }

        return preboard.toString();
    }
}
