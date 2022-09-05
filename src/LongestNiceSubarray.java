public class LongestNiceSubarray {
    public int longestNiceSubarray(int[] a) {
        int[] set = new int[32];
        int n = a.length;
        int start = 0;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int cur = a[i];
            int[] cset = new int[32];
            for (int j = 0; j < 32; ++j) {
                if (((cur >> j) & 1) == 1) {
                    ++cset[j];
                }
            }
            while (start < i && conflict(cset, set)) {
                for (int j = 0; j < 32; ++j) {
                    if (((a[start] >> j) & 1) == 1) {
                        --set[j];
                    }
                }
                ++start;
            }
            for (int j = 0; j < 32; ++j) {
                set[j] += cset[j];
            }
            int cd = i - start + 1;
            res = Math.max(res, cd);
        }
        return res;
    }

    private boolean conflict(int[] a, int[] b) {
        for (int i = 0; i < a.length; ++i) {
            if (a[i] > 0 && b[i] > 0) {
                return true;
            }
        }
        return false;
    }
}
