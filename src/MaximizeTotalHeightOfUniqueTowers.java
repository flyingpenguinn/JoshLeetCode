import java.util.Arrays;

public class MaximizeTotalHeightOfUniqueTowers {
    public long maximumTotalSum(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        long res = 0;
        long last = (long) 1e9 + 5;
        for (int i = n - 1; i >= 0; --i) {
            long cur = a[i];
            cur = Math.min(last - 1, cur);
            if (cur <= 0) {
                return -1;
            }
            last = cur;
            res += cur;
        }
        return res;
    }
}
