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

}
