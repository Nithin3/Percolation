import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF uf;
    private boolean[] sites;
    private int N;
    private int count;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n){
        this.N = n;
        int numOfSites = n*n;
        uf = new WeightedQuickUnionUF(numOfSites+2);
        sites = new boolean[numOfSites+2];

        for(int i = 0; i < numOfSites; i++){
            sites[i] = false;
        }

        sites[0] = true;
        sites[sites.length-1] = true;

    }

    //Checks if the index is in bounds
    private void checkIndex(int i, int j){
        if(i < 1 || i > N){
            throw new IllegalArgumentException("i is not a valid index");
        }
        if(j < 1 || j > N){
            throw new IllegalArgumentException("j is not a valid index");
        }
    }

    private int idNumber(int i, int j){
        return (i-1)*N +j;
    }

    private boolean isInTopRow(int i, int j){
        int id = idNumber(i,j);
        if(id <= N){
            return true;
        }
        return false;
    }

    private boolean isInBottomRow(int i, int j){
        int id = idNumber(i,j);

        if(id > ((N*N)-N)){
            return true;
        }
        return false;
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {

        checkIndex(row, col);
        int id = idNumber(row, col);

            sites[id] = true;
            count++;

            if (row != 1 && isOpen(row - 1, col)) {
                uf.union(id, idNumber(row - 1, col));
            }

            if (col != N && isOpen(row, col + 1)) {
                uf.union(id, idNumber(row, col + 1));
            }
            if (row != N && isOpen(row + 1, col)) {
                uf.union(id, idNumber(row + 1, col));
            }
            if (col != 1 && isOpen(row, col - 1)) {
                uf.union(id, idNumber(row, col - 1));
            }

            if (isInTopRow(row, col)) {
                uf.union(0, id);
            }
            if (isInBottomRow(row, col)) {
                uf.union(sites.length - 1, id);
            }


    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col){

        checkIndex(row,col);
        return sites[idNumber(row,col)];

    }

    // is site (row, col) full?
    public boolean isFull(int row, int col){
        checkIndex(row,col);
        int id = idNumber(row,col);
        if(uf.connected(id,0)){
            return true;
        }
        return false;
    }

    // number of open sites
    public int numberOfOpenSites(){
        return count;
    }

    // does the system percolate?
    public boolean percolates(){

        if(uf.connected(0,(N*N)+1))
            return true;
        return false;
    }


    public static void main(String[] args){

        Percolation percolation = new Percolation(3);

        percolation.open(1,2);
        percolation.open(2,3);
        percolation.open(3,1);

        if(percolation.percolates()){
            System.out.println("Percolated");
        }else{
            System.out.println("Not Percolated");
        }

    }
}