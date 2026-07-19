public class TransformBinaryStringUsingSubseqSort {
    // key is to fill string t first

    public boolean[] transformStr(String s, String[] strs) {
        int n = s.length();
        int sn = strs.length;
        boolean[] res = new boolean[sn];
        int c0 = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '0') {
                ++c0;
            }
        }
        for (int i = 0; i < sn; ++i) {
            String t = strs[i];
            if (good(s, t, c0)) {
                res[i] = true;
            }
        }
        return res;
    }

    private boolean good(String s, String t, int c0) {
        int n = s.length();
        int t0 = 0;
        int t1 = 0;
        char[] ct = t.toCharArray();

        int c1 = n - c0;
        for (int i = 0; i < n; ++i) {
            if (t.charAt(i) == '0') {
                ++t0;
            } else if (t.charAt(i) == '1') {
                ++t1;
            }
        }
        if (t1 > c1 || t0 > c0) {
            return false;
        }
        for (int i = 0; i < n; ++i) {
            if (ct[i] == '?') {
                if (t0 < c0) {
                    ct[i] = '0';
                    ++t0;
                } else {
                    ct[i] = '1';
                    ++t1;
                }
            }
        }
        t = new String(ct);
        int i = 0;
        int j = 0;
        while (i < n && j < n) {
            while (i < n && s.charAt(i) != '1') {
                ++i;
            }
            while (j < n && t.charAt(j) != '1') {
                ++j;
            }
            if (j == n || i == n) {
                break;
            }
            if (j < i) {
                return false;
            }
            ++i;
            ++j;
        }
        return true;

    }
}
