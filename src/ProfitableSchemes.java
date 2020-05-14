import java.util.Arrays;

public class ProfitableSchemes {
    int Mod = 1000000007;
    int[][][] dp;

    // knapsack solution counts
    public int profitableSchemes(int g, int p, int[] group, int[] profit) {
        int n = group.length;

        dp = new int[g + 1][p + 1][n];
        for (int i = 0; i < g + 1; i++) {
            for (int j = 0; j < p + 1; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return domax(g, p, 0, group, profit);
    }

    int domax(int g, int p, int i, int[] gr, int[] pr) {
        // if p<0 treat it as==0 use g and i

        int n = gr.length;
        if (g < 0) {
            return 0;
        }

        if (i == n) {
            return p == 0 ? 1 : 0;
        }

        if (dp[g][p][i] != -1) {
            return dp[g][p][i];
        }
        int pick = 0;
        if (g >= gr[i]) {
            int rp = Math.max(p - pr[i], 0);
            pick = domax(g - gr[i], rp, i + 1, gr, pr);
        }
        int nopick = domax(g, p, i + 1, gr, pr);
        int rt = (pick + nopick) % Mod;
        dp[g][p][i] = rt;
        return rt;
    }
}
