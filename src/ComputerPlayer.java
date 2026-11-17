//Nathan Todd and Lucas
//CSC 331
//Program: ComputerPlayer
//The computer opponent. Fires at random cells, never the same cell twice.

import java.util.Random;

//A Player whose targets are picked at random. Overrides chooseTarget.

public class ComputerPlayer extends Player {

    //Makes the random row and column numbers for each shot.

    private Random random = new Random();

    //Constructor: receives the player's name and the data file that holds
    //the computer's ships, and passes both to the Player constructor.

    public ComputerPlayer(String name, String fileName) throws Exception {
        super(name, fileName);

        return;
    }


    //Returns a random cell on the board that has not been fired at yet.

    @Override
    public Coordinate chooseTarget() {
        Coordinate target = null;

        //Pick random cells until one is found that has not been fired at.

        while (target == null) {
            Coordinate randomCell = new Coordinate(random.nextInt(GameDefaults.BOARD_SIZE),
                    random.nextInt(GameDefaults.BOARD_SIZE));

            //Only keep the cell if this player has not fired at it before.

            if (!hasFiredAt(randomCell)) {
                target = randomCell;
            }
        }

        return target;
    }
}
