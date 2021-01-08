import java.util.HashSet;
import java.util.Set;

public class SumOfSpecialEvenlySpacedArray {
    // step related think about sqrt because n/y == sqrtn after we preprocess <=sqrt and deal with >sqrt directly
    private long Mod = 1000000007;

    public int[] solve(int[] a, int[][] qs) {
        int n = a.length;
        int sqrt = (int) Math.sqrt(n);
        long[][] dp = new long[n][sqrt + 1];
        // calc values starting from 0
        Set<Integer> set = new HashSet<>();
        for (int[] q : qs) {
            set.add(q[1]);
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int len = 1; len <= sqrt; len++) {
                if (!set.contains(len)) {
                    continue;
                }
                dp[i][len] = (i + len >= n ? 0 : dp[i + len][len]) + a[i];
                dp[i][len] %= Mod;
            }
        }
        //   System.out.println(Arrays.deepToString(dp));
        int[] res = new int[qs.length];

        for (int i = 0; i < qs.length; i++) {
            int[] q = qs[i];
            int start = q[0];
            int len = q[1];
            if (len <= sqrt) {
                res[i] = (int) (dp[start][len]);
            } else {
                long sum = 0;
                for (int j = start; j < a.length; j += len) {
                    sum += a[j];
                    sum %= Mod;
                }
                res[i] = (int) sum;
            }
        }
        return res;
    }
}
