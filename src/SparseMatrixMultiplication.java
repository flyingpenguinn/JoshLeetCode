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
    // aij*bjm so iterate on i,j, and b's row hashmaps. note we dont need a hashmap for a
    public int[][] multiply(int[][] a, int[][] b) {
        Map<Integer, Set<Integer>> mb = new HashMap<>();
        int ra = a.length;
        int ca = a[0].length;
        int rb = ca;
        int cb = b[0].length;
        int[][] r = new int[ra][cb];
        for (int i = 0; i < rb; i++) {
            for (int j = 0; j < cb; j++) {
                if (b[i][j] != 0) {
                    mb.computeIfAbsent(i, k -> new HashSet<>()).add(j);
                }
            }
        }
        for (int i = 0; i < ra; i++) {
            for (int j = 0; j < ca; j++) {
                if (a[i][j] != 0) {
                    for (int cbi : mb.getOrDefault(j, new HashSet<>())) {
                        int rbi = j;
                        r[i][cbi] += a[i][j] * b[rbi][cbi];
                    }
                }
            }
        }
        return r;
    }
}
