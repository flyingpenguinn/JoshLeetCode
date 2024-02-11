public class NumOfSubarrayMatchPatternIandII {
    public int countMatchingSubarrays(int[] a, int[] pt) {
        int n = a.length;
        int pn = pt.length;
        int[] pa = new int[n - 1];
        for (int i = 0; i + 1 < n; ++i) {
            if (a[i + 1] > a[i]) {
                pa[i] = 1;
            } else if (a[i + 1] < a[i]) {
                pa[i] = -1;
            } else {
                pa[i] = 0;
            }
        }
        int[] lps = computeLPSArray(pt);
        --n;
        int i = 0;
        int j = 0;
        int res = 0;
        while ((n - i) >= (pn - j)) {
            if (pt[j] == pa[i]) {
                j++;
                i++;
            }
            if (j == pn) {
                ++res;
                j = lps[j - 1];
            } else if (i < n
                    && pt[j] != pa[i]) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i = i + 1;
                }
            }

        }
        return res;
    }

    private int[] computeLPSArray(int[] a) {

        int n = a.length;
        int[] lps = new int[n];
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < n) {
            if (a[i] == a[len]) {
                len++;
                lps[i] = len;
                i++;
            } else {

                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
        return lps;
    }
}
