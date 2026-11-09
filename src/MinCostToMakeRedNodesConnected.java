import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MinCostToMakeRedNodesConnected {
   private List<Integer>[] t;
    private long[] subsize;
    private long Max = (long) 1e18;

    public long minCost(int n, String colors, int[][] edges) {
        t = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            t[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            t[v1].add(v2);
            t[v2].add(v1);
        }
        subsize = new long[n];
        dfs1(0, -1);
        long res = Max;
        dp = new long[n][3];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], -1);
        }
        for (int st = 0; st <= 2; ++st) {
            long cres = dfs2(0, st, -1, colors);
            res = Math.min(res, cres);
        }
        return res;
    }


    private long dfs1(int i, int pa) {
        subsize[i] = 1;
        for (int ne : t[i]) {
            if (ne == pa) {
                continue;
            }
            subsize[i] += dfs1(ne, i);
        }
        return subsize[i];
    }

    private long dfs2(int i, int st, int pa, String colors) {
        if(dp[i][st] != -1){
            return dp[i][st];
        }
        long res = 0;
        if (st == 0) {
            long res0 = colors.charAt(i) == 'R' ? subsize[i] : 0;
            for (int ne : t[i]) {
                if (ne == pa) {
                    continue;
                }
                res0 += dfs2(ne, 0, i, colors);
            }
            res = res0;
        } else if (st == 1) {
            long res1 = colors.charAt(i) == 'B' ? subsize[i] : 0;
            for (int ne : t[i]) {
                if (ne == pa) {
                    continue;
                }
                long way1 = dfs2(ne, 0, i, colors);
                long way2 = dfs2(ne, 1, i, colors);
                long cres1 = Math.min(way1, way2);
                res1 += cres1;
            }
            res = res1;
        } else {
            long res2 = colors.charAt(i) == 'R' ? subsize[i] : 0;
            long mininc = Max;
            for (int ne : t[i]) {
                if (ne == pa) {
                    continue;
                }
                long way1 = dfs2(ne, 2, i, colors);
                long way2 = dfs2(ne, 1, i, colors);

                long allblue = dfs2(ne, 0, i, colors);
                long inc = Math.min(way1, way2) - allblue;
                res2 += allblue;
                mininc = Math.min(mininc, inc);
            }
            if (mininc < 0) {
                res2 += mininc;
            }
            res = res2;
        }
        dp[i][st] = res;
        return res;
    }

    private long[][] dp;
}

