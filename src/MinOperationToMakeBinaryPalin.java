public class MinOperationToMakeBinaryPalin {
    public int[] minOperations(int[] a) {
        int n = a.length;
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            res[i] = solve(v);
        }
        return res;
    }

    private int solve(int v) {
        int adds = 0;
        int ov = v;
        while (!ispalin(v)) {
            ++v;
            ++adds;
        }
        v = ov;
        int minus = 0;
        while (!ispalin(v)) {
            --v;
            ++minus;
        }
        return Math.min(adds, minus);
    }

    private boolean ispalin(int v) {
        char[] c = Integer.toBinaryString(v).toCharArray();
        int i = 0;
        int j = c.length - 1;
        while (i < j) {
            if (c[i++] != c[j--]) {
                return false;
            }
        }
        return true;
    }
}
