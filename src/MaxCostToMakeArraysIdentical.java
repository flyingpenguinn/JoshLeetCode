import java.util.Arrays;

public class MaxCostToMakeArraysIdentical {
    public long minCost(int[] a, int[] b, long k) {
        int n = a.length;
        long res1 = 0;
        for (int i = 0; i < n; ++i) {
            long diff = Math.abs(a[i] - b[i]);
            res1 += diff;
        }
        long res2 = k;
        Arrays.sort(a);
        Arrays.sort(b);
        for (int i = 0; i < n; ++i) {
            long diff = Math.abs(a[i] - b[i]);
            res2 += diff;
        }
        long res = Math.min(res1, res2);
        return res;
    }
}
