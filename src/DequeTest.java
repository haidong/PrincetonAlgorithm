import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class DequeTest {

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test(expected = NullPointerException.class)
    public void testAddFirstNull() {
        Deque<String> d = new Deque<String>();
        d.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void testAddLastNull() {
        Deque<String> d = new Deque<String>();
        d.addLast(null);
    }

    @Test
    public void testConstructor() {
        Deque<Integer> d = new Deque<Integer>();
        assertEquals(0, d.size());
        assertTrue(d.isEmpty());
    }

    @Test
    public void testAddFirstBasicTest() {
        Deque<Integer> d = new Deque<Integer>();
        d.addFirst(5);

        assertEquals(1, d.size());
        assertFalse(d.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromEmptyDequeNoSuchElementException() {
        Deque<String> d = new Deque<String>();
        d.removeFirst();
    }

    @Test
    public void testRemoveFirstBasicTest1() {
        Deque<Integer> d = new Deque<Integer>();
        d.addFirst(5);

        int first = d.removeFirst();
        assertEquals(5, first);
        assertEquals(0, d.size());
    }

    @Test
    public void testRemoveFirstBasicTest2() {
        Deque<Integer> d = new Deque<Integer>();
        d.addLast(5);

        int first = d.removeFirst();
        assertEquals(5, first);
        assertEquals(0, d.size());
    }

    @Test
    public void testAddLastBasicTest() {
        Deque<Integer> d = new Deque<Integer>();
        d.addLast(8);

        assertEquals(1, d.size());
        assertFalse(d.isEmpty());
    }

    @Test
    public void testRemoveLastBasicTest1() {
        Deque<Integer> d = new Deque<Integer>();
        d.addLast(8);

        int last = d.removeLast();
        assertEquals(8, last);
        assertEquals(0, d.size());
    }

    @Test
    public void testRemoveLastBasicTest2() {
        Deque<Integer> d = new Deque<Integer>();
        d.addFirst(8);

        int last = d.removeLast();
        assertEquals(8, last);
        assertEquals(0, d.size());
    }

    @Test
    public void testAddFirstFirstLastFirst() {
        Deque<Integer> d = new Deque<Integer>();
        d.addFirst(1);
        d.addFirst(2);
        d.addLast(3);
        d.addFirst(4);

        assertEquals(4, d.size());
        assertFalse(d.isEmpty());
    }

    @Test
    public void testAddFirstFirstLastLast() {
        Deque<Integer> d = new Deque<Integer>();
        d.addFirst(1);
        d.addFirst(2);
        d.addLast(3);
        d.addLast(4);

        assertEquals(4, d.size());
        assertFalse(d.isEmpty());
    }

    @Test
    public void testAddLastFirstLastLast() {
        Deque<Integer> d = new Deque<Integer>();
        d.addLast(1);
        d.addFirst(2);
        d.addLast(3);
        d.addLast(4);

        assertEquals(4, d.size());
        assertFalse(d.isEmpty());
    }

    @Test
    public void testAddLastFirstLastFirst() {
        Deque<Integer> d = new Deque<Integer>();
        d.addLast(1);
        d.addFirst(2);
        d.addLast(3);
        d.addFirst(4);

        assertEquals(4, d.size());
        assertFalse(d.isEmpty());
    }

    @Test
    public void testMoreRemovalTest1() {
        Deque<Integer> d = new Deque<Integer>();
        d.addLast(1);
        d.addFirst(2);
        d.addLast(3);
        d.addFirst(4);

        int n = d.removeFirst();
        assertEquals(4, n);
        n = d.removeLast();
        assertEquals(3, n);
        n = d.removeLast();
        assertEquals(1, n);
        n = d.removeLast();
        assertEquals(2, n);

    }

    @Test
    public void testMoreRemovalTest2() {
        Deque<Integer> d = new Deque<Integer>();
        d.addLast(1);
        d.addFirst(2);
        d.addLast(3);
        d.addFirst(4);

        int n = d.removeFirst();
        assertEquals(4, n);
        n = d.removeFirst();
        assertEquals(2, n);
        n = d.removeFirst();
        assertEquals(1, n);
        n = d.removeLast();
        assertEquals(3, n);
    }

    @Test
    public void testIteratorBasic() {
        Deque<Integer> d = new Deque<Integer>();
        d.addLast(1);
        d.addFirst(2);
        d.addLast(3);
        d.addFirst(4);

        Iterator<Integer> it = d.iterator();
        assertTrue(it.hasNext());
        int n = it.next();
        assertEquals(4, n);
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorEmptyNoSuchElementException() {
        Deque<Integer> d = new Deque<Integer>();
        d.addLast(1);
        d.addFirst(2);

        Iterator<Integer> it = d.iterator();
        int n = it.next();
        n = it.next();
        n = it.next();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIteratorRemoveUnsupportedOperationException() {
        Deque<Integer> d = new Deque<Integer>();
        d.addLast(1);
        d.addFirst(2);

        Iterator<Integer> it = d.iterator();
        int n = it.next();
        it.remove();
    }

    @Test
    public void testIteratorFromGraderHaidongInterpretation() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addFirst(6);
        deque.addLast(7);
        deque.addFirst(8);
        deque.removeLast();
        assertEquals(7, deque.size());
    }

}
