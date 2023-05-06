package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private int[][] sites;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf2;
    private int openNum;

    public Percolation(int N) {    // create N-by-N grid, with all sites initially blocked
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("Argument N is illegal.");
        }
        this.N = N;
        sites = new int[N][N];
        uf = new WeightedQuickUnionUF(N * N + 1);
        uf2 = new WeightedQuickUnionUF(N * N + 2);

        for (int i = 0; i < N; i++) {   //N * N connect first floor
            uf.union(N * N, i);
            
            uf2.union(N * N, i);
            uf2.union(N * N + 1, N * (N - 1) + i);
        }
    }

    private int transform(int row, int col) {
        return row * N + col;
    }

    //detect whether the row and column indices are integers between 0 and N − 1
    private boolean detectArgument(int row, int col) {
        if ((row >= 0 && row < N) && (col >= 0 && col < N)) {
            return true;
        }
        return false;
    }

    public void open(int row, int col) {    // open the site (row, col) if it is not open already
        if (!detectArgument(row, col)) {
            throw new java.lang.IndexOutOfBoundsException("row:" + row
                    + ", col:" + col + " is out of index.");
        }
        if (isOpen(row, col)) {
            return;
        }

        int[] r = {-1, 1, 0, 0};
        int[] c = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            if (detectArgument(row + r[i], col + c[i]) && sites[row + r[i]][col + c[i]] > 0) {
                uf.union(transform(row, col), transform(row + r[i], col + c[i]));
                uf2.union(transform(row, col), transform(row + r[i], col + c[i]));
            }
        }
        sites[row][col] = 1;
        openNum++;
    }
    public boolean isOpen(int row, int col) {   // is the site (row, col) open?
        if (!detectArgument(row, col)) {
            throw new java.lang.IndexOutOfBoundsException("row:" + row
                    + ", col:" + col + " is out of index.");
        }

        return sites[row][col] > 0;
    }
    public boolean isFull(int row, int col) {   // is the site (row, col) full?
        if (!detectArgument(row, col)) {
            throw new java.lang.IndexOutOfBoundsException("row:" + row
                    + ", col:" + col + " is out of index.");
        }

        return uf.connected(transform(row, col), N * N) && isOpen(row, col);
    }
    public int numberOfOpenSites() {    // number of open sites
        return openNum;
    }
    public boolean percolates() {   // does the system percolate?
        return uf2.connected(N * N, N * N + 1);
    }
    public static void main(String[] args) {    // use for unit testing (not required)

    }

}
