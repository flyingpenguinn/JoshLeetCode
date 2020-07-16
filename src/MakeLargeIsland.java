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
    private int code(int r, int c, int n) {
        return r * n + c;
    }

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int largestIsland(int[][] a) {
        if (a == null || a.length == 0 || a[0].length == 0) {
            return 0;
        }
        int m = a.length;
        int n = a[0].length;
        int[] pa = new int[m * n];
        int[] size = new int[m * n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    int code = code(i, j, n);
                    init(pa, size, code);
                    int merged = 1;
                    if (i - 1 >= 0 && a[i - 1][j] == 1) {
                        merged = union(pa, size, code, code(i - 1, j, n));
                    }
                    if (j - 1 >= 0 && a[i][j - 1] == 1) {
                        merged = union(pa, size, code, code(i, j - 1, n));
                    }
                    max = Math.max(max, merged);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 0) {
                    Set<Integer> comps = new HashSet<>();
                    // note we have to use a set here because the elements can belong to the same component
                    for (int[] d : dirs) {
                        int ni = i + d[0];
                        int nj = j + d[1];
                        if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni][nj] == 1) {
                            comps.add(find(pa, code(ni, nj, n)));
                            //NEVER EVER use pa array directly, always use find
                        }
                    }
                    int res = 1; // count in this turned 0 too!
                    for (int k : comps) {
                        res += size[k];
                    }
                    max = Math.max(max, res);
                }
            }
        }
        return max;
    }

    private void init(int[] pa, int[] size, int k) {
        pa[k] = k;
        size[k] = 1;
    }

    // size of the new merged component
    private int union(int[] pa, int[] size, int k1, int k2) {
        int p1 = find(pa, k1);
        int p2 = find(pa, k2);
        if (p1 != p2) {
            pa[p1] = p2;
            size[p2] += size[p1];
        }
        return size[p2];
    }

    private int find(int[] pa, int k) {
        int p = pa[k];
        if (p == k) {
            return p;
        } else {
            int rt = find(pa, p);
            pa[k] = rt;
            return rt;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MakeLargeIsland().largestIsland(ArrayUtils.read("[[1,0,1],[0,0,0],[0,1,1]]")));
    }
}