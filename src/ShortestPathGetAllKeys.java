import java.util.*;

/*
LC#864
We are given a 2-dimensional grid. "." is an empty cell, "#" is a wall, "@" is the starting point, ("a", "b", ...) are keys, and ("A", "B", ...) are locks.

We start at the starting point, and one move consists of walking one space in one of the 4 cardinal directions.  We cannot walk outside the grid, or walk into a wall.  If we walk over a key, we pick it up.  We can't walk over a lock unless we have the corresponding key.

For some 1 <= K <= 6, there is exactly one lowercase and one uppercase letter of the first K letters of the English alphabet in the grid.  This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.

Return the lowest number of moves to acquire all keys.  If it's impossible, return -1.



Example 1:

Input: ["@.a.#","###.#","b.A.B"]
Output: 8
Example 2:

Input: ["@..aA","..B#.","....b"]
Output: 6


Note:

1 <= grid.length <= 30
1 <= grid[0].length <= 30
grid[i][j] contains only '.', '#', '@', 'a'-'f' and 'A'-'F'
The number of keys is in [1, 6].  Each key has a different letter and opens exactly one lock.
 */
public class ShortestPathGetAllKeys {
    // note this is different from lcp/treasure hunt. in treasure hunt there is no "state" that can block us from going to some node. hence there we can
    // cache the dist and run tsp on treasures
    // same trick can't be applied here because if we dont have the key we can't pass. so this is state on top of bfs problem. no need to enumerate permutation
    // similar to #1293 when it wants shortest path with some optional obstacle we can build the state into bfs states

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int shortestPathAllKeys(String[] a) {
        int m = a.length;
        int n = a[0].length();
        int[] start = null;
        int keys = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i].charAt(j) == '@') {
                    start = new int[]{i, j};
                } else if (Character.isLowerCase(a[i].charAt(j))) {
                    keys++;
                }
            }
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, start[0], start[1], 0});
        boolean[][][] v = new boolean[m][n][1 << keys];
        v[start[0]][start[1]][0] = true;
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int st = top[0];
            int i = top[1];
            int j = top[2];
            int dist = top[3];
            if (st + 1 == (1 << keys)) {
                return dist;
            }
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                int nst = st;
                int ndist = dist + 1;
                if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                    char nc = a[ni].charAt(nj);
                    if (nc == '#') {
                        continue;
                    }
                    if (Character.isUpperCase(nc)) {
                        int ncind = nc - 'A';
                        if (((st >> ncind) & 1) == 0) {
                            continue;
                        }
                    }
                    if (Character.isLowerCase(nc)) {
                        int ncind = nc - 'a';
                        nst = st | (1 << ncind);
                    }
                    if (v[ni][nj][nst]) {
                        continue;
                    }
                    v[ni][nj][nst] = true;
                    q.offer(new int[]{nst, ni, nj, ndist});
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        String[] grid = {"@abcdeABCDEFf"};
        System.out.println(new ShortestPathGetAllKeys().shortestPathAllKeys(grid));
    }
}
