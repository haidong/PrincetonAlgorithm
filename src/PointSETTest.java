import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSETTest {
    // We are dealing inside the square of 0, 0 and 1, 1
    private static final Point2D CENTER = new Point2D(0.5, 0.5);
    private static final Point2D LOWER_LEFT = new Point2D(0.2, 0.3);
    private static final Point2D LOWER_RIGHT = new Point2D(0.7, 0.2);
    private static final Point2D UPPER_LEFT = new Point2D(0.3, 0.6);
    private static final Point2D UPPER_RIGHT = new Point2D(0.6, 0.8);
    private static final Point2D ORIGIN = new Point2D(0.0, 0.0);
    private static final RectHV NO_POINTS_INSIDE = new RectHV(0.1, 0.1, 0.2, 0.2);
    private static final RectHV ONE_POINT_INSIDE = new RectHV(0.1, 0.1, 0.3, 0.4);
    private static final RectHV TWO_POINTS_INSIDE = new RectHV(0.1, 0.1, 0.8, 0.4);
    private static final RectHV FOUR_POINTS_INSIDE = new RectHV(0.1, 0.1, 0.8, 0.7);

    @Test
    public void shouldBeEmpty() {
        PointSET ps = new PointSET();

        assertTrue(ps.isEmpty());
        assertEquals(0, ps.size());
    }

    @Test
    public void shouldHaveOnePoint() {
        PointSET ps = new PointSET();

        assertTrue(ps.isEmpty());
        assertEquals(0, ps.size());

        ps.insert(CENTER);
        ps.insert(CENTER);

        assertFalse(ps.isEmpty());
        assertEquals(1, ps.size());
    }

    @Test
    public void shouldContainTwoPoints() {
        PointSET ps = new PointSET();

        ps.insert(CENTER);
        ps.insert(ORIGIN);

        assertFalse(ps.isEmpty());
        assertEquals(2, ps.size());

        assertTrue(ps.contains(CENTER));
        assertTrue(ps.contains(ORIGIN));
        assertFalse(ps.contains(LOWER_LEFT));
    }

    @Test
    public void shouldHaveNoPointInRect() {
        PointSET ps = new PointSET();

        ps.insert(CENTER);
        ps.insert(LOWER_LEFT);
        ps.insert(LOWER_RIGHT);
        ps.insert(UPPER_LEFT);
        ps.insert(UPPER_RIGHT);

        assertFalse(ps.range(NO_POINTS_INSIDE).iterator().hasNext());
    }

    @Test
    public void shouldHaveOnePointInRect() {
        PointSET ps = new PointSET();

        ps.insert(CENTER);
        ps.insert(LOWER_LEFT);
        ps.insert(LOWER_RIGHT);
        ps.insert(UPPER_LEFT);
        ps.insert(UPPER_RIGHT);

        assertTrue(ps.range(ONE_POINT_INSIDE).iterator().hasNext());
    }

    @Test
    public void shouldHaveTwoPointsInRect() {
        PointSET ps = new PointSET();

        ps.insert(CENTER);
        ps.insert(LOWER_LEFT);
        ps.insert(LOWER_RIGHT);
        ps.insert(UPPER_LEFT);
        ps.insert(UPPER_RIGHT);

        Iterator<Point2D> it = ps.range(TWO_POINTS_INSIDE).iterator();
        it.next();
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    public void shouldHaveFourPointsInRect() {
        PointSET ps = new PointSET();

        ps.insert(CENTER);
        ps.insert(LOWER_LEFT);
        ps.insert(LOWER_RIGHT);
        ps.insert(UPPER_LEFT);
        ps.insert(UPPER_RIGHT);

        Iterator<Point2D> it = ps.range(FOUR_POINTS_INSIDE).iterator();
        it.next();
        it.next();
        it.next();
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    public void emptySetShouldBeNullForNearest() {
        PointSET ps = new PointSET();

        assertNull(ps.nearest(CENTER));
    }

    @Test
    public void testNearest2() {
        PointSET ps = new PointSET();

        ps.insert(LOWER_LEFT);
        ps.insert(LOWER_RIGHT);
        ps.insert(UPPER_LEFT);
        ps.insert(UPPER_RIGHT);

        assertEquals(UPPER_LEFT, ps.nearest(CENTER));
    }

    @Test
    public void testNearest3() {
        PointSET ps = new PointSET();

        ps.insert(CENTER);
        ps.insert(LOWER_RIGHT);
        ps.insert(UPPER_LEFT);
        ps.insert(UPPER_RIGHT);

        assertEquals(UPPER_LEFT, ps.nearest(LOWER_LEFT));
    }

}
