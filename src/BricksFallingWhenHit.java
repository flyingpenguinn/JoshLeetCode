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
    // 2. top bricks are live by themselves while underlings need the top ones to support.
    // 3. mind the logic of calculating newly added live bricks: we need to -1 when hit brick is involvd
    private int rows = 0;
    private int cols = 0;
    private int[] pa;
    private int[] size;
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private boolean[] live;

    public int[] hitBricks(int[][] g, int[][] h) {
        this.rows = g.length;
        this.cols = g[0].length;
        pa = new int[rows * cols];
        size = new int[rows * cols];
        for (int i = 0; i < pa.length; i++) {
            pa[i] = i;
            size[i] = 1;
        }
        boolean[] flipped = new boolean[rows * cols];
        for (int i = 0; i < h.length; i++) {
            int hr = h[i][0];
            int hc = h[i][1];
            int hcode = code(hr, hc);
            if (g[hr][hc] == 1) {
                flipped[hcode] = true;
                g[hr][hc] = 0;
            }
        }
        live = new boolean[rows * cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (g[i][j] == 0) {
                    continue;
                }
                if (i == 0 && g[i][j] == 1) {
                    live[find(code(0, j))] = true;
                }
                for (int[] d : dirs) {
                    int ni = i + d[0];
                    int nj = j + d[1];
                    if (good(ni, nj) && g[ni][nj] == 1) {
                        union(code(i, j), code(ni, nj));
                    }
                }
            }
        }
        int[] res = new int[h.length];
        int ri = h.length - 1;
        for (int i = h.length - 1; i >= 0; i--) {
            int hr = h[i][0];
            int hc = h[i][1];
            int hcode = find(code(hr, hc));
            if (!flipped[hcode]) {
                res[ri--] = 0;
                continue;
            }
            int cur = 0;
            if (hr == 0) {
                // first line is born live
                live[hcode] = true;
            }
            g[hr][hc] = 1;
            for (int[] d : dirs) {
                int nr = hr + d[0];
                int nc = hc + d[1];
                if (!good(nr, nc)) {
                    continue;
                }
                if (g[nr][nc] == 0) {
                    continue;
                }
                int ncode = find(code(nr, nc));
                hcode = find(code(hr, hc));
                if (!live[hcode] && live[ncode]) {
                    int oldsize = size[hcode];
                    union(ncode, hcode);
                    cur += oldsize - 1; // count out hcode itself
                } else if (!live[ncode] && live[hcode]) {
                    int oldsize = size[ncode];
                    union(hcode, ncode);
                    cur += oldsize;
                } else if (g[nr][nc] == 1) {
                    union(hcode, ncode);
// note we can also link non live ones to be a one block and use it later
                }
            }
            res[ri--] = cur;
        }
        return res;
    }


    private int find(int code) {
        if (pa[code] == code) {
            return code;
        }
        int rt = find(pa[code]);
        pa[code] = rt;
        return rt;
    }

    private boolean union(int c1, int c2) {
        int p1 = find(c1);
        int p2 = find(c2);
        if (p1 == p2) {
            return false;
        }
        pa[p1] = p2;
        size[p2] += size[p1];
        if (live[p2]) {
            live[p1] = true;
        }
        if (live[p1]) {
            live[p2] = true;
        }
        return true;
    }

    private int code(int r, int c) {
        return r * cols + c;
    }

    private boolean good(int i, int j) {
        return i >= 0 && i < rows && j >= 0 && j < cols;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new BricksFallingWhenHit().hitBricks(ArrayUtils.read("[[1,1,1],[0,1,0],[0,0,0]]"), ArrayUtils.read("[[0,2],[2,0],[0,1],[1,2]]"))));
        System.out.println(Arrays.toString(new BricksFallingWhenHit().hitBricks(ArrayUtils.read("[[1,0,1],[1,1,1]]"), ArrayUtils.read("[[0,0],[0,2],[1,1]]"))));
        System.out.println(Arrays.toString(new BricksFallingWhenHit().hitBricks(ArrayUtils.read("[[1,0,0,0],[1,1,1,0]]"), ArrayUtils.read("[[1,0]]"))));


        System.out.println(Arrays.toString(new BricksFallingWhenHit().hitBricks(ArrayUtils.read("[[1,0,0,0],[1,1,0,0]]"), ArrayUtils.read("[[1,1],[1,0]]"))));

        System.out.println(Arrays.toString(new BricksFallingWhenHit().hitBricks(ArrayUtils.read("[[1],[1],[1],[1],[1]]"), ArrayUtils.read("[[3,0],[4,0],[1,0],[2,0],[0,0]]"))));
    }
}
