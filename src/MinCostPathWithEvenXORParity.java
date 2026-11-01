import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinCostPathWithEvenXORParity {
    private List<int[]>[] t;
    private long MAX = (long)1e15;
    private long res = MAX;
    private long IMP = MAX;
    private long[][] dp;
    public int minActivationCost(int[][] edges, int[] values) {
        int en = edges.length;
        int n = en+1;
        t = new ArrayList[n];
        for(int i=0; i<n; ++i){
            t[i] = new ArrayList<>();
        }
        for(int[] e: edges){
            int v1 = e[0];
            int v2 = e[1];
            int w = e[2];
            t[v1].add(new int[]{v2, w});
            t[v2].add(new int[]{v1, w});
        }
        dp = new long[n][2];
        for(int i=0; i<n; ++i){
            Arrays.fill(dp[i], IMP);
        }
        dfs(0, -1, values);
        System.out.println(res);
        if(res>=MAX){
            return -1;
        }
        return (int)res;
    }

    private int parity(int v){
        int res = 0;
        for(int j=31; j>=0; --j){
            if(((v>>j)&1)==1){
                ++res;
            }
        }
        return res%2;
    }

    private void dfs(int i, int p, int[] v){
        int par = parity(v[i]);
        dp[i][par] = 0;
        for(int[] nep: t[i]){
            int ne = nep[0];
            int w = nep[1];
            if(ne == p){
                continue;
            }
            dfs(ne, i, v);
            for(int j=0; j<=1; ++j){
                if(dp[i][j] != IMP && dp[ne][j]!= IMP){
                    res = Math.min(res, dp[i][j]+dp[ne][j]+w);
                }
            }
            dp[i] = mergene(i, ne, par, w);
        }
    }

    private long[] mergene(int i, int ne, int par, int w){
        long[] ndp = new long[2];
        ndp = Arrays.copyOf(dp[i], 2);
        for(int j=0; j<=1; ++j){
            int parj = par^j;
            ndp[parj] = Math.min(ndp[parj], dp[i][par] + dp[ne][j] + w);
        }
        return ndp;
    }
}
