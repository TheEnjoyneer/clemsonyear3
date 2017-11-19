package cpsc2150.hw2;

import java.lang.StringBuilder;


// Make sure to come in here and add contracts/correspondences
public class GameBoardFast implements IGameBoard {

    private int rows = 0;
    private int cols = 0;
    private int numToWin = 0;
    private static final char BLANK = ' ';


    private char [][] board = new char[MAX_SIZE][MAX_SIZE];

    /**
     * GameBoard constructor initializes the board to all BLANK characters
     * @requires numOfRows >= 0 && numOfCols >= 0 && winNum >= 0
     */
    GameBoardFast(int numOfRows, int numOfCols, int winNum)
    {
        int i, j;

        rows = numOfRows;
        cols = numOfCols;
        numToWin = winNum;

        for (i = 0; i < rows; i++)
        {
            for (j = 0; j < cols; j++)
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
        return (board[pos.getRow()][pos.getColumn()] == BLANK);
    }

    /**
     *
     * @param marker is the player's marker to place in the given position
     * @requires marker != null
     * @ensures that the marker is placed in the array of characters correctly
     */
    public void placeMarker(BoardPosition marker)
    {
        board[marker.getRow()][marker.getColumn()] = marker.getPlayer();
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

        if (col >= numToWin && col < (cols - numToWin - 1))
        {
            for (j = col - numToWin, i = 0; j < col + numToWin; j++, i++)
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

                if (checkWin == numToWin && i == numToWin)
                    return true;

            }
        }
        else
        {
            checkWin = 0;

            for (j = 0, i = 0; j < col + numToWin; j++, i++)
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

                if (checkWin == numToWin && i == numToWin)
                    return true;
            }

            checkWin = 0;

            for (j = col - numToWin - 1, i = 0; j < cols - 1; j++, i++)
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

                if (checkWin == numToWin && i == numToWin)
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
        int i, j, checkWin = 0, col = lastPos.getColumn(), row = lastPos.getRow();
        char marker = lastPos.getPlayer();

        if (row >= numToWin && row < (rows - numToWin - 1))
        {
            for (i = rows - numToWin, j = 0; i < rows + numToWin; i++, j++)
            {
                if (board[i][col] == marker)
                {
                    checkWin++;
                }
                else
                {
                    checkWin = 0;
                    i = 0;
                }

                if (checkWin == numToWin && i == numToWin)
                    return true;
            }
        }
        else
        {
            checkWin = 0;

            for (i = 0, j = 0; i < row + numToWin; i++, j++)
            {
                if (board[i][col] == marker)
                {
                    checkWin++;
                }
                else
                {
                    checkWin = 0;
                    i = 0;
                }

                if (checkWin == numToWin && i == numToWin)
                    return true;
            }

            checkWin = 0;

            for (i = row - numToWin - 1, j = 0; j < rows - 1; i++, j++)
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

                if (checkWin == numToWin && i == numToWin)
                    return true;
            }
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

        while (i2 != 0 &&  j2 != numToWin - 1)
        {
            i2--;
            j2++;
        }

        for (i = row - numToWin; i < row + numToWin; i++)
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

        for (i = i2; i < rows - numToWin - 1; i++)
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

        StringBuilder preboard = new StringBuilder();
        int i, j;

        preboard.append("  |");

        for (i = 0; i < cols; i++)
        {
            if (i < 10)
            {
                preboard.append("  ");
                preboard.append(i);
                preboard.append("|");
            }
            else
            {
                preboard.append(" ");
                preboard.append(i);
                preboard.append("|");
            }
        }

        preboard.append("\n");

        for (i = 0; i < rows; i++)
        {
            if (i < 10)
            {
                preboard.append(i);
                preboard.append(" | ");
            }
            else
            {
                preboard.append(i);
                preboard.append("| ");
            }
            for (j = 0; j < cols; j++)
            {
                preboard.append(board[i][j]);
                preboard.append(" | ");
            }

            preboard.append("\n");
        }

        return preboard.toString();
    }
}
