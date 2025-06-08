import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxGoodSubtreeScore {
    // merge two subtree scores each 1k at mask
    private static final int MOD = 1_000_000_007;
    private long ans = 0;

    public int goodSubtreeSum(int[] vals, int[] par) {
        int n = vals.length;
        List<Integer>[] children = new ArrayList[n];
        for (int i = 0; i < n; i++) children[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            children[par[i]].add(i);
        }
        dfs(0, vals, children);
        return (int) (ans % MOD);
    }

    private DpResult dfs(int u, int[] vals, List<Integer>[] children) {
        int maskU = digitMask(vals[u]);
        long valU = vals[u];

        long[] dpU = new long[1 << 10];
        Arrays.fill(dpU, -1);
        List<Integer> masksU = new ArrayList<>();

        dpU[0] = 0;
        masksU.add(0);

        dpU[maskU] = valU;
        masksU.add(maskU);


        for (int c : children[u]) {
            DpResult cr = dfs(c, vals, children);
            long[] dpV = cr.dp;
            List<Integer> masksV = cr.masks;

            long[] newDp = new long[1 << 10];
            Arrays.fill(newDp, -1);
            List<Integer> newMasks = new ArrayList<>();

            // carry over subsets that skip the child's subtree
            for (int m1 : masksU) {
                newDp[m1] = dpU[m1];
                newMasks.add(m1);
            }

            // combine subsets
            for (int m1 : masksU) {
                long s1 = dpU[m1];
                for (int m2 : masksV) {
                    if ((m1 & m2) == 0) {
                        int m3 = m1 | m2;
                        long s3 = s1 + dpV[m2];
                        if (s3 > newDp[m3]) {
                            if (newDp[m3] < 0) newMasks.add(m3);
                            newDp[m3] = s3;
                        }
                    }
                }
            }

            dpU = newDp;
            masksU = newMasks;
        }

        long best = 0;
        for (int m : masksU) {
            best = Math.max(best, dpU[m]);
        }
        ans = (ans + best) % MOD;

        return new DpResult(dpU, masksU);
    }

    private int digitMask(int x) {
        int mask = 0;
        while (x > 0) {
            int d = x % 10;
            if (((mask >> d) & 1) != 0) return -1;
            mask |= 1 << d;
            x /= 10;
        }
        return mask;
    }

    private static class DpResult {
        long[] dp;
        List<Integer> masks;

        DpResult(long[] dp, List<Integer> masks) {
            this.dp = dp;
            this.masks = masks;
        }
    }
}
