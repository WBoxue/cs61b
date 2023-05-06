package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int N;
    private int T;
    private Percolation[] percolation;

    private int[] a;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Argument N or T are illegal.");
        }

        this.N = N;
        this.T = T;
        percolation = new Percolation[T];
        for (int i = 0; i < T; i++) {
            percolation[i] = pf.make(N);
        }
        startT();
    }

    private void startT() {
        a = new int[T];
        int dx;
        int dy;
        for (int i = 0; i < T; i++) {
            while (!percolation[i].percolates()) {
                dx = StdRandom.uniform(N);
                dy = StdRandom.uniform(N);
                percolation[i].open(dx, dy);
            }
            a[i] = percolation[i].numberOfOpenSites();
        }
    }

    public double mean() {  // sample mean of percolation threshold
        return StdStats.mean(a);
    }
    public double stddev() {    // sample standard deviation of percolation threshold
        return StdStats.stddev(a);
    }
    public double confidenceLow() {     // low endpoint of 95% confidence interval
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }
    public double confidenceHigh() {    // high endpoint of 95% confidence interval
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }
}
