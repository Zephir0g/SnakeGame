import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {

    public GameFrame() {
        super("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainMenu menuPanel = new MainMenu();
        add(menuPanel);


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
