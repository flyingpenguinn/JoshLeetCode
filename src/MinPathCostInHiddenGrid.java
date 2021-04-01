import java.util.Arrays;
import java.util.PriorityQueue;

public class MinPathCostInHiddenGrid {
    interface GridMaster {
        boolean canMove(char direction);

        int move(char direction);

        boolean isTarget();
    }

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private char[] sdirs = {'U', 'D', 'L', 'R'};
    private char[] rdirs = {'D', 'U', 'R', 'L'};
    private int size = 300;
    private int start = 101;
    private int tr = -1;
    private int tc = -1;
    private int Max = 10000000;

    public int findShortestPath(GridMaster master) {
        int[][] g = new int[size][size];
        dfs(g, start, start, master);
        // r, c, cost
        //  System.out.println(g[101][101]+" "+g[99][101]+" "+" "+tr+" "+tc);
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));
        pq.offer(new int[]{start, start, 0});
        int[][] dists = new int[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(dists[i], Max);
        }
        boolean[][] done = new boolean[size][size];
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            //   System.out.println(Arrays.toString(top));
            int i = top[0];
            int j = top[1];
            if (done[i][j]) {
                continue;
            }
            done[i][j] = true;
            int dist = top[2];
            if (tr == i && tc == j) {
                return dist;
            }
            for (int k = 0; k < dirs.length; k++) {
                int[] d = dirs[k];
                int ni = i + d[0];
                int nj = j + d[1];
                if (g[ni][nj] > 0 && dists[ni][nj] > dist + g[ni][nj]) {
                    dists[ni][nj] = dist + g[ni][nj];
                    pq.offer(new int[]{ni, nj, dist + g[ni][nj]});
                }
            }
        }
        return -1;
    }

    private void dfs(int[][] g, int r, int c, GridMaster master) {
        if (master.isTarget()) {
            tr = r;
            tc = c;
        }
        for (int k = 0; k < dirs.length; k++) {
            int[] d = dirs[k];
            int nr = r + d[0];
            int nc = c + d[1];
            if (master.canMove(sdirs[k]) && g[nr][nc] == 0) {
                g[nr][nc] = master.move(sdirs[k]);
                dfs(g, nr, nc, master);
                master.move(rdirs[k]);
                // make sure we move back as part of the backtrack
            }
        }
    }

}
