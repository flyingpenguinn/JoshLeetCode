public class MaxNumberOfSubseqAfterOneInsertion {
    // can simply: must insert at head for L, and must insert at tail for T. only c is question
    public long numOfSubsequences(String s) {
        int n = s.length();
        long[] rt = new long[n + 1];
        char[] c = s.toCharArray();
        for (int i = n - 1; i >= 0; --i) {
            rt[i] = rt[i + 1];
            if (c[i] == 'T') {
                ++rt[i];
            }
        }
        long[] cts = new long[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            cts[i] = cts[i + 1];
            if (c[i] == 'C') {
                cts[i] += rt[i + 1];
            }
        }

        long[] ll = new long[n];
        for (int i = 0; i < n; ++i) {
            ll[i] = (i == 0 ? 0 : ll[i - 1]);
            if (c[i] == 'L') {
                ++ll[i];
            }
        }
        long[] lcs = new long[n];
        long base = 0;
        for (int i = 0; i < n; ++i) {
            lcs[i] = (i == 0 ? 0 : lcs[i - 1]);
            if (c[i] == 'C') {
                long left = (i == 0 ? 0 : ll[i - 1]);
                long right = rt[i + 1];
                long cur = left * right;
                base += cur;
                lcs[i] += (i == 0 ? 0 : ll[i - 1]);
            }
        }
        long maxadd = 0;
        for (int i = 0; i <= n; ++i) {
            long way1 = cts[i];
            long way2 = 0;
            long way3 = 0;
            if (i >= 1) {
                way2 = ll[i - 1] * rt[i];
            }
            if (i >= 1) {
                way3 = lcs[i - 1];
            }
            long maxway = Math.max(way1, Math.max(way2, way3));
            maxadd = Math.max(maxadd, maxway);
        }
        return base + maxadd;
    }
}
