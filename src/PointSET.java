import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.TreeSet;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {
    private TreeSet<Point2D> set = new TreeSet<Point2D>();

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public int size() {
        return set.size();
    }

    public void insert(Point2D point) {
        if (point == null)
            throw new NullPointerException();
        set.add(point);
    }

    public boolean contains(Point2D point) {
        if (point == null)
            throw new NullPointerException();
        return set.contains(point);
    }

    public void draw() {
        for (Point2D p : set) {
            StdDraw.point(p.x(), p.y());
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new NullPointerException();
        TreeSet<Point2D> inRect = new TreeSet<Point2D>();
        for (Point2D p : set) {
            if (p.x() >= rect.xmin() && p.y() >= rect.ymin() && p.x() <= rect.xmax() && p.y() <= rect.ymax())
                inRect.add(p);
        }

        return inRect;
    }

    public Point2D nearest(Point2D point) {
        if (point == null)
            throw new NullPointerException();
        if (set.size() == 0)
            return null;
        Point2D nearestPoint;
        nearestPoint = set.first();
        double distance = point.distanceTo(set.first());

        for (Point2D p : set) {
            if (point.distanceTo(p) < distance) {
                distance = point.distanceTo(p);
                nearestPoint = p;
            }
        }
        return nearestPoint;

    }

    public static void main(String[] args) {
    }
}
