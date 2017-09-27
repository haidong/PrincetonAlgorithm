import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int n;

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private static class Node<Item> {
        private Item item;
        private Node<Item> previous;
        private Node<Item> next;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException("Cannot add null!");
        Node<Item> oldFirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;
        if (isEmpty())
            last = first;
        else
            oldFirst.previous = first;
        n++;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException("Cannot add null!");
        Node<Item> oldLast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        last.previous = oldLast;
        if (isEmpty())
            first = last;
        else
            oldLast.next = last;
        n++;
    }

    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Deque is empty!");
        Node<Item> oldFirst = first;
        Item item = oldFirst.item;
        if (n == 1) {
            first = null;
        } else {
            first = oldFirst.next;
            first.previous = null;
        }
        n--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Deque is empty!");
        Node<Item> oldLast = last;
        Item item = oldLast.item;
        if (n == 1) {
            last = null;
        } else {
            last = oldLast.previous;
            last.next = null;
        }
        n--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Not supported!");
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("Ran out, no more!");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {

    }

}
