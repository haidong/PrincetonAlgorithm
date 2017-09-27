import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

public class BoardTest {

    private static final int[][] EXAMPLE_BLOCK1 = new int[][] {{8,1,3},{4,0,2},{7,6,5}};
    private static final int[][] GOAL_BLOCK1 = new int[][] {{1,2,3},{4,5,6},{7,8,0}};
    private static final int[][] EXAMPLE_BLOCK2 = new int[][] {{15,1,3,4},{2,0,10,8},{5,6,7,9},{11,12,13,14}};
    private static final int[][] GOAL_BLOCK2 = new int[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
    @Test
    public void testBoardDimension() {
        int[][] a = new int[3][3];
        Board b = new Board(a);
        
        assertEquals(3, b.dimension());
    }

    @Test
    public void testBoardManhattan1() {
        Board b = new Board(EXAMPLE_BLOCK1);
        
        assertEquals(10, b.manhattan());
    }

    @Test
    public void testBoardManhattan2() {
        Board b = new Board(EXAMPLE_BLOCK2);
        
        assertEquals(26, b.manhattan());
    }

    @Test
    public void testBoardHamming1() {
        Board b = new Board(EXAMPLE_BLOCK1);
        
        assertEquals(5, b.hamming());
    }

    @Test
    public void testBoardHamming2() {
        Board b = new Board(EXAMPLE_BLOCK2);
        
        assertEquals(12, b.hamming());
    }

    @Test
    public void testBoardIsGoal1() {
        Board b = new Board(EXAMPLE_BLOCK1);
        
        assertFalse(b.isGoal());
    }

    @Test
    public void testBoardIsGoal2() {
        Board b = new Board(GOAL_BLOCK1);
        
        assertTrue(b.isGoal());
    }

    @Test
    public void testBoardIsGoal3() {
        Board b = new Board(EXAMPLE_BLOCK2);
        
        assertFalse(b.isGoal());
    }

    @Test
    public void testBoardIsGoal4() {
        Board b = new Board(GOAL_BLOCK2);
        
        assertTrue(b.isGoal());
    }

    @Test
    public void testBoardEquals1() {
        int[][] a = new int[3][3];
        a[0][0] = 1;
        a[0][1] = 2;
        a[0][2] = 3;
        a[1][0] = 4;
        a[1][1] = 5;
        a[1][2] = 6;
        a[2][0] = 8;
        a[2][1] = 7;
        a[2][2] = 0;
        Board b = new Board(a);

        int[][] c = new int[3][3];
        c[0][0] = 1;
        c[0][1] = 2;
        c[0][2] = 3;
        c[1][0] = 4;
        c[1][1] = 5;
        c[1][2] = 6;
        c[2][0] = 7;
        c[2][1] = 8;
        c[2][2] = 0;
        Board d = new Board(a);
        
        assertFalse(b.equals(c));
    }

    @Test
    public void testBoardEquals2() {
        Board b = new Board(EXAMPLE_BLOCK1);

        int[][] c = new int[3][3];
        c[0][0] = 8;
        c[0][1] = 1;
        c[0][2] = 3;
        c[1][0] = 4;
        c[1][1] = 0;
        c[1][2] = 2;
        c[2][0] = 7;
        c[2][1] = 6;
        c[2][2] = 5;
        Board d = new Board(c);
        
        assertTrue(b.equals(d));
    }

    @Test
    public void testBoardEquals3() {
        Board b = new Board(EXAMPLE_BLOCK1);

        assertFalse(b.equals("whatever"));
    }

    @Test
    public void testBoardTwin1() {
        int[][] a = new int[3][3];
        a[0][0] = 8;
        a[0][1] = 1;
        a[0][2] = 3;
        a[1][0] = 4;
        a[1][1] = 0;
        a[1][2] = 2;
        a[2][0] = 7;
        a[2][1] = 6;
        a[2][2] = 5;
        Board b = new Board(a);
        Board c = b.twin();

        assertFalse(b.equals(c));
        assertEquals(b.dimension(), c.dimension());
    }

    @Test
    public void testBoardNeighbors1() {
        int size = 0;
        Board b = new Board(EXAMPLE_BLOCK1);

        for (Board neighbor: b.neighbors()) {
            assertNotNull(neighbor);
            size++;
        }
        
        assertEquals(4, size);
    }

    @Test
    public void testBoardNeighbors2() {
        int size = 0;
        int[][] a = new int[3][3];
        a[0][0] = 0;
        a[0][1] = 1;
        a[0][2] = 3;
        a[1][0] = 4;
        a[1][1] = 8;
        a[1][2] = 2;
        a[2][0] = 7;
        a[2][1] = 6;
        a[2][2] = 5;
        Board b = new Board(a);

        for (Board neighbor: b.neighbors()) {
            assertNotNull(neighbor);
            size++;
        }
        
        assertEquals(2, size);
    }

    @Test
    public void testBoardNeighbors3() {
        int size = 0;
        int[][] a = new int[3][3];
        a[0][0] = 1;
        a[0][1] = 0;
        a[0][2] = 3;
        a[1][0] = 4;
        a[1][1] = 8;
        a[1][2] = 2;
        a[2][0] = 7;
        a[2][1] = 6;
        a[2][2] = 5;
        Board b = new Board(a);

        for (Board neighbor: b.neighbors()) {
            assertNotNull(neighbor);
            size++;
        }
        
        assertEquals(3, size);

    }

    @Test
    public void testBoardNeighbors4() {
        int size = 0;
        int[][] a = new int[3][3];
        a[0][0] = 1;
        a[0][1] = 5;
        a[0][2] = 3;
        a[1][0] = 4;
        a[1][1] = 8;
        a[1][2] = 2;
        a[2][0] = 7;
        a[2][1] = 6;
        a[2][2] = 0;
        Board b = new Board(a);

        for (Board neighbor: b.neighbors()) {
            assertNotNull(neighbor);
            size++;
        }
        
        assertEquals(2, size);
    }

}
