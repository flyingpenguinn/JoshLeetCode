import java.util.TreeSet;

/*
LC#363
Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:

Input: matrix = [[1,0,1],[0,-2,3]], k = 2
Output: 2
Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
             and 2 is the max number no larger than k (k = 2).
Note:

The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?
 */
public class MaxSumOfRectangleNoLargerThanK {

    // like 2d max rectangle problem. use a treeset to solve at most k stuff
    public int maxSumSubmatrix(int[][] a, int t) {
        int m = a.length;
        int n = a[0].length;

        return m < n ? msmaller(a, t, m, n) : nsmaller(a, t, m, n);
    }

    private int nsmaller(int[][] a, int t, int m, int n) {
        int[][] sum = new int[m][n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (j == 0) {
                    sum[i][j] = a[i][j];
                } else {
                    sum[i][j] = sum[i][j - 1] + a[i][j];
                }
            }
        }
        int max = Integer.MIN_VALUE;
        // i to j inclusive
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                TreeSet<Integer> pre = new TreeSet<>();
                int psum = 0;
                for (int k = 0; k < m; k++) {
                    int ele = i == 0 ? sum[k][j] : sum[k][j] - sum[k][i - 1];
                    psum += ele;
                    Integer floor = pre.ceiling(psum - t);
                    if (floor != null) {
                        int diff = psum - floor;
                        max = Math.max(diff, max);
                    }
                    if (psum <= t) {
                        max = Math.max(psum, max);
                    }
                    pre.add(psum);
                }
            }
        }
        return max;
    }

    private int msmaller(int[][] a, int t, int m, int n) {
        int[][] sum = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    sum[i][j] = a[i][j];
                } else {
                    sum[i][j] = sum[i - 1][j] + a[i][j];
                }
            }
        }
        int max = Integer.MIN_VALUE;
        // i to j inclusive
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                TreeSet<Integer> pre = new TreeSet<>();
                int psum = 0;
                for (int k = 0; k < n; k++) {
                    int ele = i == 0 ? sum[j][k] : sum[j][k] - sum[i - 1][k];
                    psum += ele;
                    Integer floor = pre.ceiling(psum - t);
                    if (floor != null) {
                        int diff = psum - floor;
                        max = Math.max(diff, max);
                    }
                    if (psum <= t) {
                        max = Math.max(psum, max);
                    }
                    pre.add(psum);
                }
            }
        }
        return max;
    }
}
