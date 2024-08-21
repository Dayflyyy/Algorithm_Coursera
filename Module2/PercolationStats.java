import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] thresholds;
    private int numberOfTrials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials must be greater than 0");
        }
        numberOfTrials = trials;
        thresholds = new double[numberOfTrials];
        for (int i = 0; i < numberOfTrials; i++) {
            Percolation percolation = new Percolation(n);
            int openSites = 0;
            while (!percolation.percolates()) {
                int row = StdRandom.uniformInt(n) + 1;
                int col = StdRandom.uniformInt(n) + 1;
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    openSites++;
                }
            }
            thresholds[i] = (double) openSites / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(numberOfTrials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(numberOfTrials);
    }

    // test client (see below)
    public static void main(String[] args) {
//        if (args.length < 2) {
//            throw new IllegalArgumentException("Two arguments required: n (grid size) and trials (number of trials)");
//        }
//        int n = Integer.parseInt(args[0]);
//        int trials = Integer.parseInt(args[1]);
//        PercolationStats stats = new PercolationStats(n, trials);
        PercolationStats stats = new PercolationStats(2, 10000);


        System.out.printf("%-25s = %s\n", "mean", stats.mean());
        System.out.printf("%-25s = %s\n", "stddev", stats.stddev());
        System.out.printf("%-25s = [%s, %s]\n", "95% confidence interval", stats.confidenceLo(), stats.confidenceHi());
    }
}
