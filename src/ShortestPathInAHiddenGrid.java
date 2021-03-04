import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class ShortestPathInAHiddenGrid {
    interface gridMaster {
        public boolean canMove(char direction);

        public void move(char direction);

        public boolean isTarget();
    }


    // use dfs to build the graph then run bfs on it...
    private int[][] dirs = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
    private char[] dtexts = {'R', 'D', 'L', 'U'};
    private Set<Integer> seen = new HashSet<>();
    private int[] target = null;
    private int[][] g = new int[1010][1010];

    public int findShortestPath(gridMaster master) {
        g[501][501] = -1;
        dfs(master, 501, 501, -1);
        if (target == null) {
            return -1;
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{501, 501, 0});
        g[501][501] = 0;
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int i = top[0];
            int j = top[1];
            int dist = top[2];
            if (i == target[0] && j == target[1]) {
                return dist;
            }
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                if (g[ni][nj] != 0) {
                    g[ni][nj] = 0;
                    q.offer(new int[]{ni, nj, dist + 1});
                }
            }
        }
        return -1;
    }

    private void dfs(gridMaster master, int i, int j, int reverse) {
        int code = code(i, j);
        g[i][j] = 1;
        seen.add(code);
        if (master.isTarget()) {
            g[i][j] = 2;
            target = new int[]{i, j};
            master.move(dtexts[reverse]);
            return;
        }
        for (int k = 0; k < dirs.length; k++) {
            int ni = i + dirs[k][0];
            int nj = j + dirs[k][1];
            int ncode = code(ni, nj);
            char text = dtexts[k];
            if (!master.canMove(text)) {
                continue;
            }
            if (!seen.contains(ncode)) {
                master.move(text);
                dfs(master, ni, nj, (k + 2) % 4);
            }
        }
        if (reverse != -1) {
            master.move(dtexts[reverse]);
        }
    }

    private int code(int i, int j) {
        return i * 10000 + j;
    }
}
