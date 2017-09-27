import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int openSites = 0;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF bf;
    private int dimension;
    private boolean[] siteOpenOrNot;
    private int source;
    private int sink;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Size cannot be 0 or negative!");
        }
        dimension = n;
        source = n * n;
        sink = n * n + 1;
        uf = new WeightedQuickUnionUF(sink + 1);
        bf = new WeightedQuickUnionUF(sink);
        siteOpenOrNot = new boolean[source];

        // for (int i = 0; i < n; i++) {
        // uf.union(i, source);
        // bf.union(i, source);
        // }
        // for (int i = n * n - n; i < n * n; i++) {
        // uf.union(i, sink);
        // }
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public void open(int row, int col) {
        if (row < 1 || row > dimension || col < 1 || col > dimension) {
            throw new IndexOutOfBoundsException("row/col out bounds");
        }
        if (!(isOpen(row, col))) {
            siteOpenOrNot[xyTo1D(row, col)] = true;
            openSites++;
            // Left
            if (col != 1 && isOpen(row, col - 1)) {
                uf.union(xyTo1D(row, col), xyTo1D(row, col - 1));
                bf.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            }
            // Right
            if (col != dimension && isOpen(row, col + 1)) {
                uf.union(xyTo1D(row, col), xyTo1D(row, col + 1));
                bf.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            }

            // Above
            if (row != 1 && isOpen(row - 1, col)) {
                uf.union(xyTo1D(row, col), xyTo1D(row - 1, col));
                bf.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            }
            // Below
            if (row != dimension && isOpen(row + 1, col)) {
                uf.union(xyTo1D(row, col), xyTo1D(row + 1, col));
                bf.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            }

            if (row == 1) {
                uf.union(xyTo1D(row, col), source);
                bf.union(xyTo1D(row, col), source);
            }

            if (row == dimension) {
                uf.union(xyTo1D(row, col), sink);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 1 || row > dimension || col < 1 || col > dimension) {
            throw new IndexOutOfBoundsException("row/col out bounds");
        }
        return siteOpenOrNot[xyTo1D(row, col)];
    }

    public boolean isFull(int row, int col) {
        if (row < 1 || row > dimension || col < 1 || col > dimension) {
            throw new IndexOutOfBoundsException("row/col out bounds");
        }
        if (!isOpen(row, col))
            return false;
        return bf.connected(xyTo1D(row, col), source);
    }

    public boolean percolates() {
        return uf.connected(source, sink);
    }

    private int xyTo1D(int row, int col) {
        return (row - 1) * dimension + col - 1;
    }

    public static void main(String[] args) {

    }

}