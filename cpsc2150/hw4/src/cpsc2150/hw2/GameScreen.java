package cpsc2150.hw2;

import java.util.Scanner;

public class GameScreen {

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int rowSize, colSize, winNum;
        Character impType;
        int marker, winCondition = 0, again;
        int x, y;
        BoardPosition nextPos = null;

        IGameBoard xoboard;

        System.out.println("\nWelcome to Customizable Tic-Tac-Toe!\n");
        System.out.println("How many rows should be on the board?");
        rowSize = Integer.parseInt(input.next());

        while (rowSize > 100 || rowSize < 3)
        {
            System.out.println("Can only have between 3 and 100 rows");
            System.out.println("How many rows should be on the board?");
            rowSize = Integer.parseInt(input.next());
        }

        System.out.println("How many columns should be on the board?");
        colSize = Integer.parseInt(input.next());

        while (colSize > 100 || colSize < 3)
        {
            System.out.println("Can only have between 3 and 100 columns");
            System.out.println("How many columns should be on the board?");
            colSize = Integer.parseInt(input.next());
        }

        System.out.println("How many in a row to win?");
        winNum = Integer.parseInt(input.next());

        while (winNum > rowSize || winNum > colSize || winNum < 2)
        {
            System.out.println("You can't have that because thats more than the number of rows or columns");
            System.out.println("Or you cannot have a number less than 2 to win")
            System.out.println("How many in a row to win?");
            winNum = Integer.parseInt(input.next());
        }

        System.out.println("Enter F for a (F)ast implementation or M for a (M)emory efficient implementation");
        impType = input.next().charAt(0);

        while (impType != 'f' && impType != 'F' && impType != 'm' && impType != 'M')
        {
            System.out.println("Enter F for a (F)ast implementation or M for a (M)emory efficient implementation");
            impType = input.next().charAt(0);
        }

        if (impType == 'f' || impType == 'F')
            xoboard = new GameBoardFast(rowSize, colSize, winNum);
        else
            xoboard = new GameBoardMem(rowSize, colSize, winNum);

        again = 0;
        marker = 0;

        System.out.println(xoboard.toString());

        // While loop that loops for input and continuous play
        while (winCondition == 0)
        {
            // Checks if the player wants to play again.
            if (again == 1)
            {
                System.out.println("\nWelcome to Customizable Tic-Tac-Toe!\n");
                System.out.println("How many rows should be on the board?");
                rowSize = Integer.parseInt(input.next());

                while (rowSize > 100 || rowSize < 3)
                {
                    System.out.println("Can only have between 3 and 100 rows");
                    System.out.println("How many rows should be on the board?");
                    rowSize = Integer.parseInt(input.next());
                }

                System.out.println("How many columns should be on the board?");
                colSize = Integer.parseInt(input.next());

                while (colSize > 100 || colSize < 3)
                {
                    System.out.println("Can only have between 3 and 100 columns");
                    System.out.println("How many columns should be on the board?");
                    colSize = Integer.parseInt(input.next());
                }

                System.out.println("How many in a row to win?");
                winNum = Integer.parseInt(input.next());

                while (winNum > rowSize || winNum > colSize || winNum < 2)
                {
                    System.out.println("You can't have that because thats more than the number of rows or columns");
                    System.out.println("Or you cannot have a number less than 2 to win")
                    System.out.println("How many in a row to win?");
                    winNum = Integer.parseInt(input.next());
                }

                System.out.println("Enter F for a (F)ast implementation or M for a (M)emory efficient implementation");
                impType = input.next().charAt(0);

                while (impType != 'f' && impType != 'F' && impType != 'm' && impType != 'M')
                {
                    System.out.println("Enter F for a (F)ast implementation or M for a (M)emory efficient implementation");
                    impType = input.next().charAt(0);
                }

                if (impType == 'f' || impType == 'F')
                    xoboard = new GameBoardFast(rowSize, colSize, winNum);
                else
                    xoboard = new GameBoardMem(rowSize, colSize, winNum);

                again = 0;
                marker = 0;

                System.out.println(xoboard.toString());
            }

            System.out.println("\nThis game begins with the player");
            System.out.println("who chooses to use the X marker");
            System.out.println("and alternates with the O marker.\n");

            // Runs input intake and checks for marker 'X'
            if (marker % 2 == 0)
            {
                rowPrompt('X');
                x = Integer.parseInt(input.next());

                while (x < 0 || x >= rowSize)
                {
                    System.out.println("\nPlease enter a valid row value");
                    rowPrompt('X');
                    x = Integer.parseInt(input.next());
                }

                colPrompt('X');
                y = Integer.parseInt(input.next());

                while (y < 0 || y >= colSize)
                {
                    System.out.println("\nPlease enter a valid column value");
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

                while (x < 0 || x >= rowSize)
                {
                    System.out.println("\nPlease enter a valid row value");
                    rowPrompt('O');
                    x = Integer.parseInt(input.next());
                }

                colPrompt('O');
                y = Integer.parseInt(input.next());

                while (y < 0 || y >= colSize)
                {
                    System.out.println("\nPlease enter a valid column value");
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
                System.out.println("Player " + nextPos.getPlayer() + " wins!");
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
        System.out.println("\nPlayer " + marker + " enter your ROW");
    }

    /**
     *
     * @param marker is used to print the correct marker
     * @requires marker != null
     * @ensures the column prompt is printed
     */
    private static void colPrompt(char marker)
    {
        System.out.println("\nPlayer " + marker + " enter your COLUMN");
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
        System.out.println("\nWould you like to play again? Y/N");

        char response = again.next().charAt(0);

        if (response == 'Y' || response == 'y')
            retVal = 0;
        else if (response == 'N' || response == 'n')
            retVal = 1;

        return retVal;
    }

}
