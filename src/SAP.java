import java.util.Iterator;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;

public class SAP {
    private Digraph graph;

    public SAP(Digraph G) {
        if (G == null)
            throw new NullPointerException();
        graph = new Digraph(G);
    }

    public int length(int v, int w) {
        checkIndexBounds(v, w);
        int a = ancestor(v, w);
        if (a == -1)
            return -1;
        return shortestDistanceUsingBfs(v, a) + shortestDistanceUsingBfs(w, a);
    }

    private void checkIndexBounds(int v, int w) {
        if (v < 0 || v > graph.V() - 1 || w < 0 || w > graph.V() - 1)
            throw new IndexOutOfBoundsException();
    }

    private SET<Integer> ancestorQueue(SET<Integer> q, int s) {
        q.add(s);
        if (!graph.adj(s).iterator().hasNext())
            return q;
        for (int i : graph.adj(s)) {
            if (!q.contains(i))
                ancestorQueue(q, i);
        }
        return q;
    }

    public int ancestor(int v, int w) {
        checkIndexBounds(v, w);
        for (int i : graph.adj(v)) {
            if (i == w)
                return w;
        }
        for (int i : graph.adj(w)) {
            if (i == v)
                return v;
        }
        SET<Integer> vAncestorQ = new SET<Integer>();
        SET<Integer> wAncestorQ = new SET<Integer>();
        SET<Integer> ancestors = new SET<Integer>();
        vAncestorQ = ancestorQueue(vAncestorQ, v);
        wAncestorQ = ancestorQueue(wAncestorQ, w);
        for (int i : vAncestorQ) {
            for (int j : wAncestorQ) {
                if (i == j)
                    ancestors.add(i);
            }
        }
        if (ancestors.size() == 0)
            return -1;
        int result = ancestors.min();
        int totalDistance = shortestDistanceUsingBfs(v, result) + shortestDistanceUsingBfs(w, result);

        for (Iterator<Integer> iterator = ancestors.iterator(); iterator.hasNext();) {
            int i = iterator.next();
            int tempDistance = shortestDistanceUsingBfs(v, i) + shortestDistanceUsingBfs(w, i);
            if (tempDistance < totalDistance) {
                totalDistance = tempDistance;
                result = i;
            }
        }
        return result;
    }

    private int shortestDistanceUsingBfs(int v, int w) {
        if (v == w)
            return 0;
        Queue<Integer> queue = new Queue<Integer>();
        boolean[] marked = new boolean[graph.V()];
        int[] edgeTo = new int[graph.V()];
        marked[v] = true;
        queue.enqueue(v);
        while (!queue.isEmpty()) {
            int i = queue.dequeue();
            for (int j : graph.adj(i)) {
                if (!marked[j]) {
                    edgeTo[j] = i;
                    marked[j] = true;
                    queue.enqueue(j);
                }
            }
        }
        if (!marked[w])
            return -1;
        int result = 0;
        for (int x = w; x != v; x = edgeTo[x]) {
            result++;
        }
        return result;
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        checkNulls(v, w);
        int shortestSoFar = length(v.iterator().next(), w.iterator().next());
        for (int i : v) {
            for (int j : w) {
                if (shortestSoFar > length(i, j))
                    shortestSoFar = length(i, j);
            }
        }
        return shortestSoFar;
    }

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        checkNulls(v, w);
        int tempA = v.iterator().next();
        int tempB = w.iterator().next();
        int shortestSoFar = length(tempA, tempB);
        for (int i : v) {
            for (int j : w) {
                if (shortestSoFar > length(i, j)) {
                    shortestSoFar = length(i, j);
                    tempA = i;
                    tempB = j;
                }
            }
        }
        return ancestor(tempA, tempB);
    }

    private void checkNulls(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null)
            throw new NullPointerException();
    }

    public static void main(String[] args) {
    }
}