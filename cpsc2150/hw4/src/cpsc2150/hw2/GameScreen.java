package cpsc2150.hw2;

import java.util.Scanner;

public class GameScreen {

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        Integer rowSize = 0, colSize = 0, winNum = 0;
        Character impType = ' ';
        int marker = 0, winCondition = 0, again = 1;
        int x, y;
        BoardPosition nextPos = new BoardPosition(0, 0, ' ');

        IGameBoard xoboard = new GameBoardFast(rowSize,colSize,winNum);

        // While loop that loops for input and continuous play
        while (winCondition == 0)
        {
            // Checks if the player wants to play again.
            if (again == 1)
            {
                newGamePrompt(rowSize, colSize, winNum);

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
                    System.out.println("\nPlease enter a valid row value between 0 and 7");
                    rowPrompt('X');
                    x = Integer.parseInt(input.next());
                }

                colPrompt('X');
                y = Integer.parseInt(input.next());

                while (y < 0 || y >= colSize)
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

                while (x < 0 || x >= rowSize)
                {
                    System.out.println("\nPlease enter a valid row value between 0 and 7");
                    rowPrompt('O');
                    x = Integer.parseInt(input.next());
                }

                colPrompt('O');
                y = Integer.parseInt(input.next());

                while (y < 0 || y >= colSize)
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
     * @param numRows is the object that will store the input for number of rows
     * @param numCols is the object that will store the input for number of columns
     * @param targetNum is the object that will store the input for the number to win
     * @requires numRows != null && numCols != null && targetNum != null
     * @ensures numRows, numCols, and targetNum get populated with correct values for game
     */
    private static void newGamePrompt(Integer numRows, Integer numCols, Integer targetNum)
    {
        Scanner promptIn = new Scanner(System.in);
        System.out.println("\nWelcome to Customizable Tic-Tac-Toe!\n");
        System.out.println("How many rows should be on the board?");
        numRows = Integer.parseInt(promptIn.next());

        while (numRows > 100)
        {
            System.out.println("Can only have 100 rows or less");
            System.out.println("How many rows should be on the board?");
            numRows = Integer.parseInt(promptIn.next());
        }

        System.out.println("How many columns should be on the board?");
        numCols = Integer.parseInt(promptIn.next());

        while (numCols > 100)
        {
            System.out.println("Can only have 100 columns or less");
            System.out.println("How many columns should be on the board?");
            numCols = Integer.parseInt(promptIn.next());
        }

        System.out.println("How many in a row to win?");
        targetNum = Integer.parseInt(promptIn.next());

        while (targetNum > numRows || targetNum > numCols)
        {
            System.out.println("You can't have that because thats more than the number of rows or columns");
            System.out.println("How many in a row to win?");
            targetNum = Integer.parseInt(promptIn.next());
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
