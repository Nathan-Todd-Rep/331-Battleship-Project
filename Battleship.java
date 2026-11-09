import java.util.Scanner;
import java.io.File;


// for computer
import java.util.Random;

public class Battleship {
    public final static String SHIP_CODE = "*";
    public final static String OCEAN_CODE = "0";
    public final static String HIT_CODE = "1";

    public final static int GRID_SIZE = 10;

    private String[][] grid = new String[GRID_SIZE][GRID_SIZE];

    private int battleshipsLeft = 0;
    

// For testing purposes we will read the grids instead of creating
    private void readGrid(String dataFile) throws Exception {

        //initialize scanner object for data input
        Scanner input = new Scanner(new File(dataFile));

        // nextInt() to string conversion does not work
        // outer loop stores a row and removes commas
        for (int row = 0; row < GRID_SIZE; row++) {
            String line = input.nextLine();
            String[] nums = line.split(",");

            // stores elements in grid
            for (int column = 0; column < GRID_SIZE; column++)
                grid[row][column] = nums[column];
        }

        //close scanner
        input.close();
    }
    public void displayGrid() {
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                System.out.print(grid[r][c] + " ");
            }
            System.out.println();
        }

        return;
    }
    private static void takeGuess{
        //display grid being attacked
        boolean hitTarget = true;
        // get user coordinates for attack
        int xCord = new Scanner(System.in).nextInt();
        int xCord = new Scanner(System.in).nextInt();

        while (hitTarget == true){
            if (grid[xCord][yCord].equals(SHIP_CODE){
                grid[xCord][yCord] = HIT_CODE;
                battleshipsLeft -=1;
                hitTarget = true;
            
            else if (grid[xCord][yCord].equals(HIT_CODE){
                grid[xCord][yCord] = HIT_CODE;
                // reiterate and let player select new cordinate
            else{
              hitTarget = false;  
        }
        }
    private static void takeGuessComputer{
        //same as takeGuess but with random coordinates generated
    }
    public boolean playGame{
        
        }
