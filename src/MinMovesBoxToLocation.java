import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

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
    // player's positions should also be in the state...
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minPushBox(char[][] g) {
        int rows = g.length;
        int cols = g[0].length;
        int boxrow = -1;
        int boxcol = -1;
        int targetrow = -1;
        int targetcol = -1;
        int prow = -1;
        int pcol = -1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (g[i][j] == 'B') {
                    boxrow = i;
                    boxcol = j;
                    g[i][j] = '.';
                } else if (g[i][j] == 'T') {
                    targetrow = i;
                    targetcol = j;
                    g[i][j] = '.';
                } else if (g[i][j] == 'S') {
                    prow = i;
                    pcol = j;
                    g[i][j] = '.';
                }
            }
        }
        int r = bfs(g, boxrow, boxcol, prow, pcol, targetrow, targetcol);
        return r;
    }

    private int bfs(char[][] g, int si, int sj, int pi, int pj, int ti, int tj) {
        int rows = g.length;
        int cols = g[0].length;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{si, sj, pi, pj, 0});
        Set<Integer> seen = new HashSet<>();
        seen.add(encode(g, si, sj, pi, pj));
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int br = top[0];
            int bc = top[1];
            int pr = top[2];
            int pc = top[3];

            if (br == ti && bc == tj) {
                return top[4];
            }
            for (int[] d : dirs) {
                int nbr = br + d[0];
                int nbc = bc + d[1];
                if (inrange(nbr, nbc, g)) {
                    int pnr = br - (nbr - br);
                    int pnc = bc - (nbc - bc);
                    if (inrange(pnr, pnc, g) && connected(g, pr, pc, pnr, pnc, br, bc)) {
                        int ncode = encode(g, nbr, nbc, br, bc);
                        if (!seen.contains(ncode)) {
                            seen.add(ncode);
                            q.offer(new int[]{nbr, nbc, br, bc, top[4] + 1});
                        }
                    }
                }
            }
        }
        return -1;
    }

    boolean[][] visited;

    private boolean connected(char[][] g, int pi, int pj, int pni, int pnj, int br, int bc) {
        int rows = g.length;
        int cols = g[0].length;
        g[br][bc] = '#';
        visited = new boolean[rows][cols];
        boolean rt = dodfs(g, pi, pj, pni, pnj);
        g[br][bc] = '.';
        return rt;
    }

    private boolean dodfs(char[][] g, int pi, int pj, int pni, int pnj) {
        if (pi == pni && pj == pnj) {
            return true;
        }
        visited[pi][pj] = true;
        for (int[] d : dirs) {
            int ni = pi + d[0];
            int nj = pj + d[1];
            if (inrange(ni, nj, g) && !visited[ni][nj]) {
                boolean rt = dodfs(g, ni, nj, pni, pnj);
                if (rt) {
                    return true;
                }
            }
        }
        return false;
    }

    int encode(char[][] g, int bi, int bj, int pi, int pj) {
        int rows = g.length;
        int cols = g[0].length;
        return bi * rows * rows * rows + bj * rows * rows + pi * rows + pj;
    }

    boolean inrange(int i, int j, char[][] g) {
        int rows = g.length;
        int cols = g[0].length;
        return i >= 0 && i < rows && j >= 0 && j < cols && g[i][j] != '#';
    }
}
