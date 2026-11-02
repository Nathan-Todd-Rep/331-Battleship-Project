import java.util.Scanner;
import java.io.File;

public class battleship {
    public final static int SHIP_CODE = "*";
    public final static int OCEAN_CODE = "0";
    public final static int HIT_CODE = "1";

    public final static int GRID_SIZE = 10;

    private String[][] grid = new String[GRID_SIZE][GRID_SIZE];

    private int battleshipLeft = 0;
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