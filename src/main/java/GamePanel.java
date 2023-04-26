import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener {

        public static final int gridSize = 30; //set grid size n^2
        private final Timer timer;
        private Snake snake;
        private Food food;
        private Random random;


    public GamePanel(Snake snake) {
        this.snake = snake;
        setPreferredSize(new Dimension(gridSize * 20, gridSize * 20));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        random = new Random();
        food = new Food(random.nextInt(gridSize), random.nextInt(gridSize));

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tick(snake);
            }
        });
        timer.start();
    }

    public void changeDifficulty(int interval) {
        timer.setDelay(interval);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                snake.setDirection(Snake.UP);
                break;
            case KeyEvent.VK_DOWN:
                snake.setDirection(Snake.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                snake.setDirection(Snake.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                snake.setDirection(Snake.RIGHT);
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
        snake.move();

        // exit/restart game if snake collides
        if (snake.checkCollision()) {
            timer.stop();
            int choice = JOptionPane.showOptionDialog(
                    this,
                    "Game Over",
                    "Game Over",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new Object[]{"Restart", "Exit"},
                    null);
            if (choice == JOptionPane.YES_OPTION) {
                // restart the game
                snake.reset();
                food = new Food(random.nextInt(gridSize), random.nextInt(gridSize));
                timer.start();
            } else {
                System.exit(0);
            }
        }

        if (snake.getHead().equals(new Point(food.getX(), food.getY()))) {
            snake.grow(food.getX(), food.getY());
            food = new Food(random.nextInt(gridSize), random.nextInt(gridSize));
        }

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Snake snakeInstance = snake;

        //draw grid
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                g.drawRect(i * 20, j * 20, 20, 20);
            }
        }

        // draw snake
        for (Point segment : snake.getSegments()) {
            g.setColor(Color.WHITE);
            g.fillRect(segment.x * 20, segment.y * 20, 20, 20);
        }

        // draw food
        g.setColor(Color.RED);
        g.fillRect(food.getX() * 20, food.getY() * 20, 20, 20);

}

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }
}
