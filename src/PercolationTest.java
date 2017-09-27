import static org.junit.Assert.*;

import org.junit.Test;

public class PercolationTest {

    @Test(expected=IllegalArgumentException.class)
    public void testConstructorExceptionWhenNIsZeorOrNegative() {
        Percolation p1 = new Percolation(0);
        Percolation p2 = new Percolation(-1);
    }

    @Test
    public void testConstructor() {
        Percolation p = new Percolation(3);
        assertEquals(0, p.numberOfOpenSites());
        assertFalse(p.isOpen(1,1));
    }

    @Test
    public void testOpen() {
        Percolation p = new Percolation(3);
        assertFalse(p.isOpen(1,1));
        p.open(1, 1);
        assertTrue(p.isOpen(1,1));
        assertEquals(1, p.numberOfOpenSites());
    }

    @Test
    public void testOpenSameSiteMoreThanOnce() {
        Percolation p = new Percolation(3);
        assertFalse(p.isOpen(1,1));
        p.open(1, 1);
        assertTrue(p.isOpen(1,1));
        assertEquals(1, p.numberOfOpenSites());
        p.open(1, 1);
        assertEquals(1, p.numberOfOpenSites());
        p.open(1, 1);
        assertEquals(1, p.numberOfOpenSites());
    }

    @Test
    public void testIsFull() {
        Percolation p = new Percolation(3);
        p.open(2, 1);
        assertFalse(p.isFull(2,1));
        assertEquals(1, p.numberOfOpenSites());
        p.open(2, 2);
        assertFalse(p.isFull(2,1));
        assertEquals(2, p.numberOfOpenSites());
        p.open(1, 2);
        assertTrue(p.isFull(2,1));
        assertEquals(3, p.numberOfOpenSites());
    }

    @Test
    public void testPercolates() {
        Percolation p = new Percolation(3);
        assertFalse(p.percolates());
        p.open(2, 1);
        p.open(2, 2);
        p.open(1, 2);
        p.open(3, 3);
        assertFalse(p.percolates());
        p.open(3, 2);
        assertTrue(p.percolates());
    }

    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

}
