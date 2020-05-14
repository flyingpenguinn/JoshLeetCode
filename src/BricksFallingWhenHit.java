import base.ArrayUtils;
import java.util.*;
/*
LC#803
We have a grid of 1s and 0s; the 1s in a cell represent bricks.  A brick will not drop if and only if it is directly connected to the top of the grid, or at least one of its (4-way) adjacent bricks will not drop.

We will do some erasures sequentially. Each time we want to do the erasure at the location (i, j), the brick (if it exists) on that location will disappear, and then some other bricks may drop because of that erasure.

Return an array representing the number of bricks that will drop after each erasure in sequence.

Example 1:
Input:
grid = [[1,0,0,0],[1,1,1,0]]
hits = [[1,0]]
Output: [2]
Explanation:
If we erase the brick at (1, 0), the brick at (1, 1) and (1, 2) will drop. So we should return 2.
Example 2:
Input:
grid = [[1,0,0,0],[1,1,0,0]]
hits = [[1,1],[1,0]]
Output: [0,0]
Explanation:
When we erase the brick at (1, 0), the brick at (1, 1) has already disappeared due to the last move. So each erasure will cause no bricks dropping.  Note that the erased brick (1, 0) will not be counted as a dropped brick.


Note:

The number of rows and columns in the grid will be in the range [1, 200].
The number of erasures will not exceed the area of the grid.
It is guaranteed that each erasure will be different from any other erasure, and located inside the grid.
An erasure may refer to a location with no brick - if it does, no bricks drop.
 */

public class BricksFallingWhenHit {
    // reverse of LC#305
    // reverse the hits to calculate how many bricks we can merge into "live" part every hit
    // note the traps
    // 1. it can hit no brick point
    // 2. there can be no brick at all after all the hits
    // 3. top bricks are live by themselves while underlings need the top ones to support
    private int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    private boolean inRange(int i, int j, int rowLimit, int colLimit) {
        return i >= 0 && i < rowLimit && j >= 0 && j < colLimit;
    }

    int code(int i, int j, int n) {
        return i * n + j;
    }

    public int[] hitBricks(int[][] g, int[][] hs) {
        int m = g.length;
        int n = g[0].length;
        for (int[] h : hs) {
            g[h[0]][h[1]] = g[h[0]][h[1]] == 1 ? -1 : 0;
        }
        int[] p = new int[m * n];
        int[] size = new int[m * n];
        boolean[] live = new boolean[m * n];
        for (int j = 0; j < n; j++) {
            live[j] = g[0][j] == 1;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] <= 0) {
                    continue;
                }
                int code = code(i, j, n);
                p[code] = code;
                size[code] = 1;
                // only left and up for the initial- not looking for breaking point yet
                for (int k = 0; k < 2; k++) {
                    int ni = i + dirs[k][0];
                    int nj = j + dirs[k][1];
                    if (inRange(ni, nj, m, n) && g[ni][nj] == 1) {
                        union(p, size, live, code, code(ni, nj, n));
                    }
                }
            }
        }
        int hn = hs.length;
        int[] r = new int[hn];
        for (int k = hn - 1; k >= 0; k--) {
            int i = hs[k][0];
            int j = hs[k][1];
            if (g[i][j] == 0) {
                continue;
            }
            g[i][j] = 1;
            int code = code(i, j, n);
            p[code] = code;
            size[code] = 1;
            live[code] = i == 0;
            int ri = live[code] ? 1 : 0;
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                if (inRange(ni, nj, m, n) && g[ni][nj] == 1) {
                    ri += union(p, size, live, code, code(ni, nj, n));
                }
            }
            r[k] = Math.max(ri - 1, 0);// take out the current shot number itself
        }
        return r;
    }

    private int union(int[] p, int[] size, boolean[] live, int c1, int c2) {
        int p1 = find(p, c1);
        int p2 = find(p, c2);
        if (p1 == p2) {
            return 0;
        }
        p[p1] = p2;
        int oldp2 = size[p2];
        size[p2] += size[p1];
        if (live[p2] && !live[p1]) {
            return size[p1];
        } else if (live[p1] && !live[p2]) {
            live[p2] = true;
            return oldp2;
        } else {
            return 0;
        }
    }

    private int find(int[] p, int i) {
        if (p[i] == i) {
            return i;
        }
        int rt = find(p, p[i]);
        p[i] = rt;
        return rt;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new BricksFallingWhenHit().hitBricks(ArrayUtils.read("[[1,1,1],[0,1,0],[0,0,0]]"), ArrayUtils.read("[[0,2],[2,0],[0,1],[1,2]]"))));
        System.out.println(Arrays.toString(new BricksFallingWhenHit().hitBricks(ArrayUtils.read("[[1,0,1],[1,1,1]]"), ArrayUtils.read("[[0,0],[0,2],[1,1]]"))));
        System.out.println(Arrays.toString(new BricksFallingWhenHit().hitBricks(ArrayUtils.read("[[1,0,0,0],[1,1,1,0]]"), ArrayUtils.read("[[1,0]]"))));


        System.out.println(Arrays.toString(new BricksFallingWhenHit().hitBricks(ArrayUtils.read("[[1,0,0,0],[1,1,0,0]]"), ArrayUtils.read("[[1,1],[1,0]]"))));

        System.out.println(Arrays.toString(new BricksFallingWhenHit().hitBricks(ArrayUtils.read("[[1],[1],[1],[1],[1]]"), ArrayUtils.read("[[3,0],[4,0],[1,0],[2,0],[0,0]]"))));
    }
}
