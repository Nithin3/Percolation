# Percolation
Percolation. Given a composite systems comprised of randomly distributed insulating and metallic materials: what fraction of the materials need to be metallic so that the composite system is an electrical conductor? Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able to drain through to the bottom (or the oil to gush through to the surface)? Scientists have defined an abstract process known as percolation to model such situations.

Don't modify anything in PercolationVisualizer.java or InteractivePercolationVisualizer.java as the latter will function using the API in Percolation.java file to test class Percolation.

The problem. In a famous scientific problem, researchers are interested in the following question: if sites are independently set to be open with probability p (and therefore blocked with probability 1 − p), what is the probability that the system percolates? When p equals 0, the system does not percolate; when p equals 1, the system percolates. The plots below show the site vacancy probability p versus the percolation probability for 20-by-20 random grid (left) and 100-by-100 random grid (right).

Percolation.java

Percolation data type. To model a percolation system, create a data type Percolation with the following API:

public class Percolation {
   public Percolation(int n)                // create n-by-n grid, with all sites blocked
   public    void open(int row, int col)    // open site (row, col) if it is not open already
   public boolean isOpen(int row, int col)  // is site (row, col) open?
   public boolean isFull(int row, int col)  // is site (row, col) full?
   public     int numberOfOpenSites()       // number of open sites
   public boolean percolates()              // does the system percolate?

   public static void main(String[] args)   // test client (optional)
}

Corner cases.  By convention, the row and column indices are integers between 1 and n, where (1, 1) is the upper-left site: Throw a java.lang.IllegalArgumentException if any argument to open(), isOpen(), or isFull() is outside its prescribed range. The constructor should throw a java.lang.IllegalArgumentException if n ≤ 0.


PercolationStats.java

Monte Carlo simulation. To estimate the percolation threshold, consider the following computational experiment:

Initialize all sites to be blocked.
Repeat the following until the system percolates:
    Choose a site uniformly at random among all blocked sites.
    Open the site.
The fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold.

For example, if sites are opened in a 20-by-20 lattice according to the snapshots below, then our estimate of the percolation threshold is 204/400 = 0.51 because the system percolates when the 204th site is opened.

To perform a series of computational experiments, create a data type PercolationStats with the following API.

public class PercolationStats {
   public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
   public double mean()                          // sample mean of percolation threshold
   public double stddev()                        // sample standard deviation of percolation threshold
   public double confidenceLo()                  // low  endpoint of 95% confidence interval
   public double confidenceHi()                  // high endpoint of 95% confidence interval

   public static void main(String[] args)        // test client (described below)
}
The constructor should throw a java.lang.IllegalArgumentException if either n ≤ 0 or trials ≤ 0.
