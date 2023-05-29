import base.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MaxStrictlyIncreasingCells {
    public int maxIncreasingCells(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[] rowmax = new int[m]; // max length so far of row
        int[] colmax = new int[n]; // max length so far of col
        TreeMap<Integer, List<int[]>> tm = new TreeMap<>();
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int v = a[i][j];
                tm.computeIfAbsent(v, k -> new ArrayList<>()).add(new int[]{i, j});
            }
        }
        for (int k : tm.keySet()) {
            List<int[]> values = tm.get(k);
            for (int[] vi : values) {
                int r = vi[0];
                int c = vi[1];
                dp[r][c] = Math.max(rowmax[r], colmax[c]) + 1;
            }
            // avoid doing two at the same time so that same values don't interfere with each other
            for (int[] vi : values) {
                int r = vi[0];
                int c = vi[1];
                rowmax[r] = Math.max(rowmax[r], dp[r][c]);
                colmax[c] = Math.max(colmax[c], dp[r][c]);
            }
        }
        int res = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new MaxStrictlyIncreasingCells().maxIncreasingCells(ArrayUtils.read("[[1,1],[1,1]]")));
    }
}
