import static org.junit.Assert.*;

import org.junit.Test;

public class BruteCollinearPointsTest {

    @Test(expected = NullPointerException.class)
    public void testConstructorNotNullInput1() {
        BruteCollinearPoints bp = new BruteCollinearPoints(null);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNotNullInput2() {
        Point[] pts = new Point[4];
        pts[0] = null;
        pts[1] = null;
        pts[2] = null;
        pts[3] = null;
        
        BruteCollinearPoints bp = new BruteCollinearPoints(pts);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIllegalArgument() {
        Point[] pts = new Point[4];
        pts[0] = new Point(1,2);
        pts[1] = new Point(3,4);
        pts[2] = new Point(1,2);
        pts[3] = new Point(1,2);
        
        BruteCollinearPoints bp = new BruteCollinearPoints(pts);
    }

    @Test
    public void testOneLinesegment1() {
        Point[] pts = new Point[4];
        pts[0] = new Point(1,2);
        pts[1] = new Point(3,4);
        pts[2] = new Point(5,6);
        pts[3] = new Point(7,8);
        
        BruteCollinearPoints bp = new BruteCollinearPoints(pts);
        assertEquals(1, bp.numberOfSegments());
        assertEquals(1, bp.segments().length);
    }

    @Test
    public void testOneLinesegment2() {
        Point[] pts = new Point[5];
        pts[0] = new Point(1,2);
        pts[1] = new Point(3,4);
        pts[2] = new Point(5,6);
        pts[3] = new Point(7,8);
        pts[4] = new Point(1,1);
        
        BruteCollinearPoints bp = new BruteCollinearPoints(pts);
        assertEquals(1, bp.numberOfSegments());
        assertEquals(1, bp.segments().length);
    }

}
