package cpsc2150.hw2;

import java.util.Scanner;

public class GameScreen {

    public static void main(String[] args)
    {
        System.out.println("\nWelcome to 8x8 Tic-Tac-Toe!");
        System.out.println("\nThis game begins with the player");
        System.out.println("\nwho chooses to use the X marker");
        System.out.println("\nand alternates with the O marker.\n");

        Scanner input = new Scanner(System.in);
        int marker = 0, winCondition = 0, again = 0;
        int x, y;
        BoardPosition nextPos;
        GameBoard xoboard = new GameBoard();


        while (winCondition == 0)
        {
            if (again == 1)
            {
                xoboard = new GameBoard();
                again = 0;
                marker = 0;
            }

            if (marker % 2 == 0)
            {
                rowPrompt('X');
                x = Integer.parseInt(input.next());

                while (x < 0 || x > 7)
                {
                    rowPrompt('X');
                    x = Integer.parseInt(input.next());
                }

                colPrompt('X');
                y = Integer.parseInt(input.next());

                while (y < 0 || y > 7)
                {
                    colPrompt('X');
                    y = Integer.parseInt(input.next());
                }

                nextPos = new BoardPosition(x, y, 'X');

                if (xoboard.checkSpace(nextPos))
                {
                    xoboard.placeMarker(nextPos);
                    marker++;
                }
                else
                {
                    System.out.println("\nThis space is already filled. Try again.");
                }


            }
            else if (marker % 2 == 1)
            {
                rowPrompt('O');
                x = Integer.parseInt(input.next());

                while (x < 0 || x > 7)
                {
                    rowPrompt('O');
                    x = Integer.parseInt(input.next());
                }

                colPrompt('O');
                y = Integer.parseInt(input.next());

                while (y < 0 || y > 7)
                {
                    colPrompt('O');
                    y = Integer.parseInt(input.next());
                }

                nextPos = new BoardPosition(x, y, 'O');

                if (xoboard.checkSpace(nextPos))
                {
                    xoboard.placeMarker(nextPos);
                    marker++;
                }
                else
                {
                    System.out.println("\nThis space is already filled. Try again.");
                }
            }

            // check for win condition and figure out how to restart/end the game
        }
    }

    private static void rowPrompt(char marker)
    {
        System.out.println("\nPlayer using marker ");
        System.out.println(marker);
        System.out.println(" enter desired row: ");
    }

    private static void colPrompt(char marker)
    {
        System.out.println("\nPlayer using marker ");
        System.out.println(marker);
        System.out.println(" enter desired column: ");
    }

    private static int playAgain()
    {
        int retVal = 0;

        Scanner again = new Scanner(System.in);
        System.out.println("\nWould you like to play again?");
        System.out.println("\nInsert a Y for yes or N for no");
        System.out.println("\n*Not case sensitive*");
        System.out.println("\nPlay again? ");

        char response = again.next().charAt(0);

        if (response == 'Y' || response == 'y')
            retVal = 0;
        else if (response == 'N' || response == 'n')
            retVal = 1;

        return retVal;
    }

}
