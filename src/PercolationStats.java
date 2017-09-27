import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

    private double[] simulationResults;
    private int numberOfTries;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trails must be positive integers.");
        }
        numberOfTries = trials;
        simulationResults = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!(p.percolates())) {
                p.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
            }
            simulationResults[i] = (double) p.numberOfOpenSites() / (double) (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(simulationResults);
    }

    public double stddev() {
        return StdStats.stddev(simulationResults);
    }

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(numberOfTries);
    }

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(numberOfTries);
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
    }

}
