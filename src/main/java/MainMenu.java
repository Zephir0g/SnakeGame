import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {

    private JButton startButton;
    private JButton difficultyButton;
    private JButton exitButton;

    public MainMenu() {
        setLayout(new GridLayout(3, 1));
        startButton = new JButton("Start Game");
        startButton.addActionListener(e -> {
            GamePanel gamePanel = new GamePanel(new Snake(GamePanel.gridSize / 2, GamePanel.gridSize / 2));
            JFrame frame = new JFrame("Snake Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(gamePanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            gamePanel.requestFocus();
            frame.setResizable(false);

        });
        difficultyButton = new JButton("Change Difficulty");
        difficultyButton.addActionListener(e -> {
            // Code to change difficulty
        });
        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        add(startButton);
        add(difficultyButton);
        add(exitButton);
    }
}
