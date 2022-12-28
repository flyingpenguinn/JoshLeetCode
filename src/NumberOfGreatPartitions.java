public class NumberOfGreatPartitions {

    private long mod = (long) (1e9 + 7);
    private Long[][] dp;

    public int countPartitions(int[] a, int k) {

        int n = a.length;
        long sum = 0;
        for (int ai : a) {
            sum += ai;
        }
        if (sum < 2 * k) {
            return 0;
        }
        dp = new Long[n][k];
        // the opposite fo the question: finding a set that has sum <k
        long v1 = solve(a, 0, 0, k);
        // either set 1 or set2 can be the smaller one
        long v = pow2(n) - 2 - 2 * v1;
        v %= mod;
        if (v < 0) {
            v += mod;
        }
        return (int) v;
    }

    private long pow2(int n) {
        if (n == 0) {
            return 1L;
        }
        long half = pow2(n / 2);
        long res = half * half;
        res %= mod;
        if (n % 2 == 1) {
            res *= 2;
            res %= mod;
        }
        return res;
    }


    // num of ways so that 0<sum <k. current sum in j
    private long solve(int[] a, int i, int j, int k) {
        int n = a.length;
        if (j >= k) {
            return 0;
        }
        if (i == n) {
            if (j > 0 && j < k) {
                return 1;
            } else {
                return 0;
            }
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        long way1 = solve(a, i + 1, j, k);
        long way2 = solve(a, i + 1, j + a[i], k);
        long res = way1 + way2;
        res %= mod;
        dp[i][j] = res;
        return res;
    }
}
