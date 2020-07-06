/*
LC#1504
Given a rows * columns matrix mat of ones and zeros, return how many submatrices have all ones.



Example 1:

Input: mat = [[1,0,1],
              [1,1,0],
              [1,1,0]]
Output: 13
Explanation:
There are 6 rectangles of side 1x1.
There are 2 rectangles of side 1x2.
There are 3 rectangles of side 2x1.
There is 1 rectangle of side 2x2.
There is 1 rectangle of side 3x1.
Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.
Example 2:

Input: mat = [[0,1,1,0],
              [0,1,1,1],
              [1,1,1,0]]
Output: 24
Explanation:
There are 8 rectangles of side 1x1.
There are 5 rectangles of side 1x2.
There are 2 rectangles of side 1x3.
There are 4 rectangles of side 2x1.
There are 2 rectangles of side 2x2.
There are 2 rectangles of side 3x1.
There is 1 rectangle of side 3x2.
Total number of rectangles = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24.
Example 3:

Input: mat = [[1,1,1,1,1,1]]
Output: 21
Example 4:

Input: mat = [[1,0,1],[0,1,0],[1,0,1]]
Output: 5


Constraints:

1 <= rows <= 150
1 <= columns <= 150
0 <= mat[i][j] <= 1
 */
public class CountSubmatricesWithAllOnes {
    // Om^2*n. we convert to sth like 2d subarray problem
    // @todo investigate the on^2 stack solution
    public int numSubmat(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] sum = new int[m][n];
        for (int j = 0; j < n; j++) {
            int colsum = 0;
            for (int i = 0; i < m; i++) {
                colsum += a[i][j];
                sum[i][j] = colsum;
            }
        }
        int r = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                int target = j - i + 1;
                int k = 0;
                // find consecutive segments that add up to j-i+1. each such segment gives n*(n+1)/2 matrices
                while (k < n) {
                    while (k < n && (sum[j][k] - (i == 0 ? 0 : sum[i - 1][k]) != target)) {
                        k++;
                    }
                    int count = 0;
                    while (k < n && (sum[j][k] - (i == 0 ? 0 : sum[i - 1][k]) == target)) {
                        count++;
                        k++;
                    }
                    r += count * (count + 1) / 2;
                }
            }
        }
        return r;
    }
}