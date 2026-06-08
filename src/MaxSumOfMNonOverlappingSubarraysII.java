public class MaxSumOfMNonOverlappingSubarraysII {
    // Alien trick ??!!
    private int n;
    private int m;
    private int l;
    private int r;
    private long[] psum;

    public long maximumSum(int[] nums, int m, int l, int r) {
        int[] fentoluric = nums;

        this.n = nums.length;
        this.m = m;
        this.l = l;
        this.r = r;

        psum = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            psum[i + 1] = psum[i] + nums[i];
        }

        Pair zero = calc(0);
        if (zero.cnt == 0) {
            return bestOne();
        }
        if (zero.cnt <= m) {
            return zero.val;
        }

        long lo = 0;
        long hi = 100000000000000L;
        while (lo < hi) {
            long mid = lo + (hi - lo + 1) / 2;
            Pair cur = calc(mid);
            if (cur.cnt >= m) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }

        Pair cur = calc(lo);
        return cur.val + lo * m;
    }

    private Pair calc(long cost) {
        long[] dp = new long[n + 1];
        int[] cnt = new int[n + 1];

        int[] dq = new int[n + 1];
        int h = 0;
        int t = 0;

        for (int i = 1; i <= n; ++i) {
            int head = i - l;
            if (head >= 0) {
                while (h < t && worse(dq[t - 1], head, dp, cnt)) {
                    --t;
                }
                dq[t++] = head;
            }

            int tail = i - r;
            while (h < t && dq[h] < tail) {
                ++h;
            }

            dp[i] = dp[i - 1];
            cnt[i] = cnt[i - 1];

            if (h < t) {
                int j = dq[h];
                long way2 = dp[j] + psum[i] - psum[j] - cost;
                int c = cnt[j] + 1;
                if (way2 > dp[i] || way2 == dp[i] && c > cnt[i]) {
                    dp[i] = way2;
                    cnt[i] = c;
                }
            }
        }

        return new Pair(dp[n], cnt[n]);
    }

    private boolean worse(int x, int y, long[] dp, int[] cnt) {
        long vx = dp[x] - psum[x];
        long vy = dp[y] - psum[y];
        if (vx != vy) {
            return vx < vy;
        }
        return cnt[x] <= cnt[y];
    }

    private long bestOne() {
        long res = Long.MIN_VALUE / 4;

        int[] dq = new int[n + 1];
        int h = 0;
        int t = 0;

        for (int i = 1; i <= n; ++i) {
            int head = i - l;
            if (head >= 0) {
                while (h < t && psum[dq[t - 1]] >= psum[head]) {
                    --t;
                }
                dq[t++] = head;
            }

            int tail = i - r;
            while (h < t && dq[h] < tail) {
                ++h;
            }

            if (h < t) {
                res = Math.max(res, psum[i] - psum[dq[h]]);
            }
        }

        return res;
    }

    private static class Pair {
        long val;
        int cnt;

        Pair(long val, int cnt) {
            this.val = val;
            this.cnt = cnt;
        }
    }
}
