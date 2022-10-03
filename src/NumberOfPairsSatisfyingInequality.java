public class NumberOfPairsSatisfyingInequality {
    private int[] bit;
    private int shift = 30001;

    public long numberOfPairs(int[] a, int[] b, int diff) {
        bit = new int[70000];
        int n = a.length;
        long res = 0;
        for (int i = 0; i < n; ++i) {
            int cd = a[i] - b[i];
            cd += shift;
            int cur = q(cd);
            res += cur;
            u(cd - diff, 1);
        }
        return res;
    }

    private void u(int i, int d) {
        while (i < bit.length) {
            bit[i] += d;
            i += i & (-i);
        }
    }

    private int q(int i) {
        int cur = 0;
        while (i > 0) {
            cur += bit[i];
            i -= i & (-i);
        }
        return cur;
    }
}
