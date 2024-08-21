import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] flag;
    private int opennum;
    private int size;
    private WeightedQuickUnionUF net;
    private WeightedQuickUnionUF netforfull;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        net = new WeightedQuickUnionUF(n * n + 2);
        netforfull = new WeightedQuickUnionUF(n * n + 1);
        size = n;
        opennum = 0;
        flag = new boolean[n * n + 2];
        flag[0] = true;
        flag[size * size + 1] = true;
    }

    private int pos(int row, int col) {
        return (row - 1) * size + col; // Adjusted for 1-based index
    }

    private void validate(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) { // Adjusted for 1-based index
            throw new IllegalArgumentException();
        }
    }


    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            int posmain = pos(row, col);
            flag[posmain] = true;
            int up = row - 1;
            int down = row + 1;
            int left = col - 1;
            int right = col + 1;

            if (up >= 1 && isOpen(up, col)) { // Adjusted for 1-based index
                net.union(posmain, pos(up, col));
                netforfull.union(posmain, pos(up, col));

            } else if (up < 1) { // Adjusted for 1-based index
                net.union(posmain, 0);
                netforfull.union(posmain, 0);

            }
            if (down <= size && isOpen(down, col)) { // Adjusted for 1-based index
                net.union(posmain, pos(down, col));
                netforfull.union(posmain, pos(down, col));
            } else if (down > size) { // Adjusted for 1-based index
                net.union(posmain, size * size + 1);
            }
            if (left >= 1 && isOpen(row, left)) { // Adjusted for 1-based index
                net.union(posmain, pos(row, left));
                netforfull.union(posmain, pos(row, left));
            }
            if (right <= size && isOpen(row, right)) { // Adjusted for 1-based index
                net.union(posmain, pos(row, right));
                netforfull.union(posmain, pos(row, right));
            }
            opennum++;
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return flag[pos(row, col)];
    }

    public boolean isFull(int row, int col) {
        // isfull需要单独有一个排除最后一排的并查集，这是因为该点可能和最后一层相连，但不与顶部项链
        validate(row, col);
        return netforfull.find(0) == netforfull.find(pos(row, col));
    }

    public int numberOfOpenSites() {
        return opennum;
    }

    public boolean percolates() {
        return net.find(0) == net.find(size * size + 1);
    }

    public static void main(String[] args) {
        // test client (optional)
    }
}
