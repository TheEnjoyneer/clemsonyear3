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

    // checkSpace() test 1
    @Test
    public void testCheckSpaceR0C0T()
    {

    }

    // checkSpace() test 2
    @Test
    public void testCheckSpaceR0C0F()
    {

    }

    // checkSpace() test 3
    @Test
    public void testCheckSpaceR7C7T()
    {

    }

    // checkSpace() test 4
    @Test
    public void testCheckSpaceR7C7F()
    {

    }

    // checkSpace() test 5
    @Test
    public void testCheckSpaceR8C8F()
    {

    }

    // placeMarker() test 1
    @Test
    public void testPlaceMarkerR0C0()
    {

    }

    // placeMarker() test 2
    @Test
    public void testPlaceMarkerR0C7()
    {

    }

    // placeMarker() test 3
    @Test
    public void testPlaceMarkerR7C0()
    {

    }

    // placeMarker() test 4
    @Test
    public void testPlaceMarkerR7C7()
    {

    }

    // placeMarker() test 5
    @Test
    public void testPlaceMarkerR3C3()
    {

    }

    // placeMarker() test 6
    @Test
    public void testPlaceMarkerR4C4()
    {

    }

    // checkForWinner() test 1 - horizontal true
    @Test
    public void testCheckForWinnerHorizontalT1()
    {

    }

    // checkForWinner() test 2 - horizontal true
    @Test
    public void testCheckForWinnerHorizontalT2()
    {

    }

    // checkForWinner() test 3 - horizontal false
    @Test
    public void testCheckForWinnerHorizontalF1()
    {

    }

    // checkForWinner() test 4 - horizontal false
    @Test
    public void testCheckForWinnerHorizontalF2()
    {

    }

    // checkForWinner() test 5 - vertical true
    @Test
    public void testCheckForWinnerVerticalT1()
    {

    }

    // checkForWinner() test 6 - vertical true
    @Test
    public void testCheckForWinnerVerticalT2()
    {

    }

    // checkForWinner() test 7 - vertical false
    @Test
    public void testCheckForWinnerVertical1()
    {

    }

    // checkForWinner() test 8 - vertical false
    @Test
    public void testCheckForWinnerVerticalF2()
    {

    }

    // checkForWinner() test 9 - major diagonal true
    @Test
    public void testCheckForWinnerMajorDiagonalT1()
    {

    }

    // checkForWinner() test 10 - major diagonal true
    @Test
    public void testCheckForWinnerMajorDiagonalT2()
    {

    }

    // checkForWinner() test 11 - major diagonal false
    @Test
    public void testCheckForWinnerMajorDiagonalF1()
    {

    }

    // checkForWinner() test 12 - major diagonal false
    @Test
    public void testCheckForWinnerMajorDiagonalF2()
    {

    }

    // checkForWinner() test 13 - minor diagonal true
    @Test
    public void testCheckForWinnerMinorDiagonalT1()
    {

    }

    // checkForWinner() test 14 - minor diagonal true
    @Test
    public void testCheckForWinnerMinorDiagonalT2()
    {

    }

    // checkForWinner() test 15 - minor diagonal false
    @Test
    public void testCheckForWinnerMinorDiagonalF1()
    {

    }

    // checkForWinner() test 16 - minor diagonal false
    public void testCheckForWinnerMinorDiagonalF2()
    {

    }


}
