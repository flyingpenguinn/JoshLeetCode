/*
LC#1314
Given a m * n matrix mat and an integer K, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for i - K <= r <= i + K, j - K <= c <= j + K, and (r, c) is a valid position in the matrix.


Example 1:

Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
Output: [[12,21,16],[27,45,33],[24,39,28]]
Example 2:

Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
Output: [[45,45,45],[45,45,45],[45,45,45]]


Constraints:

m == mat.length
n == mat[i].length
1 <= m, n, K <= 100
1 <= mat[i][j] <= 100
 */
public class MaxBlockSum {
    public int[][] matrixBlockSum(int[][] a, int k) {
        int m = a.length;
        int n = a[0].length;
        int[][] sum = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    sum[i][j] = a[i][j];
                } else if (i == 0) {
                    sum[i][j] = sum[i][j - 1] + a[i][j];
                } else if (j == 0) {
                    sum[i][j] = sum[i - 1][j] + a[i][j];
                } else {
                    sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + a[i][j];
                }
            }
        }
        int[][] r = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int ulr = Math.max(i - k, 0);
                int ulc = Math.max(j - k, 0);
                int lrr = Math.min(i + k, m - 1);
                int lrc = Math.min(j + k, n - 1);
                int lminus = ulc == 0 ? 0 : sum[lrr][ulc - 1];
                int uminus = ulr == 0 ? 0 : sum[ulr - 1][lrc];
                int addback = ulc == 0 || ulr == 0 ? 0 : sum[ulr - 1][ulc - 1];
                int cur = sum[lrr][lrc] - lminus - uminus + addback;
                r[i][j] = cur;
            }
        }
        return r;
    }
}
