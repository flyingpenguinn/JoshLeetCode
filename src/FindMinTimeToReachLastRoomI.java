import java.util.Arrays;
import java.util.PriorityQueue;

public class FindMinTimeToReachLastRoomI {
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int Max = (int) 2e9;

    public int minTimeToReach(int[][] g) {
        int m = g.length;
        int n = g[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dist[i], Max);
        }
        dist[0][0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));
        pq.offer(new int[]{0, 0, 0});
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int r = top[0];
            int c = top[1];
            int cd = top[2];
            if (r == m - 1 && c == n - 1) {
                return cd;
            }
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && dist[nr][nc] == Max) {
                    int nd = Math.max(cd + 1, g[nr][nc] + 1);
                    dist[nr][nc] = nd;
                    pq.offer(new int[]{nr, nc, nd});
                }
            }
        }
        return -1;
    }
}
