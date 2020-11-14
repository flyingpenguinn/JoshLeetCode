import java.util.HashMap;
import java.util.Map;

public class DistributeRepeatingIntegers {
    // similar to parallel courses II, we can enable a certain subset each time
    public boolean canDistribute(int[] a, int[] q) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            m.put(a[i], m.getOrDefault(a[i], 0) + 1);
        }
        int[] items = new int[m.size()];

        int ii = 0;
        for (int k : m.keySet()) {
            items[ii++] = m.get(k);
        }
        Boolean[][] dp = new Boolean[items.length][(1 << q.length)];
        return docan(0, 0, items, q, dp);
    }

    private boolean docan(int i, int st, int[] items, int[] q, Boolean[][] dp) {
        if (i == items.length) {
            return st == (1 << q.length) - 1;
        }
        if (dp[i][st] != null) {
            return dp[i][st];
        }
        boolean cur = docan(i + 1, st, items, q, dp);
        if (cur) {
            dp[i][st] = true;
            return true;
        }
        int subset = 0;
        for (int j = 0; j < q.length; j++) {
            if (((st >> j) & 1) == 0 && q[j] <= items[i]) {
                subset |= (1 << j);
            }
        }

        for (int j = subset; j > 0; j = (j - 1) & subset) {
            if (cando(j, q, items, i)) {
                int nst = st | j;
                cur = docan(i + 1, nst, items, q, dp);
                if (cur) {
                    dp[i][st] = true;
                    return true;
                }
            }
        }
        dp[i][st] = false;
        return false;
    }

    private boolean cando(int st, int[] q, int[] items, int i) {
        int res = 0;
        for (int j = 0; j < q.length; j++) {
            if (((st >> j) & 1) == 1) {
                res += q[j];
            }
        }
        boolean rt = res <= items[i];
        return rt;
    }
}
