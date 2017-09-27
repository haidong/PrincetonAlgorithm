import static org.junit.Assert.*;

import org.junit.Test;

public class VerticalPixelGraphTest {

    @Test
    public void testVerticesForX1() {
        VerticalPixelGraph vpg = new VerticalPixelGraph(6, 5);
        
        assertEquals(19, vpg.verticesForX(1, 5, 0));
    }

}
