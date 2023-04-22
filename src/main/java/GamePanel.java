import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {

    private final int gridSize = 20; //set grid size n^2
    private final Timer timer;
    private final Snake snake;

    public GamePanel() {
        setPreferredSize(new Dimension(gridSize * 20, gridSize * 20));
        setBackground(Color.BLACK);
        snake = new Snake();
        setFocusable(true);
        addKeyListener(this);


        timer = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tick(snake);
            }
        });
        timer.start();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                snake.move(0, -1);
                break;
            case KeyEvent.VK_DOWN:
                snake.move(0, 1);
                break;
            case KeyEvent.VK_LEFT:
                snake.move(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                snake.move(1, 0);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    public void tick(Snake snake) {
        snake.move(0,0);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Snake snakeInstance = snake;


        for (Point segment : snakeInstance.getSegments()) {
            g.setColor(Color.WHITE);
            g.fillRect(segment.x * 20, segment.y * 20, 20, 20);
        }
    }

    //The getPreferredSize() method returns the preferred size of the component, which we set to 400x400 pixels.
    // The getMinimumSize() method returns the minimum size of the component, which is equal to the preferred size in this case.
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(gridSize * 20, gridSize * 20);
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }
}
