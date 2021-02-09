import java.util.*;

public class NumberOfDistinctIslands {
    // encode each island according to their top left as "points"
    // we can use a set of list because if islands are the same the sequence from top left to all others must be the same, we visit in the same order
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int numDistinctIslands(int[][] a) {
        Set<List<Integer>> comps = new HashSet<>();
        int m = a.length;
        int n = a[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    List<Integer> comp = new ArrayList<>();
                    dfs(a, i, j, comp, i, j);
                    comps.add(comp);
                }
            }
        }
        return comps.size();
    }

    private void dfs(int[][] a, int i, int j, List<Integer> comp, int ui, int uj) {
        int m = a.length;
        int n = a[0].length;
        a[i][j] = 0;
        int di = i - ui;
        int dj = j - uj;
        comp.add(di * n + dj);
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni >= 0 && ni < m && nj >= 0 && nj < n && a[ni][nj] == 1) {
                dfs(a, ni, nj, comp, ui, uj);
            }
        }
    }
}
