package cpsc2150.hw2;

import junit.framework.TestCase;
import org.junit.*;

public class TestGameBoard extends TestCase {

    private GameBoard xoboard;

    @Before
    public void setUp()
    {
        xoboard = new GameBoard();
    }

    @After
    public void tearDown()
    {
        xoboard = null;
    }

    // checkSpace() test 1 - available
    @Test
    public void testCheckSpace_R0_C0_T()
    {
        BoardPosition testPos = new BoardPosition(0, 0, 'X');
        boolean testResponse = xoboard.checkSpace(testPos);

        // Test for expected open space
        assertTrue(testResponse);
    }

    // checkSpace() test 2 - not available
    @Test
    public void testCheckSpace_R0_C0_F()
    {
        BoardPosition testPos = new BoardPosition(0, 0, 'X');

        // Place fullPos into position (0,0)
        xoboard.placeMarker(testPos);
        boolean testResponse = xoboard.checkSpace(testPos);

        // Test for expected open space
        assertFalse(testResponse);
    }

    // checkSpace() test 3 - available
    @Test
    public void testCheckSpace_R7_C7_T()
    {
        BoardPosition testPos = new BoardPosition(7, 7, 'X');
        boolean testResponse = xoboard.checkSpace(testPos);

        // Test for expected open space
        assertTrue(testResponse);
    }

    // checkSpace() test 4 - not available
    @Test
    public void testCheckSpace_R7_C7_F()
    {
        BoardPosition testPos = new BoardPosition(7, 7, 'X');

        // Place fullPos into position (7,7)
        xoboard.placeMarker(testPos);
        boolean testResponse = xoboard.checkSpace(testPos);

        // Test for expected open space
        assertFalse(testResponse);
    }

    // checkSpace() test 5 - available
    @Test
    public void testCheckSpace_R4_C4_T()
    {
        BoardPosition testPos = new BoardPosition(4, 4, 'X');
        boolean testResponse = xoboard.checkSpace(testPos);

        // Test for expected open space
        assertTrue(testResponse);
    }

    // placeMarker() test 1 - places correctly
    @Test
    public void testPlaceMarker_R0_C0()
    {
        // Declare and place BoardPosition (0,0) with marker X
        BoardPosition testPos = new BoardPosition(0,0,'X');
        xoboard.placeMarker(testPos);

        // Create test board to check against
        int i, j, boardSize = 8;
        char [][] testBoard = new char[boardSize][boardSize];

        for (i = 0; i < boardSize; i++)
        {
            for (j = 0; j < boardSize; j++)
            {
                if (i == 0 && j == 0)
                    testBoard[i][j] = 'X';
                else
                    testBoard[i][j] = ' ';
            }
        }

        // Change test board to a string
        StringBuffer preboard = new StringBuffer();

        preboard.append("  0 1 2 3 4 5 6 7 \n");

        for (i = 0; i < boardSize; i++)
        {
            preboard.append(i);
            preboard.append("|");

            for (j = 0; j < boardSize; j++)
            {
                preboard.append(testBoard[i][j]);
                preboard.append("|");
            }

            preboard.append("\n");
        }

        assertEquals(preboard.toString(), xoboard.toString());
    }

    // placeMarker() test 2
    @Test
    public void testPlaceMarker_R0_C7()
    {
        // Declare and place BoardPosition (0,0) with marker X
        BoardPosition testPos = new BoardPosition(0,7,'X');
        xoboard.placeMarker(testPos);

        // Create test board to check against
        int i, j, boardSize = 8;
        char [][] testBoard = new char[boardSize][boardSize];

        for (i = 0; i < boardSize; i++)
        {
            for (j = 0; j < boardSize; j++)
            {
                if (i == 0 && j == 7)
                    testBoard[i][j] = 'X';
                else
                    testBoard[i][j] = ' ';
            }
        }

        // Change test board to a string
        StringBuffer preboard = new StringBuffer();

        preboard.append("  0 1 2 3 4 5 6 7 \n");

        for (i = 0; i < boardSize; i++)
        {
            preboard.append(i);
            preboard.append("|");

            for (j = 0; j < boardSize; j++)
            {
                preboard.append(testBoard[i][j]);
                preboard.append("|");
            }

            preboard.append("\n");
        }

        assertEquals(preboard.toString(), xoboard.toString());
    }

    // placeMarker() test 3
    @Test
    public void testPlaceMarker_R7_C0()
    {
        // Declare and place BoardPosition (0,0) with marker X
        BoardPosition testPos = new BoardPosition(7,0,'X');
        xoboard.placeMarker(testPos);

        // Create test board to check against
        int i, j, boardSize = 8;
        char [][] testBoard = new char[boardSize][boardSize];

        for (i = 0; i < boardSize; i++)
        {
            for (j = 0; j < boardSize; j++)
            {
                if (i == 7 && j == 0)
                    testBoard[i][j] = 'X';
                else
                    testBoard[i][j] = ' ';
            }
        }

        // Change test board to a string
        StringBuffer preboard = new StringBuffer();

        preboard.append("  0 1 2 3 4 5 6 7 \n");

        for (i = 0; i < boardSize; i++)
        {
            preboard.append(i);
            preboard.append("|");

            for (j = 0; j < boardSize; j++)
            {
                preboard.append(testBoard[i][j]);
                preboard.append("|");
            }

            preboard.append("\n");
        }

        assertEquals(preboard.toString(), xoboard.toString());
    }

    // placeMarker() test 4
    @Test
    public void testPlaceMarker_R7_C7()
    {
        // Declare and place BoardPosition (0,0) with marker X
        BoardPosition testPos = new BoardPosition(7,7,'X');
        xoboard.placeMarker(testPos);

        // Create test board to check against
        int i, j, boardSize = 8;
        char [][] testBoard = new char[boardSize][boardSize];

        for (i = 0; i < boardSize; i++)
        {
            for (j = 0; j < boardSize; j++)
            {
                if (i == 7 && j == 7)
                    testBoard[i][j] = 'X';
                else
                    testBoard[i][j] = ' ';
            }
        }

        // Change test board to a string
        StringBuffer preboard = new StringBuffer();

        preboard.append("  0 1 2 3 4 5 6 7 \n");

        for (i = 0; i < boardSize; i++)
        {
            preboard.append(i);
            preboard.append("|");

            for (j = 0; j < boardSize; j++)
            {
                preboard.append(testBoard[i][j]);
                preboard.append("|");
            }

            preboard.append("\n");
        }

        assertEquals(preboard.toString(), xoboard.toString());
    }

    // placeMarker() test 5
    @Test
    public void testPlaceMarker_R3_C3()
    {
        // Declare and place BoardPosition (0,0) with marker X
        BoardPosition testPos = new BoardPosition(3,3,'X');
        xoboard.placeMarker(testPos);

        // Create test board to check against
        int i, j, boardSize = 8;
        char [][] testBoard = new char[boardSize][boardSize];

        for (i = 0; i < boardSize; i++)
        {
            for (j = 0; j < boardSize; j++)
            {
                if (i == 3 && j == 3)
                    testBoard[i][j] = 'X';
                else
                    testBoard[i][j] = ' ';
            }
        }

        // Change test board to a string
        StringBuffer preboard = new StringBuffer();

        preboard.append("  0 1 2 3 4 5 6 7 \n");

        for (i = 0; i < boardSize; i++)
        {
            preboard.append(i);
            preboard.append("|");

            for (j = 0; j < boardSize; j++)
            {
                preboard.append(testBoard[i][j]);
                preboard.append("|");
            }

            preboard.append("\n");
        }

        assertEquals(preboard.toString(), xoboard.toString());
    }

    // placeMarker() test 6
    @Test
    public void testPlaceMarker_R4_C4()
    {
        // Declare and place BoardPosition (0,0) with marker X
        BoardPosition testPos = new BoardPosition(4,4,'X');
        xoboard.placeMarker(testPos);

        // Create test board to check against
        int i, j, boardSize = 8;
        char [][] testBoard = new char[boardSize][boardSize];

        for (i = 0; i < boardSize; i++)
        {
            for (j = 0; j < boardSize; j++)
            {
                if (i == 4 && j == 4)
                    testBoard[i][j] = 'X';
                else
                    testBoard[i][j] = ' ';
            }
        }

        // Change test board to a string
        StringBuffer preboard = new StringBuffer();

        preboard.append("  0 1 2 3 4 5 6 7 \n");

        for (i = 0; i < boardSize; i++)
        {
            preboard.append(i);
            preboard.append("|");

            for (j = 0; j < boardSize; j++)
            {
                preboard.append(testBoard[i][j]);
                preboard.append("|");
            }

            preboard.append("\n");
        }

        assertEquals(preboard.toString(), xoboard.toString());
    }

    // checkForWinner() test 1 - horizontal true
    @Test
    public void testCheckForWinnerHorizontal_T1()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(0,0, 'X');
        BoardPosition pos2 = new BoardPosition(0,1, 'X');
        BoardPosition pos3 = new BoardPosition(0,2, 'X');
        BoardPosition pos4 = new BoardPosition(0,3, 'X');
        BoardPosition pos5 = new BoardPosition(0,4, 'X');

        //
        xoboard.placeMarker(pos1);
    }

    // checkForWinner() test 2 - horizontal true
    @Test
    public void testCheckForWinnerHorizontal_T2()
    {

    }

    // checkForWinner() test 3 - horizontal false
    @Test
    public void testCheckForWinnerHorizontal_F1()
    {

    }

    // checkForWinner() test 4 - horizontal false
    @Test
    public void testCheckForWinnerHorizontal_F2()
    {

    }

    // checkForWinner() test 5 - vertical true
    @Test
    public void testCheckForWinnerVertical_T1()
    {

    }

    // checkForWinner() test 6 - vertical true
    @Test
    public void testCheckForWinnerVertical_T2()
    {

    }

    // checkForWinner() test 7 - vertical false
    @Test
    public void testCheckForWinnerVertical_F1()
    {

    }

    // checkForWinner() test 8 - vertical false
    @Test
    public void testCheckForWinnerVertical_F2()
    {

    }

    // checkForWinner() test 9 - major diagonal true
    @Test
    public void testCheckForWinnerMajorDiagonal_T1()
    {

    }

    // checkForWinner() test 10 - major diagonal true
    @Test
    public void testCheckForWinnerMajorDiagonal_T2()
    {

    }

    // checkForWinner() test 11 - major diagonal false
    @Test
    public void testCheckForWinnerMajorDiagonal_F1()
    {

    }

    // checkForWinner() test 12 - major diagonal false
    @Test
    public void testCheckForWinnerMajorDiagonal_F2()
    {

    }

    // checkForWinner() test 13 - minor diagonal true
    @Test
    public void testCheckForWinnerMinorDiagonal_T1()
    {

    }

    // checkForWinner() test 14 - minor diagonal true
    @Test
    public void testCheckForWinnerMinorDiagonal_T2()
    {

    }

    // checkForWinner() test 15 - minor diagonal false
    @Test
    public void testCheckForWinnerMinorDiagonal_F1()
    {

    }

    // checkForWinner() test 16 - minor diagonal false
    @Test
    public void testCheckForWinnerMinorDiagonal_F2()
    {

    }

}
