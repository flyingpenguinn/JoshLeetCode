public class ZeroArrayTransformationII {
    public int minZeroArray(int[] a, int[][] qs) {
        int qn = qs.length;
        int n = a.length;
        int l = 0;
        int u = qn;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (good(a, mid, qs)) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l > qn ? -1 : l;
    }

    private boolean good(int[] a, int k, int[][] qs) {
        int qn = qs.length;
        int n = a.length;
        long[] tm = new long[n+1];
        for (int i = 0; i < k; ++i) {
            int[] q = qs[i];
            int s = q[0];
            int e = q[1];
            long v = q[2];
            tm[s] += v;
            tm[e+1] -= v;
        }
        long csum = 0;
        for (int i = 0; i < n; ++i) {
            csum += tm[i];
            if (csum < a[i]) {
                return false;
            }
        }
        return true;
    }
}
