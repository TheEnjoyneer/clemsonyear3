package cpsc2150.hw2;

import java.util.*;

/**
 *
 * @invariant MIN_SIZE < rows <= MAX_SIZE
 * @invariant MIN_SIZE < cols <= MAX_SIZE
 * @invariant MIN_WIN < numToWin <= MAX_SIZE
 * Correspondence NUM_ROWS = rows
 * Correspondence NUM_COLS = cols
 * Correspondence NUM_TO_WIN = numToWin
 */
public class GameBoardMem implements IGameBoard {

    private int rows = MIN_SIZE;
    private int cols = MIN_SIZE;
    private int numToWin = MIN_WIN;

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

    @Override
    public boolean checkSpace(BoardPosition pos)
    {
         if (pos.getPlayer() == 'X')
             return !(listX.contains(pos));
         else
             return !(listO.contains(pos));
    }

    @Override
    public void placeMarker(BoardPosition marker)
    {
        if (marker.getPlayer() == 'X')
            listX.add(marker);
        else
            listO.add(marker);
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
        List<BoardPosition> checkList;
        BoardPosition checkPos;

        if (marker == 'X')
            checkList = listX;
        else
            checkList = listO;

        for (i = 1; i <= numToWin; i++)
        {
            col = checkCol - i;
            // Check when the value is outside of bounds
            if (col < 0)
                break;

            checkPos = new BoardPosition(checkRow, col, marker);

            if (checkList.contains(checkPos))
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

            checkPos = new BoardPosition(checkRow, col, marker);

            if (checkList.contains(checkPos))
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
        List<BoardPosition> checkList;
        BoardPosition checkPos;

        if (marker == 'X')
            checkList = listX;
        else
            checkList = listO;

        for (i = 1; i <= numToWin; i++)
        {
            row = checkRow - i;
            // Check when the value is outside of bounds
            if (row < 0)
                break;

            checkPos = new BoardPosition(row, checkCol, marker);

            if (checkList.contains(checkPos))
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

            checkPos = new BoardPosition(row, checkCol, marker);

            if (checkList.contains(checkPos))
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
        List<BoardPosition> checkList;
        BoardPosition checkPos;

        if (marker == 'X')
            checkList = listX;
        else
            checkList = listO;

        for (i = 1, j = 1; i <= numToWin && j <= numToWin; i++, j++)
        {
            row = checkRow - i;
            col = checkCol - i;
            // Check when the value is outside of bounds
            if (row < 0 || col < 0)
                break;

            checkPos = new BoardPosition(row, col, marker);

            if (checkList.contains(checkPos))
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

            checkPos = new BoardPosition(row, col, marker);

            if (checkList.contains(checkPos))
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

            checkPos = new BoardPosition(row, col, marker);

            if (checkList.contains(checkPos))
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

            checkPos = new BoardPosition(row, col, marker);

            if (checkList.contains(checkPos))
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
        StringBuilder preboard = new StringBuilder();
        int i, j;
        BoardPosition printPosX;
        BoardPosition printPosO;

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
                printPosX = new BoardPosition(i, j, 'X');
                printPosO = new BoardPosition(i, j, 'O');

                if (listX.contains(printPosX))
                    preboard.append('X');
                else if (listO.contains(printPosO))
                    preboard.append('O');
                else
                    preboard.append(' ');

                preboard.append(" | ");
            }

            preboard.append("\n");
        }

        return preboard.toString();
    }
}
