import base.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

/*
LC#1074
Given a matrix, and a target, return the number of non-empty submatrices that sum to target.

A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.

Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.



Example 1:

Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
Output: 4
Explanation: The four 1x1 submatrices that only contain 0.
Example 2:

Input: matrix = [[1,-1],[-1,1]], target = 0
Output: 5
Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.


Note:

1 <= matrix.length <= 300
1 <= matrix[0].length <= 300
-1000 <= matrix[i] <= 1000
-10^8 <= target <= 10^8
 */
public class NumberOfSubmatricesSumToTarget {
    // O(n3) worst case. this is one of the most frequently used ways to treat matrix!
    // similar to LC#363, and they are both similar to 2d max subarray sum problem
    public int numSubmatrixSumTarget(int[][] a, int t) {
        int m = a.length;
        int n = a[0].length;
        int[][] p = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                p[i][j] = i == 0 ? a[i][j] : p[i - 1][j] + a[i][j];
            }
        }
        int r = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                int psum = 0;
                Map<Integer, Integer> map = new HashMap<>();
                for (int k = 0; k < n; k++) {
                    int cur = i == 0 ? p[j][k] : p[j][k] - p[i - 1][k];
                    psum = k == 0 ? cur : psum + cur;
                    if (psum == t) {
                        r++;
                    }
                    int target = psum - t;
                    int count = map.getOrDefault(target, 0);
                    r += count;
                    map.put(psum, map.getOrDefault(psum, 0) + 1);
                }

            }
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfSubmatricesSumToTarget().numSubmatrixSumTarget(ArrayUtils.read("[[0,1,0],[1,1,1],[0,1,0]]"), 0));
        System.out.println(new NumberOfSubmatricesSumToTarget().numSubmatrixSumTarget(ArrayUtils.read("[[1,-1], [-1,1]]"), 0));
    }
}
