package cpsc2150.hw2;

import java.lang.StringBuilder;


/**
 *
 * @invariant 0 < rows <= MAX_SIZE
 * @invariant 0 < cols <= MAX_SIZE
 * Correspondence NUM_ROWS = rows
 * Correspondence NUM_COLS = cols
 * Correspondence this = board[0...rows-1][0...cols-1]
 */
public class GameBoardFast implements IGameBoard {

    private int rows = 0;
    private int cols = 0;
    private int numToWin = 0;
    private static final char BLANK = ' ';

    private char [][] board = null;

    /**
     *
     * GameBoardFast constructor initializes the board to all BLANK characters
     * @param numOfRows int value for number of rows
     * @param numOfCols int value for number of columns
     * @param winNum int value for number of markers in a row to win
     * @requires numOfRows >= 0 && numOfCols >= 0 && winNum >= 0
     * @ensures rows, cols, and numToWin private variables are set
     */
    GameBoardFast(int numOfRows, int numOfCols, int winNum)
    {
        int i, j;

        rows = numOfRows;
        cols = numOfCols;
        numToWin = winNum;

        board = new char[rows][cols];

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
        int checkWinCount = 1, checkCol = lastPos.getColumn(), checkRow = lastPos.getRow();
        int i, col;
        char marker = lastPos.getPlayer();

        for (i = 0; i < numToWin; i++)
        {
            col = checkCol - i;
            // Check when the value is outside of bounds
            if (col < 0)
                break;

            if (board[checkRow][col] == marker)
                checkWinCount++;
            else
                break;
        }

        for (i = 0; i < numToWin; i++)
        {
            col = checkCol + i;
            // Check when the value is outside of bounds
            if (col > MAX_SIZE - 1)
                break;

            if (board[checkRow][col] == marker)
                checkWinCount++;
            else
                break;
        }

        return checkWinCount >= numToWin;
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
        int checkWinCount = 1, checkCol = lastPos.getColumn(), checkRow = lastPos.getRow();
        int i, row;
        char marker = lastPos.getPlayer();

        for (i = 0; i < numToWin; i++)
        {
            row = checkRow - i;
            // Check when the value is outside of bounds
            if (row < 0)
                break;

            if (board[row][checkCol] == marker)
                checkWinCount++;
            else
                break;
        }

        for (i = 0; i < numToWin; i++)
        {
            row = checkRow + i;
            // Check when the value is outside of bounds
            if (row > MAX_SIZE - 1)
                break;

            if (board[row][checkCol] == marker)
                checkWinCount++;
            else
                break;
        }

        return checkWinCount >= numToWin;
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
        int checkRow = lastPos.getRow();
        int checkCol = lastPos.getColumn();
        char marker = lastPos.getPlayer();
        int i, j, row, col;
        int checkWinCount = 1;

        for (i = 0, j = 0; i < numToWin && j < numToWin; i++, j++)
        {
            row = checkRow - i;
            col = checkCol - i;
            // Check when the value is outside of bounds
            if (row < 0 || col < 0)
                break;

            if (board[row][col] == marker)
                checkWinCount++;
            else
                break;
        }

        return checkWinCount >= numToWin;
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
