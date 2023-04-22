import javax.swing.*;


public class Main {

    public static void main(String[] args){

        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel gamePanel = new GamePanel(); //object of a gamePanel class
        frame.add(gamePanel);

        frame.pack(); // This method is used to set the size of the frame to the size of the panel.
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
