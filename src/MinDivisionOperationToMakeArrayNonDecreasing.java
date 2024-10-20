import java.util.Arrays;

public class MinDivisionOperationToMakeArrayNonDecreasing {
    private static int[] lookup;

    public int minOperations(int[] a) {
        if (lookup == null) {
            lookup = new int[1000000];
            Arrays.fill(lookup, -1);
        }
        int n = a.length;
        int res = 0;
        for (int i = n - 2; i >= 0; --i) {
            int t = a[i + 1];
            int v = a[i];
            while (v > t) {
                int gd = getGreatestProper(v);
                if (gd == 1) {
                    return -1;
                }
                ++res;
                v /= gd;
            }
            a[i] = v;
        }
        return res;
    }

    private int getGreatestProper(int v) {
        if (lookup[v] != -1) {
            return lookup[v];
        }
        int res = 1;
        for (int i = 2; i * i <= v; ++i) {
            if (v % i == 0) {
                int p1 = i;
                int p2 = v / i;
                res = Math.max(res, p2);
                break;
            }
        }
        lookup[v] = res;
        return res;
    }
}
