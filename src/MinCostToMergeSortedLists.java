import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinCostToMergeSortedLists {
    // the result has nothing to do with order so we can use subset dp
    private long[] len;
    private long[] med;
    private long[] dp;

    public long minMergeCost(int[][] a) {
        int n = a.length;
        int full = (1 << n);
        len = new long[full];
        med = new long[full];
        dp = new long[full];
        Arrays.fill(dp, (long) 1e15);
        for (int i = 1; i < (1 << n); ++i) {
            if(Integer.bitCount(i) == 1){
                dp[i] = 0;
            }
            calc(a, i);
        }

        for (int i = 1; i < full; ++i) {
            for (int j = (i - 1) & i; j > 0; j = (j - 1) & i) {
                int other = i - j;
                long cres = dp[j] + dp[other] + len[j] + len[other] + Math.abs(med[j] - med[other]);
                dp[i] = Math.min(dp[i], cres);
            }
        }
        return dp[full - 1];
    }

    private void calc(int[][] a, int st) {
        List<Integer> cres = new ArrayList<>();
        int n = a.length;
        for (int i = 0; i < n; ++i) {
            if (((st >> i) & 1) == 1) {
                for (int ai : a[i]) {
                    cres.add(ai);
                }
            }
        }
        Collections.sort(cres);
        int cn = cres.size();
        len[st] = cres.size();
        med[st] = cres.get((cn - 1) / 2);
    }
}
