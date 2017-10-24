package cpsc2150.hw2;

import junit.framework.TestCase;
import org.junit.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;


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
    public void testCheckForWinnerHorizontal_R0_C04_T1()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(0,0, 'X');
        BoardPosition pos2 = new BoardPosition(0,1, 'X');
        BoardPosition pos3 = new BoardPosition(0,2, 'X');
        BoardPosition pos4 = new BoardPosition(0,3, 'X');
        BoardPosition pos5 = new BoardPosition(0,4, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertTrue(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 2 - horizontal true
    @Test
    public void testCheckForWinnerHorizontal_R0_C37_T2()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(0,3, 'X');
        BoardPosition pos2 = new BoardPosition(0,4, 'X');
        BoardPosition pos3 = new BoardPosition(0,5, 'X');
        BoardPosition pos4 = new BoardPosition(0,6, 'X');
        BoardPosition pos5 = new BoardPosition(0,7, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertTrue(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 3 - horizontal true
    @Test
    public void testCheckForWinnerHorizontal_R7_C04_T3()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(7,0, 'X');
        BoardPosition pos2 = new BoardPosition(7,1, 'X');
        BoardPosition pos3 = new BoardPosition(7,2, 'X');
        BoardPosition pos4 = new BoardPosition(7,3, 'X');
        BoardPosition pos5 = new BoardPosition(7,4, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertTrue(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 4 - horizontal true
    @Test
    public void testCheckForWinnerHorizontal_R7_C37_T4()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(7,3, 'X');
        BoardPosition pos2 = new BoardPosition(7,4, 'X');
        BoardPosition pos3 = new BoardPosition(7,5, 'X');
        BoardPosition pos4 = new BoardPosition(7,6, 'X');
        BoardPosition pos5 = new BoardPosition(7,7, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertTrue(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 5 - horizontal true
    @Test
    public void testCheckForWinnerHorizontal_R3_C26_T5()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(3,2, 'X');
        BoardPosition pos2 = new BoardPosition(3,3, 'X');
        BoardPosition pos3 = new BoardPosition(3,4, 'X');
        BoardPosition pos4 = new BoardPosition(3,5, 'X');
        BoardPosition pos5 = new BoardPosition(3,6, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertTrue(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 6 - horizontal false
    @Test
    public void testCheckForWinnerHorizontal_R0_F1()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(0,0, 'X');
        BoardPosition pos2 = new BoardPosition(0,2, 'X');
        BoardPosition pos3 = new BoardPosition(0,3, 'X');
        BoardPosition pos4 = new BoardPosition(0,4, 'X');
        BoardPosition pos5 = new BoardPosition(0,5, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertFalse(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 7 - horizontal false
    @Test
    public void testCheckForWinnerHorizontal_R7_F2()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(7,0, 'X');
        BoardPosition pos2 = new BoardPosition(7,1, 'X');
        BoardPosition pos3 = new BoardPosition(7,2, 'X');
        BoardPosition pos4 = new BoardPosition(7,6, 'X');
        BoardPosition pos5 = new BoardPosition(7,7, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertFalse(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 8 - horizontal false
    @Test
    public void testCheckForWinnerHorizontal_R4_CF3()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(4,0, 'X');
        BoardPosition pos2 = new BoardPosition(4,1, 'X');
        BoardPosition pos3 = new BoardPosition(4,2, 'X');
        BoardPosition pos4 = new BoardPosition(4,3, 'X');
        BoardPosition pos5 = new BoardPosition(4,4, 'O');
        BoardPosition pos6 = new BoardPosition(4,5, 'X');
        BoardPosition pos7 = new BoardPosition(4,6, 'X');
        BoardPosition pos8 = new BoardPosition(4,7, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);
        xoboard.placeMarker(pos6);
        xoboard.placeMarker(pos7);
        xoboard.placeMarker(pos8);

        // Test for win and assert True
        assertFalse(xoboard.checkForWinner(pos8));
    }

    // checkForWinner() test 9 - vertical true
    @Test
    public void testCheckForWinnerVertical_R04_C0_T1()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(0,0, 'X');
        BoardPosition pos2 = new BoardPosition(1,0, 'X');
        BoardPosition pos3 = new BoardPosition(2,0, 'X');
        BoardPosition pos4 = new BoardPosition(3,0, 'X');
        BoardPosition pos5 = new BoardPosition(4,0, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertTrue(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 10 - vertical true
    @Test
    public void testCheckForWinnerVertical_R37_C0_T2()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(3,0, 'X');
        BoardPosition pos2 = new BoardPosition(4,0, 'X');
        BoardPosition pos3 = new BoardPosition(5,0, 'X');
        BoardPosition pos4 = new BoardPosition(6,0, 'X');
        BoardPosition pos5 = new BoardPosition(7,0, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertTrue(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 11 - vertical true
    @Test
    public void testCheckForWinnerVertical_R04_C7_T3()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(0,7, 'X');
        BoardPosition pos2 = new BoardPosition(1,7, 'X');
        BoardPosition pos3 = new BoardPosition(2,7, 'X');
        BoardPosition pos4 = new BoardPosition(3,7, 'X');
        BoardPosition pos5 = new BoardPosition(4,7, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertTrue(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 12 - vertical true
    @Test
    public void testCheckForWinnerVertical_R37_C7_T4()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(3,7, 'X');
        BoardPosition pos2 = new BoardPosition(4,7, 'X');
        BoardPosition pos3 = new BoardPosition(5,7, 'X');
        BoardPosition pos4 = new BoardPosition(6,7, 'X');
        BoardPosition pos5 = new BoardPosition(7,7, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertTrue(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 13 - vertical true
    @Test
    public void testCheckForWinnerVertical_R26_C4_T5()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(2,4, 'X');
        BoardPosition pos2 = new BoardPosition(3,4, 'X');
        BoardPosition pos3 = new BoardPosition(4,4, 'X');
        BoardPosition pos4 = new BoardPosition(5,4, 'X');
        BoardPosition pos5 = new BoardPosition(6,4, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertTrue(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 14 - vertical false
    @Test
    public void testCheckForWinnerVertical_C0_F1()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(0,0, 'X');
        BoardPosition pos2 = new BoardPosition(1,0, 'X');
        BoardPosition pos3 = new BoardPosition(2,0, 'X');
        BoardPosition pos4 = new BoardPosition(4,0, 'X');
        BoardPosition pos5 = new BoardPosition(5,0, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertFalse(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 15 - vertical false
    @Test
    public void testCheckForWinnerVertical_C7_F2()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(2,7, 'X');
        BoardPosition pos2 = new BoardPosition(3,7, 'X');
        BoardPosition pos3 = new BoardPosition(5,7, 'X');
        BoardPosition pos4 = new BoardPosition(6,7, 'X');
        BoardPosition pos5 = new BoardPosition(7,7, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertFalse(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 16 - vertical false
    @Test
    public void testCheckForWinnerVertical_C4_F3()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(4,0, 'X');
        BoardPosition pos2 = new BoardPosition(4,1, 'X');
        BoardPosition pos3 = new BoardPosition(4,2, 'X');
        BoardPosition pos4 = new BoardPosition(4,3, 'X');
        BoardPosition pos5 = new BoardPosition(4,4, 'O');
        BoardPosition pos6 = new BoardPosition(4,5, 'X');
        BoardPosition pos7 = new BoardPosition(4,6, 'X');
        BoardPosition pos8 = new BoardPosition(4,7, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);
        xoboard.placeMarker(pos6);
        xoboard.placeMarker(pos7);
        xoboard.placeMarker(pos8);

        // Test for win and assert True
        assertFalse(xoboard.checkForWinner(pos8));
    }

    // checkForWinner() test 17 - major diagonal true
    @Test
    public void testCheckForWinnerMajorDiagonal_R04_C04_T1()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(0,0, 'X');
        BoardPosition pos2 = new BoardPosition(1,1, 'X');
        BoardPosition pos3 = new BoardPosition(2,2, 'X');
        BoardPosition pos4 = new BoardPosition(3,3, 'X');
        BoardPosition pos5 = new BoardPosition(4,4, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertTrue(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 18 - major diagonal true
    @Test
    public void testCheckForWinnerMajorDiagonal_R33_C77_T2()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(3,3, 'X');
        BoardPosition pos2 = new BoardPosition(4,4, 'X');
        BoardPosition pos3 = new BoardPosition(5,5, 'X');
        BoardPosition pos4 = new BoardPosition(6,6, 'X');
        BoardPosition pos5 = new BoardPosition(7,7, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertTrue(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 19 - major diagonal true
    @Test
    public void testCheckForWinnerMajorDiagonal_R37_C04_T3()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(3,0, 'X');
        BoardPosition pos2 = new BoardPosition(4,1, 'X');
        BoardPosition pos3 = new BoardPosition(5,2, 'X');
        BoardPosition pos4 = new BoardPosition(6,3, 'X');
        BoardPosition pos5 = new BoardPosition(7,4, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertTrue(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 20 - major diagonal true
    @Test
    public void testCheckForWinnerMajorDiagonal_R04_C37_T4()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(0,3, 'X');
        BoardPosition pos2 = new BoardPosition(1,4, 'X');
        BoardPosition pos3 = new BoardPosition(2,5, 'X');
        BoardPosition pos4 = new BoardPosition(3,6, 'X');
        BoardPosition pos5 = new BoardPosition(4,7, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertTrue(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 21 - major diagonal false
    @Test
    public void testCheckForWinnerMajorDiagonal_R07_C07_F1()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(0,0, 'X');
        BoardPosition pos2 = new BoardPosition(1,1, 'X');
        BoardPosition pos3 = new BoardPosition(2,2, 'X');
        BoardPosition pos4 = new BoardPosition(3,3, 'X');
        BoardPosition pos5 = new BoardPosition(4,4, 'O');
        BoardPosition pos6 = new BoardPosition(5,5, 'X');
        BoardPosition pos7 = new BoardPosition(6,6, 'X');
        BoardPosition pos8 = new BoardPosition(7,7, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);
        xoboard.placeMarker(pos6);
        xoboard.placeMarker(pos7);
        xoboard.placeMarker(pos8);

        // Test for win and assert True
        assertFalse(xoboard.checkForWinner(pos8));
    }

    // checkForWinner() test 22 - major diagonal false
    @Test
    public void testCheckForWinnerMajorDiagonal_R04_C37_F2()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(0,3, 'X');
        BoardPosition pos2 = new BoardPosition(1,4, 'X');
        BoardPosition pos3 = new BoardPosition(2,5, 'X');
        BoardPosition pos4 = new BoardPosition(3,6, 'X');
        BoardPosition pos5 = new BoardPosition(4,7, '0');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertFalse(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 23 - major diagonal false
    @Test
    public void testCheckForWinnerMajorDiagonal_R37_C04_F3()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(3,0, 'X');
        BoardPosition pos2 = new BoardPosition(4,1, 'X');
        BoardPosition pos3 = new BoardPosition(5,2, 'X');
        BoardPosition pos4 = new BoardPosition(6,3, 'X');
        BoardPosition pos5 = new BoardPosition(7,4, '0');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertFalse(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 24 - minor diagonal true
    @Test
    public void testCheckForWinnerMinorDiagonal_R73_C04_T1()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(7,0, 'X');
        BoardPosition pos2 = new BoardPosition(6,1, 'X');
        BoardPosition pos3 = new BoardPosition(5,2, 'X');
        BoardPosition pos4 = new BoardPosition(4,3, 'X');
        BoardPosition pos5 = new BoardPosition(3,4, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertTrue(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 25 - minor diagonal true
    @Test
    public void testCheckForWinnerMinorDiagonal_R40_C37_T2()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(4,3, 'X');
        BoardPosition pos2 = new BoardPosition(3,4, 'X');
        BoardPosition pos3 = new BoardPosition(2,5, 'X');
        BoardPosition pos4 = new BoardPosition(1,6, 'X');
        BoardPosition pos5 = new BoardPosition(0,7, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertTrue(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 26 - minor diagonal true
    @Test
    public void testCheckForWinnerMinorDiagonal_R40_C04_T3()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(4,0, 'X');
        BoardPosition pos2 = new BoardPosition(3,1, 'X');
        BoardPosition pos3 = new BoardPosition(2,2, 'X');
        BoardPosition pos4 = new BoardPosition(1,3, 'X');
        BoardPosition pos5 = new BoardPosition(0,4, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertTrue(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 27 - minor diagonal true
    @Test
    public void testCheckForWinnerMinorDiagonal_R73_C37_T4()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(7,3, 'X');
        BoardPosition pos2 = new BoardPosition(6,4, 'X');
        BoardPosition pos3 = new BoardPosition(5,5, 'X');
        BoardPosition pos4 = new BoardPosition(4,6, 'X');
        BoardPosition pos5 = new BoardPosition(3,7, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertTrue(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 28 - minor diagonal false
    @Test
    public void testCheckForWinnerMinorDiagonal_R70_C07_F1()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(7,0, 'X');
        BoardPosition pos2 = new BoardPosition(6,1, 'X');
        BoardPosition pos3 = new BoardPosition(5,2, 'X');
        BoardPosition pos4 = new BoardPosition(4,3, 'X');
        BoardPosition pos5 = new BoardPosition(3,4, 'O');
        BoardPosition pos6 = new BoardPosition(2,5, 'X');
        BoardPosition pos7 = new BoardPosition(1,6, 'X');
        BoardPosition pos8 = new BoardPosition(0,7, 'X');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);
        xoboard.placeMarker(pos6);
        xoboard.placeMarker(pos7);
        xoboard.placeMarker(pos8);

        // Test for win and assert True
        assertFalse(xoboard.checkForWinner(pos8));
    }

    // checkForWinner() test 29 - minor diagonal false
    @Test
    public void testCheckForWinnerMinorDiagonal_R40_C04_F2()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(4,0, 'X');
        BoardPosition pos2 = new BoardPosition(3,1, 'X');
        BoardPosition pos3 = new BoardPosition(2,2, 'X');
        BoardPosition pos4 = new BoardPosition(1,3, 'X');
        BoardPosition pos5 = new BoardPosition(0,4, '0');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertFalse(xoboard.checkForWinner(pos5));
    }

    // checkForWinner() test 30 - minor diagonal false
    @Test
    public void testCheckForWinnerMinorDiagonal_R73_C37_F3()
    {
        // Declare and initialize positions
        BoardPosition pos1 = new BoardPosition(7,3, 'X');
        BoardPosition pos2 = new BoardPosition(6,4, 'X');
        BoardPosition pos3 = new BoardPosition(5,5, 'X');
        BoardPosition pos4 = new BoardPosition(4,6, 'X');
        BoardPosition pos5 = new BoardPosition(3,7, '0');

        // Place markers in position
        xoboard.placeMarker(pos1);
        xoboard.placeMarker(pos2);
        xoboard.placeMarker(pos3);
        xoboard.placeMarker(pos4);
        xoboard.placeMarker(pos5);

        // Test for win and assert True
        assertFalse(xoboard.checkForWinner(pos5));
    }
}
