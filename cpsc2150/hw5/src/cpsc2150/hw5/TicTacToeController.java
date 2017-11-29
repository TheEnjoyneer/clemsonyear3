package cpsc2150.hw5;

/**
 * The TicTacToe controller class will handle communication between our
 * TicTacToeView and our Model (IGameBoard and BoardPosition)
 *
 * This is where you will write code
 *
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and one of the IGameBoard implementations from Homework 4
 * You can choose which IGameBoard implementation to use
 *
 * GameBoardFast implementation is used
 *
 * If your code was correct you will not need to make any changes to your
 * IGameBoard implementation class besides the package name
 */
public class TicTacToeController {
    //our current game that is being played
    private IGameBoard curGame;
    //to track who's turn it is
    private char curPlayer;
    //The screen that provides our view
    private TicTacToeView screen;
    // no other class variables are needed or should be declared


    // add the code and contracts for the constructor of our tic tac toe game

    /**
     *
     * @param model is the object used for the game tracking itself
     * @param view is the object used for controlling what is displayed on the GUI
     * @requires model != null and view != null
     * @requires both model and view to be valid objects of IGameBoard and TicTacToeView type
     * @ensures curGame = model, meaning that the curGame variable is initialized to the object model
     * @ensures curPlayer is initialized to the first player character, 'X' in this case
     * @ensures screen = model, meaning that the screen variable is initialized to the object view
     * @ensures the first prompt is printed over the initial prompt from the TicTacToeView constructor
     */
    TicTacToeController(IGameBoard model, TicTacToeView view)
    {
        // Initialize the game to the IGameBoard model parameter
        this.curGame = model;

        // Initialize current player to X as the first move will be by X
        this.curPlayer = 'X';

        // Initialize the screen to the TicTacToeView view parameter
        this.screen = view;

        // Display initial message to the user
        String curMessage = "Welcome to Tic Tac Toe! Now Featuring GUI! Player " + curPlayer + " choose your location.";
        screen.setMessage(curMessage);
    }



    /* Add the code to respond to a user clicking on a button to try to claim a space
     * User will click on the button at row, col
     * User may click on a space that is not available, they do not get that space
     * We no longer have a main function that controls the flow of the game. Users will click buttons, and
     * Will handle the events here
     * Make sure to make any changes to the screen needed through publicly available functions
     * When a player wins, display a message. You do not need to reset the game or close the window
     * The players can close the window and restart the game themselves
     * remember your javadoc comments and contracts
     */

    /**
     *
     * @param row is the row of the button that was pressed
     * @param col is the column of the button that was pressed
     * @requires row is an int
     * @requires 0 <= row < NUM_ROWS of the chosen IGameBoard implementation
     * @requires col is an int
     * @requires 0 <= col < NUM_COLS of the chosen IGameBoard implementation
     * @ensures the correct message is written on the prompt at all times
     * @ensures the game alternates between players
     * @ensures button text displays marker of player that chose that space first
     * @ensures invalid button presses are checked and error message is printed
     * @ensures all possible win conditions are checked every time a new marker is placed
     */
    public void processButtonClick(int row, int col)
    {
        BoardPosition pos = new BoardPosition(row, col, curPlayer);
        String curMessage;

        if (curGame.checkSpace(pos))
        {
            // Place marker and change the screen
            curGame.placeMarker(pos);
            screen.setMarker(pos.getRow(), pos.getColumn(), pos.getPlayer());

            // Check for a winner
            if (curGame.checkForWinner(pos))
            {
                curMessage = "Player " + curPlayer + " wins!";
                screen.setMessage(curMessage);
            }
            else
            {
                // Alternate player marker
                if (curPlayer == 'X')
                    curPlayer = 'O';
                else
                    curPlayer = 'X';

                // Display the next player message prompt
                curMessage = "Player " + curPlayer + " choose your location.";
                screen.setMessage(curMessage);
            }
        }
        else
        {
            curMessage = "Invalid input, space is taken. Try again Player " + curPlayer + ".";
            screen.setMessage(curMessage);
        }
    }
}
