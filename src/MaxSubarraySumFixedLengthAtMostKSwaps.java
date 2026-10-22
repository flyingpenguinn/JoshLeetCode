import java.util.Arrays;

public class MaxSubarraySumFixedLengthAtMostKSwaps {
    public long maxSum(int[] a, int length, int k) {
        int n = a.length;
        long res = (long) -1e18;
        for (int i = 0; i + length - 1 < n; ++i) {
            int[] inner = new int[length];
            long suminner = 0;
            int ii = 0;
            for (int j = i; j <= i + length - 1; ++j) {
                inner[ii++] = a[j];
                suminner += a[j];
            }
            int[] outer = new int[n - length];
            int oi = 0;
            for (int j = 0; j < i; ++j) {
                outer[oi++] = a[j];
            }
            for (int j = i + length; j < n; ++j) {
                outer[oi++] = a[j];
            }
            Arrays.sort(inner);
            Arrays.sort(outer);
            long sk1 = 0;
            long sk2 = 0;
            res = Math.max(res, suminner);
            for (int j = 0; j < k && outer.length - 1 - j>=0 && j<inner.length; ++j) {
                sk1 += inner[j];
                sk2 += outer[outer.length - 1 - j];
                long delta = sk2 - sk1;
                if (delta < 0) {
                    break;
                }
                long cres = suminner + delta;
                res = Math.max(res, cres);
            }
        }
        return res;
    }
}
