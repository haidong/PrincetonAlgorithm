import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    private ArrayList<LineSegment> segments = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new NullPointerException();

        checkDuplicateElements(points);
        Point[] tempPoints = points.clone();
        Arrays.sort(tempPoints);

        for (int i = 0; i < tempPoints.length - 3; i++) {
            Arrays.sort(tempPoints);

            Arrays.sort(tempPoints, tempPoints[i].slopeOrder());

            for (int p = 0, first = 1, last = 2; last < tempPoints.length; last++) {
                while (last < tempPoints.length &&
                       Double.compare(tempPoints[p].slopeTo(tempPoints[first]),
                                      tempPoints[p].slopeTo(tempPoints[last])) == 0) {
                    last++;
                }
                if (last - first >= 3 && tempPoints[p].compareTo(tempPoints[first]) < 0) {
                    segments.add(new LineSegment(tempPoints[p], tempPoints[last - 1]));
                }
                first = last;
            }
        }
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }

    private void checkDuplicateElements(Point[] p) {
        for (int i = 0; i < p.length; i++) {
            for (int j = i + 1; j < p.length; j++) {
                if (p[i].compareTo(p[j]) == 0)
                    throw new IllegalArgumentException("Same points cannot appear twice!");
            }
        }
    }

    public static void main(String[] args) {

        // read the N points from a file
        In in = new In(args[0]);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        // StdOut.println(collinear.segments().length);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
    }
}