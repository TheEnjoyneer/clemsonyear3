package cpsc2150.hw5;

import java.lang.StringBuffer;

/**
 *
 * Completed by cbrant on 11/9/19
 *
 * Ensured correct for hw5 turn in on 12/5/17
 *
 * @invariant MIN_SIZE < rows <= MAX_SIZE
 * @invariant MIN_SIZE < cols <= MAX_SIZE
 * @invariant MIN_WIN < numToWin <= MAX_SIZE
 * Correspondence NUM_ROWS = rows
 * Correspondence NUM_COLS = cols
 * Correspondence NUM_TO_WIN = numToWin
 */
public class GameBoardFast implements IGameBoard {

    private int rows = MIN_SIZE;
    private int cols = MIN_SIZE;
    private int numToWin = MIN_WIN;
    private static final char BLANK = ' ';

    private char [][] board = null;

    /**
     *
     * GameBoardFast constructor initializes the board to all BLANK characters
     * @param numOfRows int value for number of rows
     * @param numOfCols int value for number of columns
     * @param winNum int value for number of markers in a row to win
     * @requires numOfRows >= MIN_SIZE && numOfCols >= MIN_SIZE && winNum >= MIN_SIZE
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

    @Override
    public boolean checkSpace(BoardPosition pos)
    {
        return (board[pos.getRow()][pos.getColumn()] == BLANK);
    }

    @Override
    public void placeMarker(BoardPosition marker)
    {
        board[marker.getRow()][marker.getColumn()] = marker.getPlayer();
    }

    @Override
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

        for (i = 1; i <= numToWin; i++)
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

        for (i = 1; i <= numToWin; i++)
        {
            col = checkCol + i;
            // Check when the value is outside of bounds
            if (col > cols - 1)
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

        for (i = 1; i <= numToWin; i++)
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

        for (i = 1; i <= numToWin; i++)
        {
            row = checkRow + i;
            // Check when the value is outside of bounds
            if (row > rows - 1)
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
        int checkMajorWin = 1;
        int checkMinorWin = 1;

        for (i = 1, j = 1; i <= numToWin && j <= numToWin; i++, j++)
        {
            row = checkRow - i;
            col = checkCol - i;
            // Check when the value is outside of bounds
            if (row < 0 || col < 0)
                break;

            if (board[row][col] == marker)
                checkMajorWin++;
            else
                break;
        }

        for (i = 1, j = 1; i <= numToWin && j <= numToWin; i++, j++)
        {
            row = checkRow + i;
            col = checkCol + i;
            // Check when the value is outside of bounds
            if (row > rows - 1 || col > cols - 1)
                break;

            if (board[row][col] == marker)
                checkMajorWin++;
            else
                break;
        }

        for (i = 1, j = 1; i <= numToWin && j <= numToWin; i++, j++)
        {
            row = checkRow - i;
            col = checkCol + i;
            // Check when the value is outside of bounds
            if (row < 0 || col > cols - 1)
                break;

            if (board[row][col] == marker)
                checkMinorWin++;
            else
                break;
        }

        for (i = 1, j = 1; i <= numToWin && j <= numToWin; i++, j++)
        {
            row = checkRow + i;
            col = checkCol - i;
            // Check when the value is outside of bounds
            if (row > rows - 1 || col < 0)
                break;

            if (board[row][col] == marker)
                checkMinorWin++;
            else
                break;
        }

        return (checkMajorWin >= numToWin) || (checkMinorWin >= numToWin);
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
