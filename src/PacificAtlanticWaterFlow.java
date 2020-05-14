import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
