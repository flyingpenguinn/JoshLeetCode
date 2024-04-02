import base.ArrayUtils;

import java.util.*;

public class FindSumOfSubsequencePowers {
    // we dont really need to dp on i because it's incorporated in "last"
    private long mod = (long) (1e9 + 7);
    private long Max = (long) 1e9;
    private Map<Long, Long[][]> dp = new HashMap<>();

    public int sumOfPowers(int[] a, int k) {
        int n = a.length;
        Arrays.sort(a);
        long res = 0;
        for (int i = 0; i + 1 < n; ++i) {
            long cur = solve(a, i + 1, i, k - 1, k, Max);
            res += cur;
            res %= mod;
        }
        return (int) res;
    }

    private long solve(int[] a, int i, int last, int k, int ok, long diff) {
        int n = a.length;
        if (k < 0) {
            return 0;
        }
        if (i == n) {
            return k == 0 ? diff : 0;
        }
        if (dp.containsKey(diff)) {
            Long[][] cdp = dp.get(diff);
            if (cdp[last][k] != null) {
                return cdp[last][k];
            }
        }
        long way1 = solve(a, i + 1, last, k,ok, diff);
        long cdiff = a[i] - a[last];
        long mindiff = Math.min(cdiff, diff);
        long way2 = solve(a, i + 1, i, k - 1, ok, mindiff);
        long res = way1 + way2;
        res %= mod;
        Long[][] cdp = dp.get(diff);
        if (cdp == null) {
            cdp = new Long[n][ok];
            dp.put(diff, cdp);
        }
        cdp[last][k] = res;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new FindSumOfSubsequencePowers().sumOfPowers(ArrayUtils.read1d("1,2,3"), 3));
    }
}
