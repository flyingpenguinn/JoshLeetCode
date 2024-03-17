public class NumOfSubarrayMatchPatternIandII {
    public int countMatchingSubarrays(int[] a, int[] pt) {
        int n = a.length;
        int[] s = new int[n - 1];
        for (int i = 0; i + 1 < n; ++i) {
            if (a[i] < a[i + 1]) {
                s[i] = 1;
            } else if (a[i] > a[i + 1]) {
                s[i] = -1;
            } else {
                s[i] = 0;
            }
        }
        return match(s, pt);
    }

    private int[] lps(int[] p) {
        int n = p.length;
        int len = 0;
        int[] lps = new int[n];
        lps[0] = 0;
        for (int i = 1; i < n; ++i) {
            while (len > 0 && p[len] != p[i]) {
                len = lps[len - 1];
            }
            if (p[len] == p[i]) {
                ++len;
            }
            lps[i] = len;
        }
        return lps;
    }

    private int match(int[] s, int[] p) {
        int n = s.length;
        int[] lps = lps(p);
        int len = 0;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            while (len > 0 && s[i] != p[len]) {
                len = lps[len - 1];
            }
            if (p[len] == s[i]) {
                ++len;
            }
            if (len == p.length) {
                ++res;
                // as if there is a mismatch
                len = lps[len - 1];
            }
        }
        return res;
    }
}
