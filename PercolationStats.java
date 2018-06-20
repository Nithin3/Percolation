import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    //Double array for threshold values of each computational experiment
    private double[] thresholdValues;
    private int T;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials){
        int countOpenSites = 0, row, col;
        this.T = trials;
        thresholdValues = new double[T];

        if (n <= 0 || T <= 0) {
            throw new IllegalArgumentException("Arguments out of bound");
        }

        for (int i = 0; i < trials; i++){

            Percolation percolation = new Percolation(n);
            do{
                row = StdRandom.uniform(1,n+1);
                col = StdRandom.uniform(1,n+1);

                if (percolation.isOpen(row,col)){
                    continue;
                }
                percolation.open(row,col);
                countOpenSites++;

            }while (percolation.percolates()==false);

            thresholdValues[i] = (double) countOpenSites/(n*n);
            countOpenSites=0;
        }

    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(thresholdValues);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){

        return StdStats.stddev(thresholdValues);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo(){

        return (mean()-(1.96*stddev()/Math.sqrt(T)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return (mean()+(1.96*stddev()/Math.sqrt(T)));
    }

    // test client (described below)
    public static void main(String[] args){

        //Command Line arguments
        int N = Integer.valueOf(args[0]);
        int T = Integer.valueOf(args[1]);
        final PercolationStats ps = new PercolationStats(N, T);
        System.out.println("mean                     =  "+ ps.mean());
        System.out.println("stddev                   =  "+ ps.stddev());
        System.out.println("95%% confidence interval =  ["+ps.confidenceLo()+","+ps.confidenceHi()+"]");
    }
}
