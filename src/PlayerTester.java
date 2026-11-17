//Nathan Todd
//CSC 331
//Program: PlayerTester
//Loads the test board and has a computer player fire at it until every
//ship cell is hit, so the team can check the Player classes work.

public class PlayerTester {

    //Runs the test: one computer player fires at another's board until it
    //is sunk, then prints how many turns it took and the final board.

    public static void main(String[] args) throws Exception {

        //The board file both test players load their ships from.

        final String TEST_FILE = "testfile1.txt";

        ComputerPlayer shooter = new ComputerPlayer("Computer", TEST_FILE);
        Player target = new Player("Target", TEST_FILE);

        //Show the target's board with its ships before any shots.

        System.out.println("Target board at the start:");
        target.displayGrid(true);
        System.out.println();
        System.out.println("Ship cells to sink: " + target.getShipCellsLeft());
        System.out.println();

        //Count the turns it takes to hit every ship cell.

        int turns = 0;

        //Keep giving the shooter turns until the target has no ship cells left.

        while (!target.hasLost()) {
            shooter.takeTurn(target);
            turns++;
        }

        //Show the results: every ship cell should now be a hit.

        System.out.println();
        System.out.println("All ships sunk in " + turns + " turns.");
        System.out.println("Ship cells left: " + target.getShipCellsLeft());
        System.out.println("Target board at the end:");
        target.displayGrid(true);

        return;
    }
}
