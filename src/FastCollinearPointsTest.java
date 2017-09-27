import static org.junit.Assert.*;

import org.junit.Test;

public class FastCollinearPointsTest {

    @Test(expected = NullPointerException.class)
    public void testConstructorNotNullInput1() {
        FastCollinearPoints bp = new FastCollinearPoints(null);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNotNullInput2() {
        Point[] pts = new Point[4];
        pts[0] = null;
        pts[1] = null;
        pts[2] = null;
        pts[3] = null;
        
        FastCollinearPoints bp = new FastCollinearPoints(pts);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIllegalArgument() {
        Point[] pts = new Point[4];
        pts[0] = new Point(1,2);
        pts[1] = new Point(3,4);
        pts[2] = new Point(1,2);
        pts[3] = new Point(1,2);
        
        FastCollinearPoints bp = new FastCollinearPoints(pts);
    }

    @Test
    public void testOneLinesegment1() {
        Point[] pts = new Point[4];
        pts[0] = new Point(1,2);
        pts[1] = new Point(3,4);
        pts[2] = new Point(5,6);
        pts[3] = new Point(7,8);
        
        FastCollinearPoints bp = new FastCollinearPoints(pts);
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
        
        FastCollinearPoints bp = new FastCollinearPoints(pts);
        assertEquals(1, bp.numberOfSegments());
        assertEquals(1, bp.segments().length);
    }

    @Test
    public void testOneLinesegment3() {
        Point[] pts = new Point[5];
        pts[0] = new Point(1,2);
        pts[1] = new Point(3,4);
        pts[2] = new Point(1,1);
        pts[3] = new Point(7,8);
        pts[4] = new Point(5,6);
        
        FastCollinearPoints bp = new FastCollinearPoints(pts);
        assertEquals(1, bp.numberOfSegments());
        assertEquals(1, bp.segments().length);
    }

    @Test
    public void testInput8() {
        Point[] pts = new Point[8];
        pts[0] = new Point(10000,0);
        pts[1] = new Point(0,10000);
        pts[2] = new Point(3000,7000);
        pts[3] = new Point(7000,3000);
        pts[4] = new Point(20000,21000);
        pts[5] = new Point(3000,4000);
        pts[6] = new Point(14000,15000);
        pts[7] = new Point(6000,7000);
        
        FastCollinearPoints bp = new FastCollinearPoints(pts);
        assertEquals(2, bp.numberOfSegments());
        assertEquals(2, bp.segments().length);
    }

}
