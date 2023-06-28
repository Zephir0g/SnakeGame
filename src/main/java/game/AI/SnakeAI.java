package game.AI;

import game.classes.Snake;
import game.logic.GamePanel;

import java.util.Random;

public class SnakeAI {

    private Snake snake;
    private GamePanel gamePanel;

    public SnakeAI(Snake snake, GamePanel gamePanel) {
        this.snake = snake;
        this.gamePanel = gamePanel;
    }

    public void makeMove() {
        Random random = new Random();
        int direction = random.nextInt(4); // Случайно выбираем число от 0 до 3 для определения направления

        // Проверяем, что выбранное направление доступно для движения змейки
        if (isValidDirection(direction)) {
            snake.setDirection(direction); // Устанавливаем выбранное направление
        }
    }

    private boolean isValidDirection(int direction) {
        // Проверяем, что выбранное направление не противоположно текущему направлению змейки
        int currentDirection = snake.getDirection();
        if ((currentDirection == Snake.UP && direction == Snake.DOWN)
                || (currentDirection == Snake.DOWN && direction == Snake.UP)
                || (currentDirection == Snake.LEFT && direction == Snake.RIGHT)
                || (currentDirection == Snake.RIGHT && direction == Snake.LEFT)) {
            return false;
        }

        // Проверяем, что выбранное направление не приведет к столкновению со стеной или телом змейки
        Snake tempSnake = new Snake(snake.getHead().x, snake.getHead().y);
        tempSnake.setDirection(direction);
        tempSnake.move();
        return !tempSnake.checkCollision();
    }
}
