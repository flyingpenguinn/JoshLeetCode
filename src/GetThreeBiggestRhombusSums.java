import java.util.Set;
import java.util.TreeSet;

public class GetThreeBiggestRhombusSums {

    public int[] getBiggestThree(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] left = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                left[i][j] = value(left, i - 1, j - 1) + a[i][j];
            }
        }
        int[][] right = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                right[i][j] = value(right, i - 1, j + 1) + a[i][j];
            }
        }
        TreeSet<Integer> res = new TreeSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 1; k <= Math.min(m, n); k++) {
                    if (k == 1) {
                        res.add(a[i][j]);
                    }
                    else {
                        // this only works for cases where k>1
                        int p3x = i - 2 * (k - 1);
                        int p2x = i - k + 1;
                        int p2y = j - k + 1;
                        int p4x = p2x;
                        int p4y = j + k - 1;
                        if (!valid(p3x, m) || !valid(p2x, m) || !valid(p2y, n) || !valid(p4y, n)) {
                            break;
                        }
                        int diff1 = left[i][j] - left[p2x][p2y];
                        int diff2 = right[i][j] - right[p4x][p4y];
                        int diff3 = right[p2x][p2y] - right[p3x][j];
                        int diff4 = left[p4x][p4y] - left[p3x][j];
                        // we counted i, j twice but didnt count p3x, j
                        // p2 and p4 were counted in their own segment
                        int cv = diff1 + diff2 + diff3 + diff4 + a[p3x][j] - a[i][j];
                        res.add(cv);
                    }
                    if (res.size() > 3) {
                        // top 3, so set starts with a small one
                        res.pollFirst();
                    }
                }
            }
        }
        // reverse the set
        int[] rr = new int[res.size()];
        int ri = 0;
        while(!res.isEmpty()){
            rr[ri++]  = res.pollLast();
        }
        return rr;
    }

    private boolean valid(int i, int limit) {
        return i >= 0 && i < limit;
    }

    private int value(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (!valid(i, m) || !valid(j, n)) {
            return 0;
        }
        return grid[i][j];
    }
}
