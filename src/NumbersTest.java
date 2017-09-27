import static org.junit.Assert.*;

import org.junit.Test;

public class NumbersTest {

    @Test
    public void test1() {
        assertEquals("10000", Numbers.stringValue(16, 2));
    }

    @Test
    public void test2() {
        assertEquals("16", Numbers.stringValue(16, 10));
    }

    @Test
    public void test3() {
        assertEquals("-10000", Numbers.stringValue(-16, 2));
    }

}
