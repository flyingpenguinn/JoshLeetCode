import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestPalinPathInGraph {
    private Map<Integer, Set<Integer>> g = new HashMap<>();
    private char[] ch;
    private Integer[][][] dp;

    public int maxLen(int n, int[][] edges, String label) {
        ch = label.toCharArray();
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            g.computeIfAbsent(u, p -> new HashSet<>()).add(v);
            g.computeIfAbsent(v, p -> new HashSet<>()).add(u);
        }
        int res = n > 0 ? 1 : 0;
        dp = new Integer[(1 << n)][n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (ch[i] != ch[j]) {
                    continue;
                }
                if (i == j) {
                    continue;
                }
                int cst = 0;
                cst |= (1 << i);
                cst |= (1 << j);
                int later = solve(n, cst, i, j);
                if (later == 0) {
                    if (g.getOrDefault(i, new HashSet<>()).contains(j)) {
                        res = Math.max(res, 2);
                    }
                } else {
                    res = Math.max(res, 2 + later);
                }
            }
        }
        return res;
    }

    private int solve(int n, int st, int i, int j) {
        //   System.out.println("st="+st+" i="+i+" j="+j);
        if (st + 1 == (1 << n)) {
            return 0;
        }
        if (dp[st][i][j] != null) {
            return dp[st][i][j];
        }
        int res = -100;
        if (g.getOrDefault(i, new HashSet<>()).contains(j)) {
            res = 0;
        }
        for (int k : g.getOrDefault(i, new HashSet<>())) {
            for (int p : g.getOrDefault(j, new HashSet<>())) {
                if (((st >> p) & 1) == 1) {
                    continue;
                }
                if (((st >> k) & 1) == 1) {
                    continue;
                }
                //  System.out.println("from i="+i+ " got k="+k+" from j="+j +" got p="+p);
                if (k == p) {
                    res = Math.max(res, 1);
                    //   System.out.println("maxing with 1");
                    continue;
                } else if (ch[k] != ch[p]) {
                    continue;
                }
                int nst = st;
                nst |= (1 << k);
                nst |= (1 << p);
                //      System.out.println("doing into");
                int later = solve(n, nst, k, p);
                res = Math.max(res, 2 + later);

            }
        }
        //  System.out.println("st="+st+" i="+i+" j="+j+" res="+res);
        dp[st][i][j] = res;
        return res;
    }
}
