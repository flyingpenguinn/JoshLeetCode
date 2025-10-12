public class LongestBalancedStringI {
    public int longestBalanced(String s) {
        int n = s.length();
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int[] cnt = new int[26];
            for (int j = i; j < n; ++j) {
                int cind = s.charAt(j) - 'a';
                ++cnt[cind];
                int app = -1;
                boolean bad = false;
                for (int k = 0; k < 26; ++k) {
                    if (cnt[k] > 0) {
                        if (app == -1) {
                            app = cnt[k];
                        } else {
                            if (app != cnt[k]) {
                                bad = true;
                                break;
                            }
                        }

                    }
                }
                if (!bad) {
                    int len = j - i + 1;
                    res = Math.max(res, len);
                }
            }
        }
        return res;
    }
}
