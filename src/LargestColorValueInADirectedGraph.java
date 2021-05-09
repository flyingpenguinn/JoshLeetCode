import java.util.ArrayList;
import java.util.List;

public class LargestColorValueInADirectedGraph {
    // don't know how to merge the colors in each i? just put the color we want to trace as j in the dp formula!
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
        }
        int res = 0;
        int[][] dp = new int[n][26];
        for (int j = 0; j < 26; j++) {
            int[] st = new int[n];
            for (int i = 0; i < n; i++) {
                if (st[i] == 0) {
                    boolean cycle = dfs(g, i, j, st, dp, colors);
                    if (cycle) {
                        return -1;
                    }
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }

    private boolean dfs(List<Integer>[] g, int i, int j, int[] st, int[][] dp, String color) {
        st[i] = 1;
        int cc = color.charAt(i) - 'a';
        if (cc == j) {
            dp[i][cc]++;
        }
        int oldj = dp[i][j];
        for (int ne : g[i]) {
            if (st[ne] == 1) {
                return true;
            } else if (st[ne] == 0) {
                boolean lc = dfs(g, ne, j, st, dp, color);
                if (lc) {
                    return true;
                }
            }
            dp[i][j] = Math.max(dp[i][j], oldj + dp[ne][j]);
        }
        st[i] = 2;
        return false;
    }
}
