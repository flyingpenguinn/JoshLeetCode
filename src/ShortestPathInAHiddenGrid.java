import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class ShortestPathInAHiddenGrid {
    interface GridMaster {
        public boolean canMove(char direction);

        public void move(char direction);

        public boolean isTarget();
    }


    // use dfs to build the graph then run bfs on it...
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private char[] sdirs = {'U', 'D', 'L', 'R'};
    private char[] rdirs = {'D', 'U', 'R', 'L'};
    private int[][] g = new int[1010][1010];
    private int[] target = null;

    public int findShortestPath(GridMaster master) {
        g[501][501] = -1;
        dfs(master, 501, 501);
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

    // almost same as minpathcostinhiddengrid...
    private void dfs(GridMaster master, int r, int c) {
        //    System.out.println(g[r][c]+" "+r+" "+c);
        if (master.isTarget()) {
            target = new int[]{r, c};
            g[r][c] = 2;
        }
        for (int k = 0; k < dirs.length; k++) {
            int[] d = dirs[k];
            int nr = r + d[0];
            int nc = c + d[1];
            if (master.canMove(sdirs[k]) && g[nr][nc] == 0) {
                g[nr][nc] = 1;
                master.move(sdirs[k]);
                dfs(master, nr, nc);
                master.move(rdirs[k]);
                // make sure we move back
            }
        }
    }
}
