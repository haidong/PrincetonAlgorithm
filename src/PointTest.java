import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {

    @Test
    public void testSlopeToTypicalCase() {
        Point p1 = new Point(1,2);
        Point p2 = new Point(3,4);
        
        assertEquals(1.0, p1.slopeTo(p2), 0.0001);
    }

    @Test
    public void testSlopeToSamePoint() {
        Point p1 = new Point(1,2);
        
        assertEquals(Double.NEGATIVE_INFINITY, p1.slopeTo(p1), 0.0001);
    }

    @Test
    public void testSlopeToSamePointDifferentVariable() {
        Point p1 = new Point(1,2);
        Point p2 = new Point(1,2);
        
        assertEquals(Double.NEGATIVE_INFINITY, p1.slopeTo(p2), 0.0001);
    }

    @Test
    public void testSlopeToSamePointHorizontal() {
        Point p1 = new Point(1,2);
        Point p2 = new Point(2,2);
        
        assertEquals(+0.0, p1.slopeTo(p2), 0.0001);
    }

    @Test
    public void testSlopeToSamePointVertical() {
        Point p1 = new Point(2,1);
        Point p2 = new Point(2,2);
        
        assertEquals(Double.POSITIVE_INFINITY, p1.slopeTo(p2), 0.0001);
    }

    @Test
    public void testCompareToBasic() {
        Point p1 = new Point(1,2);
        Point p2 = new Point(3,4);
        
        assertTrue(p1.compareTo(p2) < 0);
    }

    @Test
    public void testCompareToEqualY() {
        Point p1 = new Point(5,6);
        Point p2 = new Point(7,6);

        assertTrue(p1.compareTo(p2) < 0);
    }

    @Test
    public void testCompareToEqualPoints() {
        Point p1 = new Point(7,8);
        Point p2 = new Point(7,8);

        assertTrue(p1.compareTo(p2) == 0);
    }

}
