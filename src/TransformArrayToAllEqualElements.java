import base.ArrayUtils;

import java.util.Arrays;

public class TransformArrayToAllEqualElements {
    private int solve(int[] a, int t, int k) {
        int n = a.length;
        for (int i = 0; i + 1 < n; ++i) {
            if (a[i] != t) {
                --k;
                a[i + 1] *= -1;
            }
        }
        if (k >= 0 && a[n - 1] == t) {
            return 1;
        }
        return 0;
    }

    public boolean canMakeEqual(int[] a, int k) {
        int n = a.length;
        int[] na = Arrays.copyOf(a, n);
        return Math.max(solve(a, 1, k), solve(na, -1, k)) == 1;
    }

    public static void main(String[] args) {
        System.out.println(new TransformArrayToAllEqualElements().canMakeEqual(ArrayUtils.read1d("[-1,1,1,1,-1,-1,-1,1,1]"), 4));
    }
}
