import base.ArrayUtils;

public class MinOperationsNumberNonNegative {
    // binary search the result
    public int minOperations(int[] a, int x, int y) {
        int l = 1;
        int u = (int) 1e9;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (possible(a, mid, x, y)) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean possible(int[] a, long t, long x, long y) {
        int n = a.length;
        long extra = 0;
        for (int i = 0; i < n; ++i) {
            if (a[i] - t * x > 0) {
                return false;
            }
            long rem = a[i] - t * y;
            if (rem > 0) {
                // p*x + (t-p)*y = a[i], needed = p
                long needed = (int) Math.ceil(1.0 * rem / (x - y));
                extra += needed;
                if (extra > t) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new MinOperationsNumberNonNegative().minOperations(ArrayUtils.read1d("[31,25,72,79]"), 8, 5));
    }
}
