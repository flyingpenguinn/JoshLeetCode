import java.util.Arrays;

public class MaxSubarraySumLenDivisibleByK {
    private long Max = (long) (1e16);

    public long maxSubarraySum(int[] a, int k) {
        int n = a.length;
        long[] mins = new long[k];
        Arrays.fill(mins, Max);
        mins[k - 1] = 0; // key! -1 is 0
        long csum = 0;
        long res = -Max;
        for (int i = 0; i < n; ++i) {
            int mod = i % k;
            csum += a[i];
            long prev = mins[mod];
            long cur = csum - prev;
            res = Math.max(res, cur);
            mins[mod] = Math.min(mins[mod], csum);
        }
        return res;
    }
}
