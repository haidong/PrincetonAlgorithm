import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    @SuppressWarnings("unchecked")
    private Item[] a = (Item[]) new Object[1];
    private int n = 0;

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int max) {
        @SuppressWarnings("unchecked")
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException("Cannot add null!");
        if (n == a.length)
            resize(2 * a.length);
        a[n++] = item;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Deque is empty!");
        int i = StdRandom.uniform(n);
        Item item = a[i];
        if (i != (n - 1))
            a[i] = a[n - 1];
        n--;
        a[n] = null;
        if (n > 0 && n == a.length / 4)
            resize(a.length / 2);
        return item;
    }

    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("Deque is empty!");
        return a[StdRandom.uniform(n)];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }

    private class RandomArrayIterator implements Iterator<Item> {
        private int i = n;
        @SuppressWarnings("unchecked")
        private Item[] queCopy = (Item[]) new Object[n];

        private RandomArrayIterator() {
            System.arraycopy(a, 0, queCopy, 0, n);
            StdRandom.shuffle(queCopy);
        }

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("Ran out, no more!");
            return queCopy[--i];
        }

        public void remove() {
            throw new UnsupportedOperationException("Not supported!");
        }

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}