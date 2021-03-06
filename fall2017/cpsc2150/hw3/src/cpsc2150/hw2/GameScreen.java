package cpsc2150.hw2;

import java.util.Scanner;

public class GameScreen {

    public static void main(String[] args)
    {
        System.out.println("\nWelcome to 8x8 Tic-Tac-Toe!");
        System.out.println("First player to 5 in a row wins!");
        System.out.println("\nThis game begins with the player");
        System.out.println("who chooses to use the X marker");
        System.out.println("and alternates with the O marker.\n");

        Scanner input = new Scanner(System.in);
        int marker = 0, winCondition = 0, again = 0;
        int x, y;
        BoardPosition nextPos = new BoardPosition(0, 0, ' ');
        GameBoard xoboard = new GameBoard();

        // While loop that loops for input and continuous play
        while (winCondition == 0)
        {
            // Checks if the player wants to play again.
            if (again == 1)
            {
                xoboard = new GameBoard();
                again = 0;
                marker = 0;
            }

            // Runs input intake and checks for marker 'X'
            if (marker % 2 == 0)
            {
                rowPrompt('X');
                x = Integer.parseInt(input.next());

                while (x < 0 || x > 7)
                {
                    System.out.println("\nPlease enter a valid row value between 0 and 7");
                    rowPrompt('X');
                    x = Integer.parseInt(input.next());
                }

                colPrompt('X');
                y = Integer.parseInt(input.next());

                while (y < 0 || y > 7)
                {
                    System.out.println("\nPlease enter a valid column value between 0 and 7");
                    colPrompt('X');
                    y = Integer.parseInt(input.next());
                }

                nextPos = new BoardPosition(x, y, 'X');

                if (xoboard.checkSpace(nextPos))
                {
                    xoboard.placeMarker(nextPos);
                    System.out.println(xoboard.toString());
                    marker++;
                }
                else
                {
                    System.out.println("\nThis space is already filled. Try again.");
                    System.out.println(xoboard.toString());
                }


            }
            // Runs input intake and checks for marker 'O'
            else if (marker % 2 == 1)
            {
                rowPrompt('O');
                x = Integer.parseInt(input.next());

                while (x < 0 || x > 7)
                {
                    System.out.println("\nPlease enter a valid row value between 0 and 7");
                    rowPrompt('O');
                    x = Integer.parseInt(input.next());
                }

                colPrompt('O');
                y = Integer.parseInt(input.next());

                while (y < 0 || y > 7)
                {
                    System.out.println("\nPlease enter a valid column value between 0 and 7");
                    colPrompt('O');
                    y = Integer.parseInt(input.next());
                }

                nextPos = new BoardPosition(x, y, 'O');

                if (xoboard.checkSpace(nextPos))
                {
                    xoboard.placeMarker(nextPos);
                    System.out.println(xoboard.toString());
                    marker++;
                }
                else
                {
                    System.out.println("\nThis space is already filled. Try again.");
                    System.out.println(xoboard.toString());
                }
            }

            // Checks for win condition
            if (xoboard.checkForWinner(nextPos))
            {
                winCondition = 1;
                System.out.println("Player using marker " + nextPos.getPlayer() + " wins!");
            }

            // Checks if the win condition integer has changed and checks if the
            // user wants to play again.
            if (winCondition == 1)
            {
                winCondition = playAgain();
                again = 1;
            }
        }
    }

    /**
     *
     * @param marker is used to print the correct marker
     * @requires marker != null
     * @ensures the row prompt is printed
     */
    private static void rowPrompt(char marker)
    {
        System.out.println("\nPlayer using marker " + marker + ",");
        System.out.println("Enter desired row: ");
    }

    /**
     *
     * @param marker is used to print the correct marker
     * @requires marker != null
     * @ensures the column prompt is printed
     */
    private static void colPrompt(char marker)
    {
        System.out.println("\nPlayer using marker " + marker + ",");
        System.out.println("Enter desired column: ");
    }

    /**
     *
     * @return retVal = 1 if the player does not want to play again
     *         and retVal = 0 if the player does want to play again
     * @ensures retVal is returned as an int
     */
    private static int playAgain()
    {
        int retVal = 0;

        Scanner again = new Scanner(System.in);
        System.out.println("\nWould you like to play again?");
        System.out.println("Insert a Y for yes or N for no");
        System.out.println("*Not case sensitive*");
        System.out.println("Play again? ");

        char response = again.next().charAt(0);

        if (response == 'Y' || response == 'y')
            retVal = 0;
        else if (response == 'N' || response == 'n')
            retVal = 1;

        return retVal;
    }

}
