import java.util.*;


public class PacificAtlanticWaterFlow {
    // dfs from ocean. note we need to revert the direction in this way
    // we can also bfs
    private int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public List<List<Integer>> pacificAtlantic(int[][] a) {
        int m = a.length;
        List<List<Integer>> r = new ArrayList<>();
        if (m == 0) {
            return r;
        }
        int n = a[0].length;
        int[][] v = new int[m][n];
        for (int j = 0; j < n; j++) {
            dfs(a, 0, j, v, 0);
        }
        for (int i = 0; i < m; i++) {
            dfs(a, i, 0, v, 0);
        }
        for (int j = 0; j < n; j++) {
            dfs(a, m - 1, j, v, 1);
        }
        for (int i = 0; i < m; i++) {
            dfs(a, i, n - 1, v, 1);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (v[i][j] == 3) {
                    List<Integer> ri = new ArrayList<>();
                    ri.add(i);
                    ri.add(j);
                    r.add(ri);
                }
            }
        }
        return r;
    }

    private void dfs(int[][] a, int i, int j, int[][] v, int offset) {
        v[i][j] = v[i][j] | (1 << offset);
        for (int[] d : directions) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < a.length && nj >= 0 && nj < a[0].length && a[ni][nj] >= a[i][j] && ((v[ni][nj] >> offset) & 1) == 0) {
                dfs(a, ni, nj, v, offset);
            }
        }
    }
}


class PacificAtlanticBfs {
    // not using a counter: the cells on the corner are hard to deal with
    public List<List<Integer>> pacificAtlantic(int[][] a) {
        int m = a.length;
        List<List<Integer>> res = new ArrayList<>();
        if (m == 0) {
            return res;
        }

        int n = a[0].length;
        Deque<int[]> dq = new ArrayDeque<>();

        boolean[][] v1 = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dq.offer(new int[]{i, 0});
            v1[i][0] = true;
        }
        for (int j = 0; j < n; j++) {
            dq.offer(new int[]{0, j});
            v1[0][j] = true;
        }
        bfs(dq, a, v1);
        dq.clear();
        boolean[][] v2 = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dq.offer(new int[]{i, n - 1});
            v2[i][n - 1] = true;
        }
        for (int j = 0; j < n; j++) {
            dq.offer(new int[]{m - 1, j});
            v2[m - 1][j] = true;
        }
        bfs(dq, a, v2);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (v1[i][j] && v2[i][j]) {
                    res.add(List.of(i, j));
                }
            }
        }
        return res;
    }

    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private void bfs(Deque<int[]> dq, int[][] a, boolean[][] v) {
        int m = a.length;
        int n = a[0].length;
        while (!dq.isEmpty()) {
            int[] top = dq.poll();
            int i = top[0];
            int j = top[1];
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                    if (a[i][j] <= a[ni][nj] && !v[ni][nj]) {
                        //    System.out.println(i+","+j+"->"+ni+","+nj);
                        v[ni][nj] = true;
                        dq.offer(new int[]{ni, nj});
                    }
                }
            }
        }
    }
}