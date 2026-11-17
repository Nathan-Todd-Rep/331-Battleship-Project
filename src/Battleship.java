//Nathan Todd and Lucas
//CSC 331
//Program: Battleship
//Runs a game of Battleship on the console, you against the computer.
//For now each side's ships are loaded from a test board file.

//Holds the two players and runs the turns until one side is sunk.

public class Battleship {

    //The person playing the game.

    private HumanPlayer human;

    //The computer opponent.

    private ComputerPlayer computer;

    //Constructor: receives the board file for each side and loads both players.

    public Battleship(String humanFile, String computerFile) throws Exception {
        human = new HumanPlayer("You", humanFile);
        computer = new ComputerPlayer("Computer", computerFile);

        return;
    }


    //Plays turns, one shot each, you first and then the computer, until one
    //side has no ship cells left. Returns true if you won, false if the computer won.

    public boolean playGame() {

        //True once you have hit every computer ship cell.

        boolean humanWon = false;

        //True once either side has no ship cells left, which ends the loop.

        boolean gameOver = false;

        //Keep taking turns until one side has lost. The boards are shown
        //before every one of your shots, with the computer's ships hidden.

        while (!gameOver) {
            displayBoards(false);
            human.takeTurn(computer);

            //If your shot hit the computer's last ship cell you win, otherwise
            //the computer takes its shot and may hit your last ship cell.

            if (computer.hasLost()) {
                humanWon = true;
                gameOver = true;
            }
            else {
                computer.takeTurn(human);
                gameOver = human.hasLost();
            }
            System.out.println();
        }

        //Show both boards one last time with the computer's ships revealed,
        //so the player can see where the ships they missed were.

        displayBoards(true);

        return humanWon;
    }


    //Prints the computer's board, your own board with your ships showing,
    //what each symbol means and how many ship cells each side has left.
    //The computer's ships only show when revealComputerShips is true.

    private void displayBoards(boolean revealComputerShips) {

        //The computer's board, where your shots land.

        System.out.println("Computer's board (your shots):");
        computer.displayGrid(revealComputerShips);
        System.out.println();

        //Your board, where the computer's shots land.

        System.out.println("Your board:");
        human.displayGrid(true);
        System.out.println();

        //The key for the symbols and the ship cells each side still has.

        System.out.println("Key: " + GameDefaults.OCEAN_SYMBOL + " ocean, " + GameDefaults.SHIP_CODE
                + " ship, " + GameDefaults.HIT_CODE + " hit, " + GameDefaults.MISS_CODE + " miss");
        System.out.println("Ship cells left: you " + human.getShipCellsLeft() + ", computer "
                + computer.getShipCellsLeft());
        System.out.println();

        return;
    }


    //Loads both boards, plays one game and prints who won. If a board file
    //is missing or has a mistake, or the game stops partway, prints the
    //problem instead of crashing.

    public static void main(String[] args) {

        //The test board files for each side, read from the project folder.

        final String HUMAN_FILE = "testfile1.txt";
        final String COMPUTER_FILE = "testfile2.txt";

        //True once both boards have loaded, so an error message can say
        //whether the game failed to start or stopped partway through.

        boolean gameStarted = false;

        //Loading a board throws an Exception if its file is missing or wrong,
        //so catch it and tell the player what went wrong.

        try {
            Battleship game = new Battleship(HUMAN_FILE, COMPUTER_FILE);
            gameStarted = true;
            boolean humanWon = game.playGame();

            //Announce the winner.

            if (humanWon) {
                System.out.println("You sank every ship. You win!");
            }
            else {
                System.out.println("The computer sank every ship. You lose.");
            }
        }
        catch (Exception ex) {

            //Say whether the problem came before or during the game.

            if (gameStarted) {
                System.out.println("The game stopped: " + ex.getMessage());
            }
            else {
                System.out.println("The game could not start: " + ex.getMessage());
            }
        }

        return;
    }
}
