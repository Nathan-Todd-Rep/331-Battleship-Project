//Nathan Todd
//CSC 331
//Program: GameDefaults
//Holds every constant the Battleship game uses so no class has to
//hard-code a board size, cell code or ship length.

//A class made only of constants.
//It is final so it cannot be subclassed.

public final class GameDefaults {

    //The board is a square grid, BOARD_SIZE rows by BOARD_SIZE columns.

    public final static int BOARD_SIZE = 10;

    //The codes stored in each cell of a board grid.
    //OCEAN_CODE is an empty cell, SHIP_CODE is part of a ship that has not
    //been hit, HIT_CODE is a ship cell that was hit, MISS_CODE is a shot
    //that landed in open water.

    public final static String OCEAN_CODE = "0";
    public final static String SHIP_CODE = "*";
    public final static String HIT_CODE = "1";
    public final static String MISS_CODE = "-";

    //The length of each ship type, in cells.

    public final static int CARRIER_LENGTH = 5;
    public final static int BATTLESHIP_LENGTH = 4;
    public final static int CRUISER_LENGTH = 3;
    public final static int SUBMARINE_LENGTH = 3;
    public final static int DESTROYER_LENGTH = 2;

    //The name of each ship type, shown to the player.

    public final static String CARRIER_NAME = "Carrier";
    public final static String BATTLESHIP_NAME = "Battleship";
    public final static String CRUISER_NAME = "Cruiser";
    public final static String SUBMARINE_NAME = "Submarine";
    public final static String DESTROYER_NAME = "Destroyer";

    //How many ships each player has in their fleet.

    public final static int FLEET_SIZE = 5;

    //Private constructor: no GameDefaults objects are ever created,
    //the constants are used directly as GameDefaults.BOARD_SIZE and so on.

    private GameDefaults() {

        return;
    }
}
