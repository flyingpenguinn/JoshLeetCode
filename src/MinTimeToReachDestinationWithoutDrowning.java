import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class MinTimeToReachDestinationWithoutDrowning {
    private int MAX = (int) (1e9 + 7);
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minimumSeconds(List<List<String>> a) {
        int m = a.size();
        int n = a.get(0).size();
        Deque<int[]> q = new ArrayDeque<>();
        int[] s = null;
        int[] dest = null;
        int[][] fg = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(fg[i], MAX);
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                String v = a.get(i).get(j);
                if (v.equals("*")) {
                    q.offer(new int[]{i, j, 0});
                    fg[i][j] = 0;
                } else if (v.equals("S")) {
                    s = new int[]{i, j};
                } else if (v.equals("D")) {
                    dest = new int[]{i, j};
                }
            }
        }

        while (!q.isEmpty()) {
            int[] top = q.poll();
            int r = top[0];
            int c = top[1];
            int cd = top[2];
            int nd = cd + 1;
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && a.get(nr).get(nc).equals(".") && fg[nr][nc] > nd) {
                    q.offer(new int[]{nr, nc, nd});
                    fg[nr][nc] = nd;
                }
            }
        }
        q = new ArrayDeque<>();
        q.offer(new int[]{s[0], s[1], 0});
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dist[i], MAX);
        }
        dist[s[0]][s[1]] = 0;
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int r = top[0];
            int c = top[1];
            int cd = top[2];
            if (r == dest[0] && c == dest[1]) {
                return cd;
            }
            int nd = cd + 1;
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0
                        && nr < m
                        && nc >= 0
                        && nc < n
                        && !a.get(nr).get(nc).equals("X") && dist[nr][nc] > nd && fg[nr][nc] > nd) {
                    dist[nr][nc] = nd;
                    q.offer(new int[]{nr, nc, nd});

                }
            }
        }
        return -1;
    }
}
