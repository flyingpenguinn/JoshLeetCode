import java.util.Arrays;
import java.util.TreeMap;

public class MinThresholdForInversionPairsCount {
    private TreeMap<Integer, Integer> rm = new TreeMap<>();

    public int minThreshold(int[] a, int k) {
        int n = a.length;
        int Max = (int) 1e9;
        int l = 0;
        int u = Max;
        int[] na = Arrays.copyOf(a, n);
        Arrays.sort(na);
        rank = 1;
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && na[i] == na[j]) {
                ++j;
            }
            rm.put(na[i], rank++);
            i = j;
        }

        while (l <= u) {
            int mid = l + (u - l) / 2;
            final int cp = pairs(a, mid);
            if (cp >= k) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l > Max ? -1 : l;
    }

    private int[] bit;
    private int rank;

    private int q(int i) {
        int res = 0;
        while (i > 0) {
            res += bit[i];
            i -= i & (-i);
        }
        return res;
    }

    private void u(int i, int d) {

        while (i < bit.length) {
            bit[i] += d;
            i += i & (-i);
        }
    }

    private int pairs(int[] a, int m) {
        bit = new int[rank];
        int n = a.length;
        int res = 0;
        for (int i = n - 1; i >= 0; --i) {
            int r1 = rm.get(a[i]);
            Integer r2key = rm.lowerKey(a[i] - m);
            int cur = 0;
            if (r2key == null) {
                cur = q(r1 - 1);
            } else {
                int r2 = rm.get(r2key);
                cur = q(r1 - 1) - q(r2);
            }
            res += cur;
            u(r1, 1);
        }
        return res;
    }
}
