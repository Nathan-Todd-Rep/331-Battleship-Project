//Nathan Todd
//CSC 331
//Program: Coordinate
//Stores one cell on a Battleship board as a row and a column. Used for
//ship placement and for every shot fired.

//One board cell. Row and column are zero-based, so (0, 0) is the top-left cell.

public class Coordinate {

    //The row of the cell, counted from the top starting at 0.

    private int row;

    //The column of the cell, counted from the left starting at 0.

    private int column;

    //Constructor: receives the row and column of the cell.

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;

        return;
    }


    //Returns the row of the cell.

    public int getRow() {

        return row;
    }


    //Returns the column of the cell.

    public int getColumn() {

        return column;
    }


    //Returns true if the cell is inside the board, false if the row or
    //column is off the edge.

    public boolean isOnBoard() {

        //Both the row and column must be from 0 up to one less than the board size.

        boolean onBoard = row >= 0 && row < GameDefaults.BOARD_SIZE
                && column >= 0 && column < GameDefaults.BOARD_SIZE;

        return onBoard;
    }


    //Returns true if otherCoordinate is the same cell as this one.
    //Overrides equals from Object, so it receives an Object and casts it
    //back to a Coordinate before comparing.

    @Override
    public boolean equals(Object otherCoordinate) {
        boolean sameCell = false;

        //Only another Coordinate can be the same cell, so anything else
        //(including null) stays false.

        if (otherCoordinate instanceof Coordinate) {

            //Cast so the row and column of the other object can be read.

            Coordinate other = (Coordinate) otherCoordinate;
            sameCell = row == other.row && column == other.column;
        }

        return sameCell;
    }


    //Returns the cell as text in the form (row, column), for example (3, 7).

    @Override
    public String toString() {
        String cellText = "(" + row + ", " + column + ")";

        return cellText;
    }
}
