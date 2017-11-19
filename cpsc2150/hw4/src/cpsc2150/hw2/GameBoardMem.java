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
     * GameBoardMem constructor initializes the board to all BLANK characters
     * @requires numOfRows >= 0 && numOfCols >= 0 && winNum >= 0
     */
}
