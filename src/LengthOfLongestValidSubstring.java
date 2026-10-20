import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LengthOfLongestValidSubstring {
    private long base = 1007;
    private long mod1 = 1_000_000_007L;
    private long mod2 = 1_000_000_009L;

    public int longestValidSubstring(String word, List<String> forbidden) {
        int n = word.length();
        int l = 0;
        int u = n;
        Set<Long> fb = new HashSet<>();
        int maxfb = 0;
        for (String f : forbidden) {
            long cur1 = 0;
            long cur2 = 0;
            for (int i = 0; i < f.length(); ++i) {
                int cind = f.charAt(i) - 'a' + 1;
                cur1 = cur1 * base + cind;
                cur1 %= mod1;
                cur2 = cur2 * base + cind;
                cur2 %= mod2;
            }
            long key = (cur1 << 32) ^ cur2;
            fb.add(key);
            maxfb = Math.max(maxfb, f.length());
        }
        int[] map = new int[n];
        Arrays.fill(map, -1);
        for (int len = 1; len <= maxfb; ++len) {
            mark(word, len, fb, map);
        }
        int[] reach = new int[n + 1];
        reach[n] = n;
        for (int i = n - 1; i >= 0; --i) {
            reach[i] = reach[i + 1];
            if (map[i] != -1) {
                int end = i + map[i] - 1;
                reach[i] = Math.min(reach[i], end);
            }
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int clen = reach[i] - i;
            res = Math.max(res, clen);
        }
        return res;
    }


    private void mark(String s, int len, Set<Long> fb, int[] map) {
        int n = s.length();
        long cur1 = 0;
        long cur2 = 0;
        long multibase1 = 1;
        long multibase2 = 1;
        for (int i = 0; i < len - 1; ++i) {
            multibase1 *= base;
            multibase1 %= mod1;
            multibase2 *= base;
            multibase2 %= mod2;
        }
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - 'a' + 1;
            cur1 = cur1 * base + cind;
            cur1 %= mod1;
            cur2 = cur2 * base + cind;
            cur2 %= mod2;

            int head = i - len + 1;
            if (head < 0) {
                continue;
            }
            long key = (cur1 << 32) ^ cur2;
            if (fb.contains(key)) {
                if (map[head] == -1) {
                    map[head] = len;
                }
            }
            int headind = s.charAt(head) - 'a' + 1;
            cur1 -= multibase1 * headind;
            cur1 %= mod1;
            if (cur1 < 0) {
                cur1 += mod1;
            }
            cur2 -= multibase2 * headind;
            cur2 %= mod2;
            if (cur2 < 0) {
                cur2 += mod2;
            }
        }
    }
}
