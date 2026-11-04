//Nathan Todd
//CSC 331
//Program: CoordinateTester
//Runs quick checks on the Coordinate class and prints what each one
//returned, so the team can see it works before the board is built.

public class CoordinateTester {

    //Creates a few coordinates and prints the results of isOnBoard,
    //equals and toString for each.

    public static void main(String[] args) {

        //The last row and column on the board, one less than the board size.

        final int LAST_INDEX = GameDefaults.BOARD_SIZE - 1;

        //A cell in the middle, the bottom-right corner, a copy of the
        //middle cell and a cell just off the right edge.

        Coordinate middleCell = new Coordinate(LAST_INDEX / 2, LAST_INDEX / 2);
        Coordinate cornerCell = new Coordinate(LAST_INDEX, LAST_INDEX);
        Coordinate middleCopy = new Coordinate(LAST_INDEX / 2, LAST_INDEX / 2);
        Coordinate offBoardCell = new Coordinate(0, GameDefaults.BOARD_SIZE);

        //toString should print each cell as (row, column).

        System.out.println("Middle cell:    " + middleCell);
        System.out.println("Corner cell:    " + cornerCell);
        System.out.println("Off-board cell: " + offBoardCell);
        System.out.println();

        //isOnBoard should be true for the first two and false for the last.

        System.out.println("Middle on board?    " + middleCell.isOnBoard());
        System.out.println("Corner on board?    " + cornerCell.isOnBoard());
        System.out.println("Off-board on board? " + offBoardCell.isOnBoard());
        System.out.println();

        //equals should be true for the same cell and false for a different one.

        System.out.println("Middle equals copy?   " + middleCell.equals(middleCopy));
        System.out.println("Middle equals corner? " + middleCell.equals(cornerCell));

        return;
    }
}
