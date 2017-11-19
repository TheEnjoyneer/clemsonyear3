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

    @Override
    public boolean checkSpace(BoardPosition pos)
    {
         if (pos.getPlayer() == 'X')
             return listX.contains(pos);
         else
             return listO.contains(pos);
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

    private boolean checkHorizontalWin(BoardPosition lastPos)
    {
        int checkWinCount = 1, checkCol = lastPos.getColumn(), checkRow = lastPos.getRow();
        int i, col;
        char marker = lastPos.getPlayer();
        List<BoardPosition> checkList = null;
        BoardPosition checkPos = null;

        if (marker == 'X')
            checkList = listX;
        else
            checkList = listO;

        for (i = 0; i < numToWin; i++)
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

        for (i = 0; i < numToWin; i++)
        {
            col = checkCol + i;
            // Check when the value is outside of bounds
            if (col > MAX_SIZE - 1)
                break;

            checkPos = new BoardPosition(checkRow, col, marker);

            if (checkList.contains(checkPos))
                checkWinCount++;
            else
                break;
        }

        return checkWinCount >= numToWin;
    }

    private boolean checkVerticalWin(BoardPosition lastPos)
    {
        int checkWinCount = 1, checkCol = lastPos.getColumn(), checkRow = lastPos.getRow();
        int i, row;
        char marker = lastPos.getPlayer();
        List<BoardPosition> checkList = null;
        BoardPosition checkPos = null;

        if (marker == 'X')
            checkList = listX;
        else
            checkList = listO;

        for (i = 0; i < numToWin; i++)
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

        for (i = 0; i < numToWin; i++)
        {
            row = checkRow + i;
            // Check when the value is outside of bounds
            if (row > MAX_SIZE - 1)
                break;

            checkPos = new BoardPosition(row, checkCol, marker);

            if (checkList.contains(checkPos))
                checkWinCount++;
            else
                break;
        }

        return checkWinCount >= numToWin;
    }

    private boolean checkDiagonalWin(BoardPosition lastPos)
    {
        int checkRow = lastPos.getRow();
        int checkCol = lastPos.getColumn();
        char marker = lastPos.getPlayer();
        int i, j, row, col;
        int checkWinCount = 1;
        List<BoardPosition> checkList = null;
        BoardPosition checkPos = null;

        if (marker == 'X')
            checkList = listX;
        else
            checkList = listO;

        for (i = 0, j = 0; i < numToWin && j < numToWin; i++, j++)
        {
            row = checkRow - i;
            col = checkCol - i;
            // Check when the value is outside of bounds
            if (row < 0 || col < 0)
                break;

            checkPos = new BoardPosition(row, col, marker);

            if (checkList.contains(checkPos));
                checkWinCount++;
            else
                break;
        }

        for (i = 0, j = 0; i < numToWin && j < numToWin; i++, j++)
        {
            row = checkRow + i;
            col = checkCol + i;
            // Check when the value is outside of bounds
            if (row < 0 || col < 0)
                break;

            checkPos = new BoardPosition(row, col, marker);

            if (checkList.contains(checkPos));
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
        StringBuilder preboard = new StringBuilder();
        int i, j;
        BoardPosition printPosX = null;
        BoardPosition printPosO = null;

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
    }
}
