package cpsc2150.hw2;

public class BoardPosition {

    private final int x_pos;
    private final int y_pos;
    private final char marker;

    /**
     *
     * @param x int for the x position on the board
     * @param y int for the y position on the board
     * @param mark char for the player's marker
     * @requires
     * x >= 0 and x < 8 and y >= 0 and y < 8
     * @requires mark == 'X' or mark == 'O'
     * @ensures <pre>
     *     BoardPosition object's values are initialized to
     *     the correct values as they will not change.
     * </pre>
     *
     */
    BoardPosition(int x, int y, char mark)
    {
        this.x_pos = x;
        this.y_pos = y;
        this.marker = mark;
    }

    /**
     *
     * @return x position of a cell in the board
     */
    public int getRow()
    {
        return this.x_pos;
    }

    /**
     *
     * @return y position of a cell in the board
     */
    public int getColumn()
    {
        return this.y_pos;
    }

    /**
     *
     * @return the player's marker character for that position
     */
    public char getPlayer()
    {
        return this.marker;
    }

    /**
     * @param obj must be an object and not a primitive data type
     * @return True if the two objects are equal to each other
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof BoardPosition)
        {
            BoardPosition cmpPos = (BoardPosition) obj;

            if (cmpPos.getRow() == this.x_pos)
                if (cmpPos.getColumn() == this.y_pos)
                    if (cmpPos.getPlayer() == this.marker)
                        return true;
        }

        return false;
    }
}
