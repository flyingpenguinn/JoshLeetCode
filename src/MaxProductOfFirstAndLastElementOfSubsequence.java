import java.util.TreeSet;

public class MaxProductOfFirstAndLastElementOfSubsequence {
    private long Max = (long) 1e15;
    private long Min = -Max;
    private long Mod = (long) (1e9 + 7);


    public long maximumProduct(int[] a, int m) {
        int n = a.length;
        if (m == 1) {
            long res = 0;
            for (long value : a) {
                res = Math.max(res, value * value);
            }
            return res;
        }
        int t = m - 1;
        int j = 0;
        long res = Min;
        TreeSet<Long> cand = new TreeSet<>();
        for (int i = 0; i < n; ++i) {
            while (j <= i - t) {
                cand.add((long) a[j]);
                ++j;
            }
            if (!cand.isEmpty()) {
                long v1 = cand.first() * a[i];
                long v2 = cand.last() * a[i];
                long cur = Math.max(v1, v2);
                res = Math.max(res, cur);
            }
        }
        return res;
    }
}
