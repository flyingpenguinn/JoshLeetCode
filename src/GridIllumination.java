import base.ArrayUtils;

import java.util.*;

/*
LC#1001
On a N x N grid of cells, each cell (x, y) with 0 <= x < N and 0 <= y < N has a lamp.

Initially, some number of lamps are on.  lamps[i] tells us the location of the i-th lamp that is on.  Each lamp that is on illuminates every square on its x-axis, y-axis, and both diagonals (similar to a Queen in chess).

For the i-th query queries[i] = (x, y), the answer to the query is 1 if the cell (x, y) is illuminated, else 0.

After each query (x, y) [in the order given by queries], we turn off any lamps that are at cell (x, y) or are adjacent 8-directionally (ie., share a corner or edge with cell (x, y).)

Return an array of answers.  Each value answer[i] should be equal to the answer of the i-th query queries[i].



Example 1:

Input: N = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
Output: [1,0]
Explanation:
Before performing the first query we have both lamps [0,0] and [4,4] on.
The grid representing which cells are lit looks like this, where [0,0] is the top left corner, and [4,4] is the bottom right corner:
1 1 1 1 1
1 1 0 0 1
1 0 1 0 1
1 0 0 1 1
1 1 1 1 1
Then the query at [1, 1] returns 1 because the cell is lit.  After this query, the lamp at [0, 0] turns off, and the grid now looks like this:
1 0 0 0 1
0 1 0 0 1
0 0 1 0 1
0 0 0 1 1
1 1 1 1 1
Before performing the second query we have only the lamp [4,4] on.  Now the query at [1,0] returns 0, because the cell is no longer lit.


Note:

1 <= N <= 10^9
0 <= lamps.length <= 20000
0 <= queries.length <= 20000
lamps[i].length == queries[i].length == 2
Accepted
7,714
Submissions
21,596
 */
public class GridIllumination {
    int[][] ninedirs = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    Map<Integer, Integer> rows = new HashMap<>();
    Map<Integer, Integer> cols = new HashMap<>();
    Map<Integer, Integer> diag = new HashMap<>();
    Map<Integer, Integer> rdiag = new HashMap<>();

    public int[] gridIllumination(int n, int[][] a, int[][] q) {
        Map<Integer, Integer> lamp = new HashMap<>();
        for (int[] ai : a) {
            update(ai, 1);
            lamp.put(ai[0], ai[1]);
        }
        int[] r = new int[q.length];
        int ri = 0;

        for (int[] qi : q) {
            if (rows.containsKey(qi[0])
                    || cols.containsKey(qi[1])
                    || diag.containsKey(qi[0] - qi[1])
                    || rdiag.containsKey(qi[0] + qi[1])) {
                r[ri++] = 1;
            } else {
                r[ri++] = 0;
            }

            for (int[] d : ninedirs) {
                int[] di = new int[]{qi[0] + d[0], qi[1] + d[1]};
                if (di[0] >= 0 && di[0] < n && di[1] >= 0 && di[1] < n && lamp.getOrDefault(di[0], -1) == di[1]) {
                    update(di, -1);
                    lamp.remove(di[0]);
                }
            }
        }
        return r;
    }

    protected void update(int[] ai, int delta) {
        updatemap(rows, ai[0], delta);
        updatemap(cols, ai[1], delta);
        updatemap(diag, ai[0] - ai[1], delta);
        updatemap(rdiag, ai[0] + ai[1], delta);
    }

    void updatemap(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public static void main(String[] args) {
        int[][] lams = ArrayUtils.read("[[0,0],[4,4], [1,2], [2,3]]");
        int[][] queries = ArrayUtils.read("[[1,1],[1,0],[2,0],[2,2],[3,2]]");
        System.out.println(Arrays.toString(new GridIllumination().gridIllumination(5, lams, queries)));
    }
}

