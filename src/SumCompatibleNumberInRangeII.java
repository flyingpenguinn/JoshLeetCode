public class SumCompatibleNumberInRangeII {
    // 2026-11-17, new problem. Digit DP over bits (high -> low): x must be 0 on every bit where n is 1
    // (x is a submask of ~n). sum over [max(0, n-k), n+k] = f(n+k) - f(start-1), dp state (bit, small),
    // returns {sum, count}. 6 WAs: 4 on long/int overflow; 1L << bit has to be taken mod before multiplying by count.
    int limit = 60;
    boolean[] must0 = new boolean[limit];
    long mod = (long) (1e9 + 7);

    public int sumOfGoodIntegers(long n, long k) {
        for (int i = 0; i < limit; ++i) {
            if (((n >> i) & 1) == 1) {
                must0[i] = true;
            }
        }
        long realstart = Math.max(0, n - k);
        long realend = n + k;

        long seg1 = solve(tobinary(realend));
        long seg2 = realstart == 0 ? 0 : solve(tobinary(realstart - 1));
        long res = seg1 - seg2;
        res %= mod;
        if(res<0){
            res += mod;
        }
        return (int) res;

    }

    private long[][][] dp;

    private long solve(int[] target) {
        dp = new long[limit][2][2];
        for(int i=0; i<limit; ++i){
            for(int j=0; j<2; ++j){
                dp[i][j] = null;
            }
        }
        return dodp(0, 0, target)[0];
    }

    private long[] dodp(int i, int small, int[] target) {
        if (i == limit) {
            return new long[]{0L, 1L};
        }
        if (dp[i][small] != null) {
            return dp[i][small];
        }
        int bit = limit - 1 - i;
        long ccnt = 0;
        long csum = 0;
        if (!must0[bit]) {
            if (small == 1 || target[bit] == 1) {
                long[] later = dodp(i + 1, small, target);
                long latercount = later[1];
                ccnt += latercount;
                ccnt %= mod;
                long latersum = later[0];
                long cbit = (1L << bit);
                cbit %= mod;
                long csumseg =  cbit * latercount;
                csumseg %= mod;
                csum = csumseg + latersum;
                csum %= mod;
            }
        }
        int nsmall = small;
        if (target[bit] == 1) {
            nsmall = 1;
        }
        long[] later1 = dodp(i + 1, nsmall, target);
        ccnt += later1[1];
        ccnt %= mod;
        csum += later1[0];
        csum %= mod;
        long[] res = {csum, ccnt};

        dp[i][small] = res;
        return res;
    }

    private int[] tobinary(long num) {
        int[] res = new int[limit];
        for (int i = limit - 1; i >= 0; --i) {
            if (((num >> i) & 1) == 1) {
                res[i] = 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new SumCompatibleNumberInRangeII().sumOfGoodIntegers(2, 3)); // 10
        System.out.println(new SumCompatibleNumberInRangeII().sumOfGoodIntegers(5, 1)); // 0
        java.util.Random rnd = new java.util.Random(7);
        int bad = 0;
        for (int t = 0; t < 3000; ++t) {
            long n = 1 + rnd.nextInt(300), k = rnd.nextInt(300);
            long exp = 0;
            for (long x = Math.max(1, n - k); x <= n + k; ++x) {
                if ((n & x) == 0) {
                    exp += x;
                }
            }
            if (new SumCompatibleNumberInRangeII().sumOfGoodIntegers(n, k) != exp % 1000000007L) {
                ++bad;
            }
        }
        System.out.println("bad = " + bad);
        System.out.println(new SumCompatibleNumberInRangeII().sumOfGoodIntegers(1000000000000000L, 1000000000000000L));
    }
}
