import java.awt.Point; // point is a class that represents a location in (x,y) coordinate space, specified in integer precision.
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private Point head;
    private int length;
    private int direction;
    private List<Point> segments;

    public Snake(){
        head = new Point(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2);
        segments = new ArrayList<Point>();
        segments.add(new Point(1, 1));
    }

    public Point getHead() {
        return head;
    }

    public void move(int dx, int dy) {
        for (int i = segments.size() - 1; i > 0; i--) { //move every segment to the position of the segment in front of it (except the head)
            Point currPos = segments.get(i);
            Point prevPos = segments.get(i - 1);
            currPos.setLocation(prevPos);
        }
        Point head = segments.get(0); //move the head
        head.translate(dx, dy);
    }

    public void grow() {
        Point tailPos = segments.get(segments.size() - 1);
        segments.add(new Point(tailPos));
    }

    public boolean checkCollision() {
        if (head.x < 0 || head.x >= GamePanel.WIDTH || head.y < 0 || head.y >= GamePanel.HEIGHT) {
            return true;
        }
        for (int i = 1; i < segments.size(); i++) {
            if (head.equals(segments.get(i))) {
                return true;
            }
        }
        return false;
    }


    public List<Point> getSegments() {
        return segments;
    }
}
