//Nathan Todd and Lucas
//CSC 331
//Program: HumanPlayer
//The person playing the game. Asks for each shot on the console and
//checks that the cell typed is on the board and has not been fired at.

import java.util.Scanner;

//A Player whose targets come from the keyboard. Overrides chooseTarget.

public class HumanPlayer extends Player {

    //Reads what the player types. One Scanner is used for every turn
    //instead of making a new one for each number.

    private Scanner keyboard = new Scanner(System.in);

    //Constructor: receives the player's name and the data file that holds
    //the player's ships, and passes both to the Player constructor.

    public HumanPlayer(String name, String fileName) throws Exception {
        super(name, fileName);

        return;
    }


    //Asks the player for a row and a column and returns that cell.
    //Keeps asking until the player types two whole numbers for a cell that
    //is on the board and has not been fired at yet.

    @Override
    public Coordinate chooseTarget() {

        //The highest row or column number on the board.

        final int LAST_INDEX = GameDefaults.BOARD_SIZE - 1;

        Coordinate target = null;

        //Keep asking until a usable cell has been typed.

        while (target == null) {
            System.out.print("Enter the row and column to fire at (0-" + LAST_INDEX + "): ");

            //nextInt throws an exception if the player types something that is
            //not a whole number, so catch it, skip the bad input and ask again.

            try {
                int row = keyboard.nextInt();
                int column = keyboard.nextInt();
                Coordinate typedCell = new Coordinate(row, column);

                //Throw away anything else typed on the same line.

                keyboard.nextLine();

                //Only accept a cell that is on the board and has not been fired at.

                if (!typedCell.isOnBoard()) {
                    System.out.println(typedCell + " is off the board. Try again.");
                }
                else if (hasFiredAt(typedCell)) {
                    System.out.println("You already fired at " + typedCell + ". Try again.");
                }
                else {
                    target = typedCell;
                }
            }
            catch (Exception ex) {
                System.out.println("Please type two whole numbers, for example: 3 7");
                keyboard.nextLine();
            }
        }

        return target;
    }
}
