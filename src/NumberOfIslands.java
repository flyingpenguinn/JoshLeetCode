import base.ArrayUtils;

/*
LC#200
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3
 */
public class NumberOfIslands {
    // using union find, just need to check left and upper
    int r = 0;

    public int numIslands(char[][] g) {
        int m = g.length;
        if (m == 0) {
            return 0;
        }
        int n = g[0].length;

        int[] p = new int[m * n];
        int[] sz = new int[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int ind = i * n + j;
                if (g[i][j] == '0') {
                    continue;
                }
                make(p, sz, ind);

                if (i > 0 && g[i - 1][j] == '1') {
                    int upind = (i - 1) * n + j;
                    union(p, sz, ind, upind);
                }
                if (j > 0 && g[i][j - 1] == '1') {
                    int lind = i * n + j - 1;
                    union(p, sz, ind, lind);
                }
            }
        }
        return r;
    }

    void make(int[] p, int[] sz, int i) {
        p[i] = -1;
        sz[i] = 1;
        r++;
    }

    int find(int[] p, int i) {
        if (p[i] == -1) {
            return i;
        }
        int rt = find(p, p[i]);
        p[i] = rt;
        return rt;
    }

    void union(int[] p, int[] sz, int i, int j) {
        int pi = find(p, i);
        int pj = find(p, j);
        if (pi == pj) {
            return;
        }
        int t = pi;
        int m = pj;
        if (sz[pi] < sz[pj]) {
            t = pj;
            m = pi;
        }
        p[m] = t;
        sz[t] += sz[m];
        r--;
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfIslands().numIslands(ArrayUtils.readAsChar("[[1,1,1,1,1,1,1],[0,0,0,0,0,0,1],[1,1,1,1,1,0,1],[1,0,0,0,1,0,1],[1,0,1,0,1,0,1],[1,0,1,1,1,0,1],[1,1,1,1,1,1,1]]")));
    }
}
