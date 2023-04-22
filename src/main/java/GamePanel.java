import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {

    private int gridSize = 20; //set grid size n^2
    private Timer timer;
    private Snake snake;
    public GamePanel(){
        setPreferredSize(new Dimension(gridSize * 20, gridSize * 20));
        setBackground(Color.BLACK);
        snake = new Snake();


        timer = new Timer(300, new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent e) {
            tick(snake);
        }
    });
        timer.start();
    }


    public void tick(Snake snake){
        snake.move(1,0);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Snake snakeInstance = snake;


        /* debug code
        // in matrices and graphs, the vertical axis (y-axis) is from top to bottom and the horizontal axis (x-axis) is from left to right.
        snake snake = new snake();
        snake.move(0, 1); //  d
        snake.grow();
        snake.move(1, 0); // Движение вправо
        snake.grow();
        snake.move(0, -1); // Движение вверх
        snake.grow();
        snake.move(-1, 0); // Движение влево
        snake.grow();


        List<Point> segments = snake.getSegments();
        for (Point p : segments) {
            System.out.println("head: (" + p.x + ", " + p.y + ")");
        }
        */

       for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                g.drawRect(i * 20, j * 20, 20, 20);  // i and j we will use as coordinates for the grid
            }
        }

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
