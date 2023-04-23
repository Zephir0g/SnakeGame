import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener {

    public static final int gridSize = 20; //set grid size n^2
    private final Timer timer;
    private Snake snake;
    private Food food;
    private Random random;

    //GamePanel gamePanel = new GamePanel(snake);

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

//    public static void showGameOver() {
//        Graphics g = getGraphics();
//        g.setColor(Color.WHITE);
//        g.setFont(new Font("Arial", Font.BOLD, 48));
//        String gameOverText = "Game Over";
//        int textWidth = g.getFontMetrics().stringWidth(gameOverText);
//        int x = (getWidth() - textWidth) / 2;
//        int y = getHeight() / 2;
//        g.drawString(gameOverText, x, y);
//    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    public void tick(Snake snake) {
        snake.move();

        if (snake.checkCollision()) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Game Over");
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



    //The getPreferredSize() method returns the preferred size of the component, which we set to 400x400 pixels.
    // The getMinimumSize() method returns the minimum size of the component, which is equal to the preferred size in this case.
//    @Override
//    public Dimension getPreferredSize() {
//        return new Dimension(gridSize * 20, gridSize * 20);
//    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }
}
