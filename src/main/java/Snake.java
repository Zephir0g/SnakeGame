import java.awt.Point; // point is a class that represents a location in (x,y) coordinate space, specified in integer precision.
import java.util.ArrayList;
import java.util.List;

public class Snake {

    private List<Point> segments;

    public Snake(){
        segments = new ArrayList<Point>();
        segments.add(new Point(1, 1));
    }

    public void move(int dx, int dy) {
        for (int i = segments.size() - 1; i > 0; i--) { //move every segment to the position of the segment in front of it (except the head)
            Point currPos = segments.get(i);
            Point prevPos = segments.get(i - 1);
            currPos.setLocation(prevPos);
        }
        Point headPos = segments.get(0); //move the head
        headPos.translate(dx, dy);
    }

    public void grow() {
        Point tailPos = segments.get(segments.size() - 1);
                segments.add(new Point(tailPos));
    }

    public List<Point> getSegments() {
        return segments;
    }
}
