import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestPathToGetFood {
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int getFood(char[][] g) {
        Deque<int[]> q = new ArrayDeque<>();
        int m = g.length;
        int n = g[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == '*') {
                    g[i][j] = 'X';
                    q.offer(new int[]{i, j, 0});
                    break;
                }
            }
        }
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int i = top[0];
            int j = top[1];
            int ndist = top[2] + 1;
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && g[ni][nj] != 'X') {
                    if (g[ni][nj] == '#') {
                        return ndist;
                    }
                    g[ni][nj] = 'X';
                    q.offer(new int[]{ni, nj, ndist});
                }
            }
        }
        return -1;
    }
}
