//Nathan Todd and Lucas
//CSC 331
//Program: Player
//One Battleship player. Holds the player's own board, loaded from a text
//file, and remembers every cell this player has fired at.

import java.util.Scanner;
import java.io.File;

//The parts every player shares. HumanPlayer and ComputerPlayer extend this
//class and override chooseTarget to pick their shots in their own way.

public class Player {

    //This player's own board. First index is the row, second is the column.
    //Each cell holds one of the cell codes from GameDefaults.

    private String[][] grid = new String[GameDefaults.BOARD_SIZE][GameDefaults.BOARD_SIZE];

    //True for every cell this player has already fired at on the opponent's
    //board, so the same cell is never chosen twice.

    private boolean[][] shotsFired = new boolean[GameDefaults.BOARD_SIZE][GameDefaults.BOARD_SIZE];

    //How many ship cells on this board have not been hit yet.
    //The player has lost when it reaches 0.

    private int shipCellsLeft = 0;

    //The name used in game messages, for example "You" or "Computer".

    private String name;

    //Constructor: receives the player's name and the data file that holds
    //the player's ships, then loads the board from that file.

    public Player(String name, String fileName) throws Exception {
        this.name = name;

        //Load this player's ships from the data file.

        readGrid(fileName);

        return;
    }


    //Opens the data file, reads one line per row, stores each value in the
    //grid, counts the ship cells and then closes the file.
    //Throws an Exception if a row has the wrong number of values, holds a
    //code other than ocean or ship, or the board is not exactly one fleet.

    private void readGrid(String fileName) throws Exception {

        //Values on each line of the file are separated by commas.

        final String VALUE_DELIMITER = ",";

        //A full fleet covers this many cells, the lengths of all five ships added up.

        final int FLEET_CELLS = GameDefaults.CARRIER_LENGTH + GameDefaults.BATTLESHIP_LENGTH
                + GameDefaults.CRUISER_LENGTH + GameDefaults.SUBMARINE_LENGTH
                + GameDefaults.DESTROYER_LENGTH;

        //Open the data file for reading.

        Scanner dataFile = new Scanner(new File(fileName));

        //Loop once per row; each line of the file is one row of the grid.

        for (int row = 0; row < grid.length; row++) {

            //Split the line at the commas so each cell value is its own array element.

            String[] rowValues = dataFile.nextLine().split(VALUE_DELIMITER);

            //A row needs one value for every column, otherwise the file is bad.

            if (rowValues.length != grid[row].length) {
                dataFile.close();
                throw new Exception("Row " + row + " of " + fileName + " should have "
                        + grid[row].length + " values but has " + rowValues.length + ".");
            }

            //Store each value in its column and count the ship cells.

            for (int column = 0; column < grid[row].length; column++) {
                grid[row][column] = rowValues[column].trim();

                //A ship cell adds to the count. Anything that is not ship or
                //ocean is a mistake in the file.

                if (grid[row][column].equals(GameDefaults.SHIP_CODE)) {
                    shipCellsLeft++;
                }
                else if (!grid[row][column].equals(GameDefaults.OCEAN_CODE)) {
                    dataFile.close();
                    throw new Exception("Cell " + new Coordinate(row, column) + " of " + fileName
                            + " holds \"" + grid[row][column] + "\", which is not a ship or ocean code.");
                }
            }
        }

        //Close the data file now that all rows are read.

        dataFile.close();

        //The board must hold exactly one full fleet, no more and no fewer ship cells.

        if (shipCellsLeft != FLEET_CELLS) {
            throw new Exception(fileName + " has " + shipCellsLeft + " ship cells but a full fleet has "
                    + FLEET_CELLS + ".");
        }

        return;
    }


    //Returns the name used in game messages.

    public String getName() {

        return name;
    }


    //Returns how many ship cells on this board have not been hit yet.

    public int getShipCellsLeft() {

        return shipCellsLeft;
    }


    //Returns true once every ship cell on this board has been hit.

    public boolean hasLost() {
        boolean allShipsHit = shipCellsLeft == 0;

        return allShipsHit;
    }


    //Returns true if this player has already fired at target.

    public boolean hasFiredAt(Coordinate target) {

        return shotsFired[target.getRow()][target.getColumn()];
    }


    //Returns the code stored in one cell of this board, so the GUI can
    //pick the right color for it.

    public String getCellCode(Coordinate cell) {

        return grid[cell.getRow()][cell.getColumn()];
    }


    //Marks a shot from the opponent at target on this board.
    //Returns GameDefaults.HIT_CODE if it hit a ship, or GameDefaults.MISS_CODE
    //if it landed in open water.

    public String receiveShot(Coordinate target) {
        String shotResult = GameDefaults.MISS_CODE;

        //A ship cell becomes a hit, so there is one less ship cell left to sink.

        if (grid[target.getRow()][target.getColumn()].equals(GameDefaults.SHIP_CODE)) {
            shotResult = GameDefaults.HIT_CODE;
            shipCellsLeft--;
        }

        //Store the result in the cell so the board shows where shots landed.

        grid[target.getRow()][target.getColumn()] = shotResult;

        return shotResult;
    }


    //Picks the next cell to fire at. This version takes the first cell, row
    //by row, that has not been fired at yet. HumanPlayer and ComputerPlayer
    //override it, and dynamic binding runs their version in takeTurn.

    public Coordinate chooseTarget() {
        Coordinate target = null;

        //Check every cell, row by row, and stop once an unfired cell is found.

        for (int row = 0; row < shotsFired.length && target == null; row++) {
            for (int column = 0; column < shotsFired[row].length && target == null; column++) {

                //The first cell not fired at yet becomes the target.

                if (!shotsFired[row][column]) {
                    target = new Coordinate(row, column);
                }
            }
        }

        return target;
    }


    //Fires one shot at target on the opponent's board.
    //Returns a message saying where the shot went and whether it hit.

    public String fireAt(Player opponent, Coordinate target) {
        String shotMessage;

        //Remember the cell so it is never chosen again, then fire at it.

        shotsFired[target.getRow()][target.getColumn()] = true;
        String shotResult = opponent.receiveShot(target);

        //Build the message for the hit or the miss.

        if (shotResult.equals(GameDefaults.HIT_CODE)) {
            shotMessage = name + " fired at " + target + " and hit a ship!";
        }
        else {
            shotMessage = name + " fired at " + target + " and missed.";
        }

        return shotMessage;
    }


    //Takes this player's turn against opponent: picks one cell and fires at it.
    //Each turn is one shot, hit or miss. Returns the message from fireAt.

    public String takeTurn(Player opponent) {
        Coordinate target = chooseTarget();

        return fireAt(opponent, target);
    }


    //Prints this board to the console with the row and column numbers.
    //When showShips is false, ships that have not been hit print as ocean,
    //so the opponent's board can be shown without giving away their ships.
    //Ocean prints as GameDefaults.OCEAN_SYMBOL so it is easy to tell from a miss.

    public void displayGrid(boolean showShips) {

        //Print the column numbers across the top, lined up with the cells.

        System.out.print("  ");
        for (int column = 0; column < grid.length; column++) {
            System.out.print(column + " ");
        }
        System.out.println();

        //Print each row number, then every cell in that row on the same line.

        for (int row = 0; row < grid.length; row++) {
            System.out.print(row + " ");

            for (int column = 0; column < grid[row].length; column++) {
                String cellSymbol = grid[row][column];

                //Ocean prints as the ocean symbol, and so do ships that have
                //not been hit when showShips is false, so they stay hidden.

                if (cellSymbol.equals(GameDefaults.OCEAN_CODE)
                        || (!showShips && cellSymbol.equals(GameDefaults.SHIP_CODE))) {
                    cellSymbol = GameDefaults.OCEAN_SYMBOL;
                }
                System.out.print(cellSymbol + " ");
            }
            System.out.println();
        }

        return;
    }
}
