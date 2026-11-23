import javax.swing.*;
import java.awt.*;

public class swing_main extends JFrame {

    public static final int GRID_SIZE = 10;

    public static void main(String[] args) {
        String [][] grid = new String [GRID_SIZE][GRID_SIZE];
        

        JFrame battleshipFrame = new JFrame("Battleship Game");
        battleshipFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        battleshipFrame.setResizable(false);
        battleshipFrame.setSize(800, 600);

        ImageIcon icon = new ImageIcon("battleshipicon.png");
        battleshipFrame.setIconImage(icon.getImage());

        //MAC icon update
        if (Taskbar.isTaskbarSupported()) {
            Taskbar taskbar = Taskbar.getTaskbar();
            if (taskbar.isSupported(Taskbar.Feature.ICON_IMAGE)) {
                taskbar.setIconImage(icon.getImage()); // Updates the macOS Dock icon
            }
        }

        JPanel humanPanel = new JPanel(new GridLayout(10, 10, 2, 2));
        JPanel computerPanel = new JPanel(new GridLayout(10, 10, 2, 2));

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {

                JButton button = new JButton();
                if(grid[row][col].equals("0")){
                button.setBackground(Color.CYAN);}

                else if(grid[row][col].equals("*")){
                    button.setBackground(Color.GRAY);}
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

                humanPanel.add(button);
            }
        }

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {

                JButton button = new JButton();
                button.setBackground(Color.CYAN);
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                computerPanel.add(button);
            }
        }

        JLabel label = new JLabel("Battleship");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);

        JLabel label1 = new JLabel("Your Ships");
        label1.setHorizontalAlignment(JLabel.CENTER);

        JLabel label2 = new JLabel("Enemy Ships");
        label2.setHorizontalAlignment(JLabel.CENTER);

        // Containers hold labels and grids
        JPanel humanContainer = new JPanel(new BorderLayout());
        JPanel computerContainer = new JPanel(new BorderLayout());

        humanContainer.add(label1, BorderLayout.NORTH);
        humanContainer.add(humanPanel, BorderLayout.CENTER);

        computerContainer.add(label2, BorderLayout.NORTH);
        computerContainer.add(computerPanel, BorderLayout.CENTER);

        // holds both panels
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        mainPanel.add(humanContainer);
        mainPanel.add(computerContainer);



        battleshipFrame.add(label, BorderLayout.NORTH);
        battleshipFrame.add(mainPanel, BorderLayout.CENTER);
        battleshipFrame.setVisible(true);
    }
}
