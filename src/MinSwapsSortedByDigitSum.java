import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinSwapsSortedByDigitSum {
    private int digsum(int v) {
        int s = 0;
        while (v > 0) {
            s += v % 10;
            v /= 10;
        }
        return s;
    }

    public int minSwaps(int[] a) {
        int n = a.length;
        Integer[] na = new Integer[n];
        for (int i = 0; i < n; ++i) {
            na[i] = a[i];
        }
        Arrays.sort(na, (x, y) -> {
            int sx = digsum(x);
            int sy = digsum(y);
            if (sx != sy) {
                return Integer.compare(sx, sy);
            } else {
                return Integer.compare(x, y);
            }
        });
        Map<Integer, Integer> npos = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            npos.put(na[i], i);
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            while (npos.get(a[i]) != i) {
                int oi = npos.get(a[i]);
                swap(a, i, oi);
                ++res;
            }
        }
        return res;
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
