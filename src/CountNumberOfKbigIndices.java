public class CountNumberOfKbigIndices {

    public int kBigIndices(int[] a, int k) {
        int n = a.length;
        int[] bit1 = new int[n + 1];
        int[] bit2 = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            u(bit2, a[i], 1);
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            u(bit2, a[i], -1);
            int cur1 = q(bit1, a[i] - 1);
            int cur2 = q(bit2, a[i] - 1);
            if (cur1 >= k && cur2 >= k) {
                ++res;
            }
            u(bit1, a[i], 1);

        }
        return res;
    }

    // update
    private void u(int[] b, int i, int d) {
        while (i < b.length) {
            b[i] += d;
            i += i & (-i);
        }
    }

    // query
    private int q(int[] b, int i) {
        int res = 0;
        while (i > 0) {
            res += b[i];
            i -= i & (-i);
        }
        return res;

    }
}
