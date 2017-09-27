import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class RandomizedQueueTest {

    @Test(expected = NullPointerException.class)
    public void testAddFirstNull() {
        RandomizedQueue<String> d = new RandomizedQueue<String>();
        d.enqueue(null);
    }

    @Test
    public void testConstructor() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        assertEquals(0, rq.size());
        assertTrue(rq.isEmpty());
    }

    @Test
    public void testEnqueue() {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        assertEquals(0, rq.size());
        assertTrue(rq.isEmpty());

        rq.enqueue("a");
        assertEquals(1, rq.size());
        assertFalse(rq.isEmpty());

        rq.enqueue("b");
        assertEquals(2, rq.size());
        assertFalse(rq.isEmpty());
    }

    @Test
    public void testDequeueBasic() {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        assertEquals(0, rq.size());
        assertTrue(rq.isEmpty());

        rq.enqueue("a");

        String s = rq.dequeue();
        assertEquals("a", s);
        assertTrue(rq.isEmpty());

        rq.enqueue("b");
        rq.enqueue("c");
        assertEquals(2, rq.size());

        s = rq.dequeue();
        assertTrue(s.equals("b") || s.equals("c"));
    }

    @Test
    public void testSample() {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        rq.enqueue("a");
        rq.enqueue("b");
        rq.enqueue("c");

        String s = rq.sample();
        assertTrue(s.equals("a") || s.equals("b") || s.equals("c"));

        assertEquals(3, rq.size());

        s = rq.dequeue();
        s = rq.sample();
        assertEquals(2, rq.size());
    }

    @Test
    public void testIteratorBasic() {
        RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
        d.enqueue(1);
        d.enqueue(2);
        d.enqueue(3);
        d.enqueue(4);

        Iterator<Integer> it = d.iterator();
        assertTrue(it.hasNext());
        int n = it.next();
        assertTrue(n == 1 || n == 2 || n == 3 || n == 4);
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorEmptyNoSuchElementException() {
        RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
        d.enqueue(1);
        d.enqueue(2);

        Iterator<Integer> it = d.iterator();
        int n = it.next();
        n = it.next();
        n = it.next();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIteratorRemoveUnsupportedOperationException() {
        RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
        d.enqueue(1);
        d.enqueue(2);

        Iterator<Integer> it = d.iterator();
        int n = it.next();
        it.remove();
    }

}
