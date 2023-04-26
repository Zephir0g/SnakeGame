package game.classes;

import game.logic.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class Snake {
    public static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
    private int direction = UP;
    private int x, y;
    private ArrayList<Point> segments = new ArrayList<>();
    private boolean hasEaten = false;
    private boolean moving = true;

    public Snake(int x, int y) {
        this.x = x;
        this.y = y;
        segments = new ArrayList<>();
        segments.add(new Point(x, y));
        segments.add(new Point(x, y+1));
        segments.add(new Point(x, y+2));
        direction = UP;
    }

    public void move() {
        Point newHead = (Point) getHead().clone();

        switch (direction) {
            case UP:
                newHead.y--;
                break;
            case RIGHT:
                newHead.x++;
                break;
            case DOWN:
                newHead.y++;
                break;
            case LEFT:
                newHead.x--;
                break;
        }

        segments.add(0, newHead);

        if (!hasEaten) {
            segments.remove(segments.size() - 1);
        } else {
            hasEaten = false;
        }
    }

    public void setDirection(int direction) {
        if ((this.direction == UP && direction != DOWN)
                || (this.direction == RIGHT && direction != LEFT)
                || (this.direction == DOWN && direction != UP)
                || (this.direction == LEFT && direction != RIGHT)) {
            this.direction = direction;
        }
    }


    public Point getHead() {
        return segments.get(0);
    }

    public ArrayList<Point> getSegments() {
        return segments;
    }

    public void eat() {
        hasEaten = true;
    }

    public boolean contains(Point point) {
        for (Point segment : segments) {
            if (segment.equals(point)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCollision() {
        Point head = segments.get(0);

        // Check for collision with the walls
        if (head.x < 0 || head.x >= GamePanel.gridSize || head.y < 0 || head.y >= GamePanel.gridSize) {
            return true;
        }

        // Check for collision with the body
        for (int i = 1; i < segments.size(); i++) {
            if (head.equals(segments.get(i))) {
                return true;
            }
        }

        return false;
    }

    public void grow(int foodX, int foodY) {
        segments.add(0, new Point(foodX, foodY));
    }

    public void reset() {
        // Set the initial position and direction of the snake
        segments.clear();
        segments.add(new Point(x, y));
        segments.add(new Point(x, y+1));
        segments.add(new Point(x, y+2));
        direction = UP;
        hasEaten = false;
    }
}
