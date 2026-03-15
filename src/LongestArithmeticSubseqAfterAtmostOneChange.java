public class LongestArithmeticSubseqAfterAtmostOneChange {
    public int longestArithmetic(int[] a) {
        int n = a.length;
        int[] diff = new int[n - 1];
        for (int i = 1; i < n; ++i) {
            diff[i - 1] = a[i] - a[i - 1];
        }
        int dn = diff.length;
        int[] inclen = new int[dn];
        inclen[0] = 1;
        for (int i = 1; i < dn; ++i) {
            inclen[i] = diff[i] == diff[i - 1] ? inclen[i - 1] + 1 : 1;
        }
        int[] declen = new int[dn];
        declen[dn - 1] = 1;
        for (int i = dn - 2; i >= 0; --i) {
            declen[i] = diff[i] == diff[i + 1] ? declen[i + 1] + 1 : 1;
        }
        int res = 0;
        for (int i = 0; i < n - 1; ++i) {
            res = Math.max(inclen[i], res);
            res = Math.max(declen[i], res);
            if (i - 1 >= 0 && diff[i] != diff[i - 1]) {
                res = Math.max(res, inclen[i - 1] + 1);
            }
            if (i + 1 < dn && diff[i] != diff[i + 1]) {
                res = Math.max(res, declen[i + 1] + 1);
            }
            if (i - 1 >= 0 && i + 1 < dn && diff[i] + diff[i + 1] == 2 * diff[i - 1]) {
                res = Math.max(res, inclen[i - 1] + 2);
                if (i + 2 < dn && diff[i + 2] == diff[i - 1]) {
                    res = Math.max(res, inclen[i - 1] + 2 + declen[i + 2]);
                }
            }
            if (i - 1 >= 0 && i + 1 < dn && diff[i] + diff[i - 1] == 2 * diff[i + 1]) {
                res = Math.max(res, declen[i + 1] + 2);
                if (i - 2 >= 0 && diff[i - 2] == diff[i + 1]) {
                    res = Math.max(res, declen[i + 1] + 2 + inclen[i - 2]);
                }
            }

        }
        return res + 1;
    }
}
