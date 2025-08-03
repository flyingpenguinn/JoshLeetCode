import java.util.Arrays;

public class MinRemovalsToBalanceArray {
    public int minRemoval(int[] ia, int k) {
        int n = ia.length;
        long[] a = new long[n];
        for (int i = 0; i < n; ++i) {
            a[i] = ia[i];
        }
        Arrays.sort(a);
        int j = 0;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            while (a[j] * k < a[i]) {
                ++j;
            }
            int len = i - j + 1;
            res = Math.max(res, len);
        }
        return n - res;
    }
}
