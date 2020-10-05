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
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int[][] opps = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int minPushBox(char[][] a) {
// status: box r, box c, human r, human c,  steps, direction. note direction is used to make sure we can visit a cell from all 4 possible directions
        int[] player = find(a, 'S');
        int[] box = find(a, 'B');
        if (player == null || box == null) {
            return -1;
        }
        int[] status = new int[]{box[0], box[1], player[0], player[1], 0, 4};
        return bfs(a, status);
    }

    private int[] find(char[][] a, char t) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == t) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    private int[] add(int[] p, int[] q) {
        return new int[]{p[0] + q[0], p[1] + q[1]};
    }

    // s must be valid t may not be
    private boolean goable(char[][] a, int[] box, int[] s, int[] t) {
        if (!walkable(a, t[0], t[1])) {
            return false;
        }
        if (s[0] == t[0] && s[1] == t[1]) {
            return true;
        }
        boolean[][] v = new boolean[a.length][a[0].length];
        boolean rt = dfs(a, box, s, t, v);
        return rt;
    }

    // human walk
    private boolean dfs(char[][] a, int[] box, int[] s, int[] t, boolean[][] v) {
        v[s[0]][s[1]] = true;
        if (s[0] == t[0] && s[1] == t[1]) {
            return true;
        }
        for (int[] d : dirs) {
            int ns0 = s[0] + d[0];
            int ns1 = s[1] + d[1];
            int[] np = new int[]{ns0, ns1};
            if (walkable(a, ns0, ns1) && !Arrays.equals(np, box) && !v[ns0][ns1] && dfs(a, box, new int[]{ns0, ns1}, t, v)) {
                return true;

            }
        }
        return false;
    }

    // not checking box location
    private boolean walkable(char[][] a, int i, int j) {
        boolean rt = i >= 0 && i < a.length && j >= 0 && j < a[0].length && a[i][j] != '#';
        return rt;
    }

    private int bfs(char[][] a, int[] st) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(st);
        boolean[][][] v = new boolean[a.length][a[0].length][5];
        v[st[0]][st[1]][st[5]] = true;
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int step = top[4];
            if (a[top[0]][top[1]] == 'T') {
                return step;
            }
            int[] box = new int[]{top[0], top[1]};
            int[] pt = new int[]{top[2], top[3]};
            for (int j = 0; j < 4; j++) {
                int[] pushbox = add(box, opps[j]);
                if (!goable(a, box, pt, pushbox)) {
                    continue;
                }
                int[] nbox = add(box, dirs[j]);
                int[] npt = new int[]{box[0], box[1]};
                if (walkable(a, nbox[0], nbox[1]) && !v[nbox[0]][nbox[1]][j]) {
                    v[nbox[0]][nbox[1]][j] = true;
                    q.offer(new int[]{nbox[0], nbox[1], npt[0], npt[1], step + 1});
                }
            }
        }
        return -1;
    }
}
