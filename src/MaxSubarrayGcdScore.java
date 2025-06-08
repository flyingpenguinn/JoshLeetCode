public class MaxSubarrayGcdScore {
    // we only need to "elevate" those with minimum factor of 2s
    public long maxGCDScore(int[] nums, int k) {
        int n = nums.length;
        long res = 0;
        int[] v2 = new int[n];
        for (int i = 0; i < n; ++i) {
            v2[i] = Integer.numberOfTrailingZeros(nums[i]);
        }
        for (int i = 0; i < n; ++i) {
            long g = 0;
            int e0 = Integer.MAX_VALUE;
            int m = 0;
            for (int j = i; j < n; ++j) {
                g = gcd(g, nums[j]);
                int tz = v2[j];
                if (tz < e0) {
                    e0 = tz;
                    m = 1;
                } else if (tz == e0) {
                    m++;
                }
                long len = j - i + 1L;
                long score = g * len;
                if (k >= m) {
                    score <<= 1;  // multiply by 2
                }
                if (score > res) {
                    res = score;
                }
            }
        }
        return res;
    }

    private long gcd(long a, long b) {
        if (a == 0) return b;
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
