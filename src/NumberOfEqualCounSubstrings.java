public class NumberOfEqualCounSubstrings {
    // similar to #395 longest substring with at least k repeating chars
    // we enumerate how many uniq chars are there so as to fix the sliding window size
    public int equalCountSubstrings(String s, int t) {
        int n = s.length();
        int res = 0;
        for (int uniq = 1; uniq <= 26; ++uniq) {
            // how many uniq chars?
            int len = uniq * t;
            int[] m = new int[26];
            for (int i = 0; i < n; ++i) {
                ++m[s.charAt(i) - 'a'];
                int head = i - len + 1;
                if (head >= 0) {
                    boolean bad = false;
                    for (int j = 0; j < 26; ++j) {
                        if (m[j] > 0 && m[j] != t) {
                            bad = true;
                            break;
                        }
                    }
                    if (!bad) {
                        ++res;
                    }
                    int hc = s.charAt(head) - 'a';
                    --m[hc];
                }
            }
        }
        return res;
    }
}
