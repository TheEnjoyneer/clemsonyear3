package cpsc2150.hw5;

/**
 * This class exists to declare our model, view, and controller objects, connect them, and start the game
 *
 * The only change you need to make is to call the appropriate constructor for your IGameBoard model variable
 */
public final class TicTacToeGame {

    /**
     * This is the entry point for our tic tac toe game
     * @param args ignored in this program
     */
    public static void main(String[] args)
    {
        int row = 10;
        int col = 10;
        int win = 5;
        //what implementation are you using
        IGameBoard model = new
        TicTacToeView view = new TicTacToeView(row, col);
        TicTacToeController controller = new TicTacToeController(model, view);

        view.registerObserver(controller);
    }
}
