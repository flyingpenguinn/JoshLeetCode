import base.ArrayUtils;

import java.util.*;

/*
LC#827
In a 2D grid of 0s and 1s, we change at most one 0 to a 1.

After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).

Example 1:

Input: [[1, 0], [0, 1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
Example 2:

Input: [[1, 1], [1, 0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
Example 3:

Input: [[1, 1], [1, 1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.


Notes:

1 <= grid.length = grid[0].length <= 50.
0 <= grid[i][j] <= 1.
 */
public class MakeLargeIsland {
    // union find, count each segment we see on a 0 only once
    Map<Integer, Integer> pa = new HashMap<>();
    Map<Integer, Integer> size = new HashMap<>();

    public int largestIsland(int[][] a) {
        int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        int m = a.length;
        int n = a[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    init(code(n, i, j));
                    for (int k = 0; k < 2; k++) {
                        int[] d = dirs[k];
                        int ni = i + d[0];
                        int nj = j + d[1];
                        if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni][nj] == 1) {

                            union(code(n, i, j), code(n, ni, nj));
                        }
                    }
                }
            }
        }
        int max = 0;
        for (int k : pa.keySet()) {
            if (pa.get(k) == k) {
                max = Math.max(max, size.get(k));
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 0) {
                    int link = 1;
                    Set<Integer> seen = new HashSet<>();
                    for (int[] d : dirs) {
                        int ni = i + d[0];
                        int nj = j + d[1];
                        if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni][nj] == 1) {
                            int parent = find(code(n, ni, nj));
                            if (!seen.contains(parent)) {
                                link += size.get(parent);
                                seen.add(parent);
                            }
                        }
                    }
                    max = Math.max(max, link);
                }
            }
        }
        return max;
    }

    int code(int n, int i, int j) {
        return n * i + j;
    }

    void init(int code) {
        pa.put(code, code);
        size.put(code, 1);
    }

    int find(int code) {
        int parent = pa.get(code);
        if (parent == code) {
            return code;
        } else {
            int rt = find(parent);
            pa.put(code, rt);
            return rt;
        }
    }

    void union(int n1, int n2) {
        int pra = find(n1);
        int prb = find(n2);
        if (pra != prb) {
            int sizea = size.get(pra);
            int sizeb = size.get(prb);
            pa.put(pra, prb);
            size.put(prb, sizeb + sizea);
        }
    }
}