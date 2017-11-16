package cpsc2150.hw2;

/** IGameBoard represents a 2-dimensional gameboard that has characters
 * on it as markers (X, O). No space on the board can have multiple
 * players, and there can be a clear winner. Board is NUM_ROWS x NUM_COLS in size
 *
 * Initialization ensures: the Board does not have any markers on it
 * Defines: NUM_ROWS: Z
 * Defines: NUM_COLS: Z
 * Constraints: 0< NUM_ROWS <= MAX_SIZE
 *              0< NUM_COLS <= MAX_SIZE
 */
public interface IGameBoard {
    int MAX_SIZE = 100;

    /**
     *
     * @param pos is the position in the board to check
     * @requires pos != null and pos contains Row and Col values
     * @return True if the space requested is an open position
     *         returns False otherwise
     */
    boolean checkSpace(BoardPosition pos);

    /**
     *
     * @param marker is the player's marker to place in the given position
     * @requires marker != null
     * @ensures that the marker character and location is recorded and placed into the board
     */
    void placeMarker(BoardPosition marker);

    /**
     *
     * @param lastPos is the most recent position that has been set on the board
     * @requires lastPos != null
     * @return True if any of the win conditions return True
     * @ensures that all possible win conditions are checked
     */
    boolean checkForWinner(BoardPosition lastPos);
}
