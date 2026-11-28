//Nathan Todd, Lucas, Alexander Aguilar
//CSC 331
//Program: Battleship
//Runs a game of Battleship on the console, you against the computer now with SWING supported GUI


//Holds the two players and runs the turns until one side is sunk.
import javax.swing.*;
import java.awt.*;

// creates and manages graphical interface VIA Java Swing
public class BattleshipGUI {

    // holds the clickable buttons that makeup all possible ship
    // coordinates for the player
    private JButton[][] humanButtons =
            new JButton[GameDefaults.BOARD_SIZE][GameDefaults.BOARD_SIZE];

    // holds the clickable buttons that makeup all possible ship
    // coordinates for the computer
    private JButton[][] computerButtons =
            new JButton[GameDefaults.BOARD_SIZE][GameDefaults.BOARD_SIZE];

    // displays message describing who's turn it is and
    // winner status
    private JLabel statusLabel;

    // connect the GUI to battleship logic
    private Battleship game;

    // initializes the game, creates both player boards
    // and displays GUI
    public BattleshipGUI() throws Exception {

        // Create the game using the two board files.
        game = new Battleship("testfile1.txt", "testfile2.txt");

        // creates main game window which display GUI
        JFrame frame = new JFrame("Battleship");

        // arranges game window components according to
        // specified dimensions
        frame.setLayout(new BorderLayout(10, 10));

        // creates message to be displayed at top of game
        // window announcing the player's turn
        statusLabel = new JLabel("Your turn! Choose a square on the computer's board.",
                SwingConstants.CENTER);

        // displays turn status at top of game window
        frame.add(statusLabel, BorderLayout.NORTH);

        // Create the two boards.
        JPanel boardsPanel = new JPanel(new GridLayout(1, 2, 15, 10));

        // creates two separate panels to hold the player
        // grid and computer grid
        JPanel humanPanel = new JPanel(new BorderLayout());
        JPanel computerPanel = new JPanel(new BorderLayout());

        // adds title to top of player panel
        humanPanel.add(new JLabel("Your Board", SwingConstants.CENTER),
                BorderLayout.NORTH);

        // adds title to top of computer panel
        computerPanel.add(new JLabel("Computer's Board", SwingConstants.CENTER),
                BorderLayout.NORTH);

        // creates 10 x 10 grid area for both players
        JPanel humanBoard = new JPanel(new GridLayout(10, 10, 3, 3));
        JPanel computerBoard = new JPanel(new GridLayout(10, 10, 3, 3));

        // Create the 100 squares for each board.
        for (int row = 0; row < GameDefaults.BOARD_SIZE; row++) {

            for (int column = 0; column < GameDefaults.BOARD_SIZE; column++) {

                // stores row and column for button click actions
                final int r = row;
                final int c = column;

                // Your board: shows your ships.
                JButton humanSquare = new JButton();

                // displays button's designated background colors
                humanSquare.setOpaque(true);
                humanSquare.setContentAreaFilled(true);

                // prevents player from clicking on own board
                humanSquare.setEnabled(false);

                // differentiates between buttons containing a
                // ship segment and empty spaces for human player's
                // board
                if (game.humanHasShipAt(row, column)) {
                    humanSquare.setText("S");
                    humanSquare.setBackground(Color.LIGHT_GRAY);
                } else {
                    humanSquare.setBackground(Color.CYAN);
                }

                // stores button and adds to human player's board
                humanButtons[row][column] = humanSquare;
                humanBoard.add(humanSquare);

                // Computer's board: hides the computer's ships.
                JButton computerSquare = new JButton();

                // makes all buttons from computer's board display
                // cyan background until a fire action is taken
                computerSquare.setOpaque(true);
                computerSquare.setContentAreaFilled(true);
                computerSquare.setBackground(Color.CYAN);

                // calls human turn when player clicks a square
                computerSquare.addActionListener(e -> humanTurn(r, c));

                // adds each completed board to its corresponding panel
                computerButtons[row][column] = computerSquare;
                computerBoard.add(computerSquare);
            }
        }

        // adds each completed board to its respective panel
        humanPanel.add(humanBoard, BorderLayout.CENTER);
        computerPanel.add(computerBoard, BorderLayout.CENTER);

        // positions player and computer panels side by side
        boardsPanel.add(humanPanel);
        boardsPanel.add(computerPanel);

        // positions both grids at center of main game window
        frame.add(boardsPanel, BorderLayout.CENTER);

        // sets initial game window size and ends program when closed
        frame.setSize(950, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // centers gaming window when running program
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // handles human player's turn for firing at computer's board
    private void humanTurn(int row, int column) {

        // holds hit or miss statues upon firing at selected
        // location
        String result = game.fireAtComputer(row, column);

        // updates corresponding square indicating a hit or miss
        updateSquare(computerButtons[row][column], result);

        // prevents player from selecting the same square twice
        computerButtons[row][column].setEnabled(false);

        // check whether human player sunk all computer ships
        // and displays corresponding message on GUI for user
        if (game.computerHasLost()) {
            statusLabel.setText("You sank all the computer's ships! You win!");
            disableComputerBoard();
            return;
        }

        // updates displayed message indicating the computer's
        // turn
        statusLabel.setText("Computer's turn...");

        // starts computer's turn
        computerTurn();
    }

    // handles computer's turn by selecting a random tile to fire at
    private void computerTurn() {

        // has computer select a random grid coordinate to fire
        // at
        Coordinate target = game.chooseComputerTarget();

        // gets row and column coordinate of selected button
        int row = target.getRow();
        int column = target.getColumn();

        // fires at selected location on human player's board
        String result = game.fireComputerAtHuman(target);

        // updates human player's button color based on whether the
        // shot had hit or missed a ship
        updateSquare(humanButtons[row][column], result);

        // check whether the computer has sunk all of the human player's
        // ships and displays corresponding message on game window
        if (game.humanHasLost()) {
            statusLabel.setText("The computer sank all your ships. You lose!");
            disableComputerBoard();
            return;
        }

        // displays message indicating it is the human player's turn
        statusLabel.setText("Your turn! Choose another square.");
    }

    // updates the boards button's text and color based on shot results
    private void updateSquare(JButton square, String result) {

        // displays result of taken shot
        square.setText(result);

        // determines resulting color of button for a hit or miss event
        if (result.equals(GameDefaults.HIT_CODE)) {
            square.setBackground(Color.GREEN);
        } else {
            square.setBackground(Color.RED);
        }
    }

    // prevents players from taking further action once
    // the game has ended
    private void disableComputerBoard() {

        // parses through each square on the computer's board
        for (int row = 0; row < GameDefaults.BOARD_SIZE; row++) {
            for (int column = 0; column < GameDefaults.BOARD_SIZE; column++) {

                // prevents additions shots to be taken
                computerButtons[row][column].setEnabled(false);
            }
        }
    }

    // starts Swing GUI and handles errors during initialization
    public static void main(String[] args) {

        // runs GUI on Swing's event-dispatch thread
        SwingUtilities.invokeLater(() -> {
            try {

                // creates and displays battleship GUI
                new BattleshipGUI();
            } catch (Exception e) {

                // prints error message if GUI cannot start
                e.printStackTrace();
            }
        });
    }
}
