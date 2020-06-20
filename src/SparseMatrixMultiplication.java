import java.util.*;

/*
LC#311
Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example:

Input:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]

Output:

     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
 */
public class SparseMatrixMultiplication {
    // aij*bjk so iterate on i,j, and b's row hashmaps. note we dont need a hashmap for a
    public int[][] multiply(int[][] a, int[][] b) {
        Map<Integer, Map<Integer, Integer>> bm = new HashMap<>();

        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                if (b[i][j] != 0) {
                    bm.computeIfAbsent(i, k -> new HashMap<>()).put(j, b[i][j]);
                }
            }
        }

        int[][] c = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                Map<Integer, Integer> bj = bm.getOrDefault(j, new HashMap<>());
                for (int k : bj.keySet()) {
                    c[i][k] += a[i][j] * bj.get(k);
                }
            }
        }
        return c;
    }
}
