import static org.junit.Assert.*;

import org.junit.Test;

public class SolverTest {

    @Test
    public void testIsSolvable() {
        int[][] a = new int[3][3];
        a[0][0] = 1;
        a[0][1] = 2;
        a[0][2] = 3;
        a[1][0] = 0;
        a[1][1] = 7;
        a[1][2] = 6;
        a[2][0] = 5;
        a[2][1] = 4;
        a[2][2] = 8;
        Board b = new Board(a);
        Solver s = new Solver(b);
        
        assertTrue(s.isSolvable());
        assertEquals(7, s.moves());
    }

}
