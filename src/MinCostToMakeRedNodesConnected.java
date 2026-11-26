import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 4108. Redo 2026-11-26: tree dp, states 1 = blue (red may live in exactly one child's subtree),
// 2 = red, 3 = whole subtree blue. State 1 = all children blue + best single exceptional child.
public class MinCostToMakeRedNodesConnected {
    private List<Integer>[] t;
    private long[] sub;

    public long minCost(int n, String colors, int[][] edges) {
        t = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            t[i] = new ArrayList<>();
        }
        sub = new long[n];
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            t[v1].add(v2);
            t[v2].add(v1);
        }
        dfs1(0, -1);
        // state = 1: blue, but allow red in sub
        // =2 : red
        // = 3: blue, and dont allow red in sub. i.e. all blue
        dp = new long[n][4];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], -1);
        }
        long way1 = dfs2(colors, 0, 2, -1);
        long way2 = dfs2(colors, 0, 1, -1);
        return Math.min(way1, way2);
    }

    private long[][] dp;

    private long dfs2(String colors, int i, int st, int pa) {
        if (dp[i][st] != -1) {
            return dp[i][st];
        }
        boolean isb = colors.charAt(i) == 'B';
        long cres = 0;
        if (st == 1 || st == 3) {
            if (!isb) {
                cres += sub[i];
            }
        } else if (st == 2) {
            if (isb) {
                cres += sub[i];
            }
        }
        long minben = (long) 1e18;
        long allblue = 0;
        for (int ne : t[i]) {
            if (ne == pa) {
                continue;
            }
            if (st == 1) {
                long way1 = dfs2(colors, ne, 1, i);
                long way2 = dfs2(colors, ne, 2, i);
                long way3 = dfs2(colors, ne, 3, i);
                long cne = Math.min(way1, way2);
                minben = Math.min(minben, cne - way3);
                allblue += way3;
            } else if (st == 2) {
                long way1 = dfs2(colors, ne, 2, i);
                long way2 = dfs2(colors, ne, 3, i);
                cres += Math.min(way1, way2);
            } else {
                cres += dfs2(colors, ne, 3, i);
            }
        }
        if (st == 1) {
            cres += allblue + Math.min(0, minben);
        }
        dp[i][st] = cres;
        return cres;
    }

    private long dfs1(int i, int pa) {
        long cres = 1;
        for (int ne : t[i]) {
            if (pa == ne) {
                continue;
            }
            cres += dfs1(ne, i);
        }
        sub[i] = cres;
        return cres;
    }
}
