import java.util.Arrays;

public class MinNumberOfOperationsMakeArraySimilar {
    public long makeSimilar(int[] ia, int[] ib) {
        int n = ia.length;
        Long[] a = new Long[n];
        for (int i = 0; i < n; ++i) {
            a[i] = Long.valueOf(ia[i]);
        }
        Long[] b = new Long[n];
        for (int i = 0; i < n; ++i) {
            b[i] = Long.valueOf(ib[i]);
        }
        Arrays.sort(a, (x, y) -> {
            if (x % 2 == y % 2) {
                return Long.compare(x, y);
            } else {
                return Long.compare(x % 2, y % 2);
            }
        });
        Arrays.sort(b, (x, y) -> {
            if (x % 2 == y % 2) {
                return Long.compare(x, y);
            } else {
                return Long.compare(x % 2, y % 2);
            }
        });
        long res = 0;
        for (int i = 0; i < n; ++i) {
            res += Math.abs(b[i] - a[i]);
        }
        return res / 4;
    }
}
