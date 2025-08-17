import java.util.Arrays;
import java.util.TreeMap;

public class NumberOfPerfectPairs {
    // can be simplied to two pointers on abs
    private TreeMap<Integer, Integer> rm = new TreeMap<>();
    private int[] bit;

    long q(int i) {
        long res = 0;
        while (i > 0) {
            res += bit[i];
            i -= i & (-i);
        }
        return res;
    }

    void u(int i) {
        while (i < bit.length) {
            ++bit[i];
            i += i & (-i);
        }
    }

    public long perfectPairs(int[] a) {
        int n = a.length;
        rm.clear();
        int[] ca = Arrays.copyOf(a, n);
        int rank = 1;
        rm.put(Integer.MIN_VALUE, rank++);

        Arrays.sort(ca);
        for (int i = 0; i < n; ++i) {
            if (ca[i] == 0) {
                continue;
            }
            if (i == 0 || ca[i] != ca[i - 1]) {
                rm.put(ca[i], rank++);
            }
        }
        rm.put(Integer.MAX_VALUE, rank++);
        bit = new int[rank + 1];
        long res = 0;
        long zeros = 0;
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            if (v == 0) {
                res += zeros;
                ++zeros;
                continue;
            }
            int v1 = (Math.abs(v) + 1) / 2;
            int v2 = Math.abs(v) * 2;
            int v3 = -v1;
            int v4 = -v2;
            long diff1 = getdiff(v1, v2);
            long diff2 = getdiff(v4, v3);
            res += diff1;
            res += diff2;

            u(getrank(v));
        }
        return res;
    }

    protected long getdiff(int v1, int v2) {
        final int v2rank = getrank(v2);
        Long bv2 = q(v2rank);
        final int v1rank = getrank(v1 - 1);
        Long bv1 = q(v1rank);
        return bv2 - bv1;
    }

    private int getrank(int v2) {
        Integer key = rm.floorKey(v2);
        return rm.get(key);
    }
}
