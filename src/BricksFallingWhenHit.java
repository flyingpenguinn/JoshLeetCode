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
    // 2. top bricks are live by themselves while underlings need the top ones to support. so if we are merging two top linked areas dont count new live ones
    // 3. mind the logic of calculating newly added live bricks: we need to -1 when it's >0 but keep it on 0 when there is no new live block
    Map<Integer, Integer> pa = new HashMap<>();
    Map<Integer, Integer> size = new HashMap<>();
    int m = 0;
    int n = 0;

    public int[] hitBricks(int[][] a, int[][] h) {
        m = a.length;
        n = a[0].length;
        int[][] olda = new int[m][n];
        for (int i = 0; i < m; i++) {
            olda[i] = Arrays.copyOf(a[i], n);
        }
        for (int[] hi : h) {
            a[hi[0]][hi[1]] = 0;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    init(tocode(i, j));
                    if (i - 1 >= 0 && a[i - 1][j] == 1) {
                        union(i, j, i - 1, j);
                    }
                    if (j - 1 >= 0 && a[i][j - 1] == 1) {
                        union(i, j, i, j - 1);
                    }
                }
            }
        }
        int[] r = new int[h.length];
        for (int i = h.length - 1; i >= 0; i--) {
            int[] pos = h[i];
            if (olda[pos[0]][pos[1]] == 0) {
                continue;
            }
            int poscode = tocode(pos[0], pos[1]);
            init(poscode);
            a[pos[0]][pos[1]] = 1;
            int cur = poscode < n ? 1 : 0; // how many top going nodes were added in this round
            for (int[] d : dirs) {
                int ni = pos[0] + d[0];
                int nj = pos[1] + d[1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni][nj] == 1) {
                    int unioned = union(pos[0], pos[1], ni, nj);
                    cur += unioned;
                }
            }
            r[i] = cur > 0 ? cur - 1 : 0; // if found top going ones then it's because this brick is top going so need to subtract that. otherwise 0
        }
        return r;
    }

    protected void init(int co) {
        pa.put(co, co);
        size.put(co, 1);
    }

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // how many are linked to a set that belongs to the top level
    private int union(int i, int j, int ni, int nj) {
        int p1 = find(tocode(i, j));
        int p2 = find(tocode(ni, nj));
        if (p1 == p2) {
            return 0;
        } else {
            // bigger one goes to the set of smaller one
            int min = Math.min(p1, p2);
            int max = Math.max(p1, p2);
            pa.put(max, min);
            int maxsize = size.get(max);
            size.put(min, maxsize + size.get(min));
            if (min < n && max >= n) {
                // how many new top going are we adding. note if max also <n there is no addition of top going bricks
                return maxsize;
            } else {
                return 0;
            }
        }
    }

    private int find(int co) {
        int father = pa.get(co);
        if (father == co) {
            return co;
        } else {
            int rt = find(father);
            pa.put(co, rt);
            return rt;
        }
    }

    int tocode(int i, int j) {
        return i * n + j;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new BricksFallingWhenHit().hitBricks(ArrayUtils.read("[[1,1,1],[0,1,0],[0,0,0]]"), ArrayUtils.read("[[0,2],[2,0],[0,1],[1,2]]"))));
        System.out.println(Arrays.toString(new BricksFallingWhenHit().hitBricks(ArrayUtils.read("[[1,0,1],[1,1,1]]"), ArrayUtils.read("[[0,0],[0,2],[1,1]]"))));
        System.out.println(Arrays.toString(new BricksFallingWhenHit().hitBricks(ArrayUtils.read("[[1,0,0,0],[1,1,1,0]]"), ArrayUtils.read("[[1,0]]"))));


        System.out.println(Arrays.toString(new BricksFallingWhenHit().hitBricks(ArrayUtils.read("[[1,0,0,0],[1,1,0,0]]"), ArrayUtils.read("[[1,1],[1,0]]"))));

        System.out.println(Arrays.toString(new BricksFallingWhenHit().hitBricks(ArrayUtils.read("[[1],[1],[1],[1],[1]]"), ArrayUtils.read("[[3,0],[4,0],[1,0],[2,0],[0,0]]"))));
    }
}
