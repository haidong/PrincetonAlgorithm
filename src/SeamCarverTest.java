import static org.junit.Assert.*;

import org.junit.Test;
import edu.princeton.cs.algs4.Picture;

public class SeamCarverTest {
    private final static Picture P1X1 = new Picture(
            "C:/Users/Haidong Ji/Downloads/seamCarving-testing/seamCarving/1x1.png");
    private final static Picture P1X8 = new Picture(
            "C:/Users/Haidong Ji/Downloads/seamCarving-testing/seamCarving/1x8.png");
    private final static Picture P3X4 = new Picture(
            "C:/Users/Haidong Ji/Downloads/seamCarving-testing/seamCarving/3x4.png");
    private final static Picture P6X5 = new Picture(
            "C:/Users/Haidong Ji/Downloads/seamCarving-testing/seamCarving/6x5.png");
    private final static double EPSILON = 0.000001;

    @Test(expected = NullPointerException.class)
    public void testConstructorException1() {
        SeamCarver seamCarver = new SeamCarver(null);
    }

    @Test
    public void testPicture1() {
        SeamCarver seamCarver = new SeamCarver(P1X1);
        assertEquals(P1X1, seamCarver.picture());
    }

    @Test
    public void testWidth1() {
        SeamCarver seamCarver = new SeamCarver(P1X1);
        assertEquals(1, seamCarver.width());
    }

    @Test
    public void testWidth2() {
        SeamCarver seamCarver = new SeamCarver(P1X8);
        assertEquals(1, seamCarver.width());
    }

    @Test
    public void testHeight1() {
        SeamCarver seamCarver = new SeamCarver(P1X1);
        assertEquals(1, seamCarver.height());
    }

    @Test
    public void testHeight2() {
        SeamCarver seamCarver = new SeamCarver(P1X8);
        assertEquals(8, seamCarver.height());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testEnergyException1() {
        SeamCarver seamCarver = new SeamCarver(P3X4);

        double e = seamCarver.energy(1, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testEnergyException2() {
        SeamCarver seamCarver = new SeamCarver(P3X4);

        double e = seamCarver.energy(5, 1);
    }

    @Test
    public void testEnergy1() {
        SeamCarver seamCarver = new SeamCarver(P3X4);

        assertEquals(1000.0, seamCarver.energy(0, 1), EPSILON);
    }

    @Test
    public void testEnergy2() {
        SeamCarver seamCarver = new SeamCarver(P3X4);

        assertEquals(1000.0, seamCarver.energy(2, 1), EPSILON);
    }

    @Test
    public void testEnergy3() {
        SeamCarver seamCarver = new SeamCarver(P3X4);

        assertEquals(Math.sqrt(52024), seamCarver.energy(1, 2), EPSILON);
    }

    @Test
    public void testEnergy4() {
        SeamCarver seamCarver = new SeamCarver(P3X4);

        assertEquals(Math.sqrt(52225), seamCarver.energy(1, 1), EPSILON);
    }

    @Test
    public void testFindVerticalSeam1() {
        SeamCarver seamCarver = new SeamCarver(P6X5);
        int[] results = new int[] { 3, 4, 3, 2, 2 };
        assertArrayEquals(results, seamCarver.findVerticalSeam());
    }

}
