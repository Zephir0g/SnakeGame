package game;

import game.menu.MainMenu;

import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        super("game.classes.Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainMenu menuPanel = new MainMenu();
        add(menuPanel);


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
