public class CountValidSuffixSortsI {
    public int countSuffix(int[] a, int[] t) {
        int n = a.length;
        int i = n - 1;
        while (i >= 1 && t[i] <= t[i - 1]) {
            --i;
        }
        int begint = i;
        int[] diff = new int[(int) (1e5 + 1)];
        boolean[] good = new boolean[n];
        int mismatch = 0;
        for (i = n - 1; i >= 0; --i) {
            if (i < begint) {
                good[i] = false;
                continue;
            }
            int av = a[i];
            int tv = t[i];
            if (diff[av] == 0) {
                ++mismatch;
            } else if (diff[av] == -1) {
                --mismatch;
            }
            ++diff[av];
            if (diff[tv] == 1) {
                --mismatch;
            } else if (diff[tv] == 0) {
                ++mismatch;
            }
            --diff[tv];

            if (mismatch == 0) {
                good[i] = true;
            }
        }
        boolean[] match = new boolean[n];
        for (i = 0; i < n; ++i) {
            if (a[i] == t[i]) {
                match[i] = true;
            } else {
                break;
            }
        }
        int res = 0;
        for (i = 0; i < n; ++i) {
            if ((i == 0 || match[i - 1]) && good[i]) {
                ++res;
            }
        }
        return res;
    }
}
