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
        // check null, error out if so
        int ma = a.length;
        int na = a[0].length;
        int mb = b.length; // na == mb
        int nb = b[0].length;

        int[][] c = new int[ma][nb];
        Map<Integer, List<Integer>> bmap = new HashMap<>();
        for (int j = 0; j < mb; j++) {
            for (int k = 0; k < nb; k++) {
                if (b[j][k] != 0) {
                    bmap.computeIfAbsent(j, key -> new ArrayList<>()).add(k);
                }
            }
        }
        for (int i = 0; i < ma; i++) {
            for (int j = 0; j < na; j++) {
                if (a[i][j] != 0) {
                    List<Integer> toadd = bmap.getOrDefault(j, new ArrayList<>());
                    for (int k : toadd) {
                        c[i][k] += a[i][j] * b[j][k];
                    }
                }
            }
        }
        return c;
    }
}
