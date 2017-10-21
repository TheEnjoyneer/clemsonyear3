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

    

}
