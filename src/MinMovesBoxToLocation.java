import java.util.*;

/*
LC#1263
Storekeeper is a game in which the player pushes boxes around in a warehouse trying to get them to target locations.

The game is represented by a grid of size n*m, where each element is a wall, floor, or a box.

Your task is move the box 'B' to the target position 'T' under the following rules:

Player is represented by character 'S' and can move up, down, left, right in the grid if it is a floor (empy cell).
Floor is represented by character '.' that means free cell to walk.
Wall is represented by character '#' that means obstacle  (impossible to walk there).
There is only one box 'B' and one target cell 'T' in the grid.
The box can be moved to an adjacent free cell by standing next to the box and then moving in the direction of the box. This is a push.
The player cannot walk through the box.
Return the minimum number of pushes to move the box to the target. If there is no way to reach the target, return -1.



Example 1:



Input: grid = [["#","#","#","#","#","#"],
               ["#","T","#","#","#","#"],
               ["#",".",".","B",".","#"],
               ["#",".","#","#",".","#"],
               ["#",".",".",".","S","#"],
               ["#","#","#","#","#","#"]]
Output: 3
Explanation: We return only the number of times the box is pushed.
Example 2:

Input: grid = [["#","#","#","#","#","#"],
               ["#","T","#","#","#","#"],
               ["#",".",".","B",".","#"],
               ["#","#","#","#",".","#"],
               ["#",".",".",".","S","#"],
               ["#","#","#","#","#","#"]]
Output: -1
Example 3:

Input: grid = [["#","#","#","#","#","#"],
               ["#","T",".",".","#","#"],
               ["#",".","#","B",".","#"],
               ["#",".",".",".",".","#"],
               ["#",".",".",".","S","#"],
               ["#","#","#","#","#","#"]]
Output: 5
Explanation:  push the box down, left, left, up and up.
Example 4:

Input: grid = [["#","#","#","#","#","#","#"],
               ["#","S","#",".","B","T","#"],
               ["#","#","#","#","#","#","#"]]
Output: -1


Constraints:

1 <= grid.length <= 20
1 <= grid[i].length <= 20
grid contains only characters '.', '#',  'S' , 'T', or 'B'.
There is only one character 'S', 'B' and 'T' in the grid.
 */
public class MinMovesBoxToLocation {
    // bfs the box, then for human use dfs to check if can reach the dedicated side...
    // player's positions should also be in the state...we may push box from one direction, then walk over, and then go from the other direction
    // in seen array we record the box position and the direction we pushed so that next time we won't try the same position again. dont need to record the human position
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int[][] rdirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int minPushBox(char[][] a) {
        int m = a.length;
        int n = a[0].length;
        Deque<int[]> q = new ArrayDeque<>();
        // box position, player position
        int[] bp = null;
        int[] sp = null;
        int[] tp = null;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 'T') {
                    tp = new int[]{i, j};
                    a[i][j] = '.';
                } else if (a[i][j] == 'B') {
                    bp = new int[]{i, j};
                    a[i][j] = '.';
                } else if (a[i][j] == 'S') {
                    sp = new int[]{i, j};
                    a[i][j] = '.';
                }
            }
        }
        // bp, player pos, and steps so far
        q.offer(new int[]{bp[0], bp[1], sp[0], sp[1], 0});
        boolean[][][] seen = new boolean[m][n][4];
        // box locations and its push direction
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int bi = top[0];
            int bj = top[1];
            int si = top[2];
            int sj = top[3];
            int steps = top[4];
            if (bi == tp[0] && bj == tp[1]) {
                return steps;
            }
            for (int k = 0; k < dirs.length; k++) {
                int[] d = dirs[k];
                int[] rd = rdirs[k];
                int nbi = bi + d[0];
                int nbj = bj + d[1];
                if (!valid(a, nbi, nbj, m, n)) {
                    continue;
                }
                int nsi = bi + rd[0];
                int nsj = bj + rd[1];
                if (!valid(a, nsi, nsj, m, n)) {
                    continue;
                }
                a[bi][bj] = '#'; // box not crossable
                boolean[][] v = new boolean[m][n];
                boolean doable = dfs(a, si, sj, nsi, nsj, v);
                a[bi][bj] = '.';
                if (doable) {
                    if (seen[nbi][nbj][k]) {
                        continue;
                    }
                    seen[nbi][nbj][k] = true;
                    //human took over box position
                    q.offer(new int[]{nbi, nbj, bi, bj, steps + 1});
                }
            }
        }
        return -1;
    }

    private boolean valid(char[][] a, int i, int j, int m, int n) {
        return i >= 0 && i < m && j >= 0 && j < n && a[i][j] != '#';
    }

    // we can never ever set a[si][sj] to # to mimic dfs. if we do that we may revisit cells we previously visited from another direction
    private boolean dfs(char[][] a, int si, int sj, int ti, int tj, boolean[][] v) {
        if (si == ti && sj == tj) {
            return true;
        }
        int m = a.length;
        int n = a[0].length;
        v[si][sj] = true;
        boolean good = false;
        for (int[] d : dirs) {
            int nsi = si + d[0];
            int nsj = sj + d[1];
            if (valid(a, nsi, nsj, m, n) && !v[nsi][nsj] && dfs(a, nsi, nsj, ti, tj, v)) {
                good = true;
                break;
            }
        }
        return good;
    }
}
