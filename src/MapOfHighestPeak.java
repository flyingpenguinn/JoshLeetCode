import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MapOfHighestPeak {
    // equivalent to shortest dist to 0, using spfa
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int[][] highestPeak(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] res = new int[m][n];
        Deque<int[]> pq = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            Arrays.fill(res[i], 0);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    pq.offer(new int[]{i, j, 0});
                }
            }
        }
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int i = top[0];
            int j = top[1];
            int h = top[2];
            int nh = h + 1;
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni][nj] == 0 && res[ni][nj] == 0) {
                    res[ni][nj] = nh;
                    pq.offer(new int[]{ni, nj, nh});
                }
            }
        }
        return res;
    }
}
