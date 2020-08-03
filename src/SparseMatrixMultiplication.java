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
        // non null, valid, a col == b row
        Map<Integer, List<Integer>> bm = new HashMap<>();
        for (int j = 0; j < b.length; j++) {
            for (int k = 0; k < b[0].length; k++) {
                if (b[j][k] != 0) {
                    bm.computeIfAbsent(j, key -> new ArrayList<>()).add(k);
                }
            }
        }
        int[][] c = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                List<Integer> klist = bm.getOrDefault(j, new ArrayList<>());
                for (int k : klist) {
                    c[i][k] += a[i][j] * b[j][k];
                }
            }
        }
        return c;
    }
}
