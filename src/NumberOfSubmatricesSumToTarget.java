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
    private int v(int[][] sum, int i, int j) {
        return i < 0 || j < 0 ? 0 : sum[i][j];
    }

    public int numSubmatrixSumTarget(int[][] a, int target) {
        int m = a.length;
        int n = a[0].length;
        int[][] sum = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i][j] = v(sum, i - 1, j) + a[i][j];
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                Map<Integer, Integer> map = new HashMap<>();
                map.put(0, 1);
                int csum = 0;
                for (int k = 0; k < n; k++) {
                    int cur = v(sum, j, k) - v(sum, i - 1, k);
                    csum += cur;
                    int count = map.getOrDefault(csum - target, 0);
                    res += count;
                    map.put(csum, map.getOrDefault(csum, 0) + 1);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfSubmatricesSumToTarget().numSubmatrixSumTarget(ArrayUtils.read("[[0,1,0],[1,1,1],[0,1,0]]"), 0));
        System.out.println(new NumberOfSubmatricesSumToTarget().numSubmatrixSumTarget(ArrayUtils.read("[[1,-1], [-1,1]]"), 0));
    }
}
