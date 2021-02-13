import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestPathInBinaryMatrix {
    // bfs. can be dijkastra, but can't dp
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, 1}, {1, -1}, {1, 1}, {-1, -1}};

    public int shortestPathBinaryMatrix(int[][] a) {
        if (a[0][0] == 1) {
            return -1;
        }
        int m = a.length;
        int n = a[0].length;
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0, 1});
        a[0][0] = 2;
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int i = top[0];
            int j = top[1];
            int cd = top[2];
            if (i == m - 1 && j == n - 1) {
                return cd;
            }
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni][nj] == 0) {
                    a[ni][nj] = 2;
                    int ndist = cd + 1;
                    q.offer(new int[]{ni, nj, ndist});

                }
            }
        }
        return -1;
    }
}
