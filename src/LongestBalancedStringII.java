import java.util.HashMap;
import java.util.Map;

public class LongestBalancedStringII {
    public int longestBalanced(String s) {
        int n = s.length();
        char[] sc = s.toCharArray();
        int i = 0;
        int res = 0;
        while (i < n) {
            int j = i;
            while (j < n && sc[j] == sc[i]) {
                ++j;
            }
            int len = j - i;
            res = Math.max(res, len);
            i = j;
        }
        int way1 = solve(s, 'a', 'b');
        int way2 = solve(s, 'a', 'c');
        int way3 = solve(s, 'b', 'c');
        res = Math.max(res, way1);
        res = Math.max(res, way2);
        res = Math.max(res, way3);
        res = Math.max(res, solve3(s));
        return res;

    }

    private int solve(String s, char v1, char v2) {
        int n = s.length();
        char[] sc = s.toCharArray();
        int i = 0;
        int res = 0;
        while (i < n) {
            if (sc[i] != v1 && sc[i] != v2) {
                ++i;
                continue;
            }
            int j = i;
            while (j < n && (sc[j] == v1 || sc[j] == v2)) {
                ++j;
            }
            Map<Integer, Integer> pre = new HashMap<>();
            pre.put(0, i - 1);
            int cnt1 = 0;
            int cnt2 = 0;
            for (int k = i; k < j; ++k) {
                char c = s.charAt(k);
                if (c == v1) {
                    ++cnt1;
                } else {
                    ++cnt2;
                }
                int cv = cnt1 - cnt2;
                if (pre.containsKey(cv)) {
                    int pindex = pre.get(cv);
                    int len = k - pindex;
                    res = Math.max(res, len);
                }
                pre.putIfAbsent(cv, k);
            }
            i = j;
        }
        return res;
    }

    private int solve3(String s) {
        int n = s.length();
        char[] sc = s.toCharArray();
        int res = 0;
        Map<Long, Integer> pre = new HashMap<>();
        pre.put(0L, -1);
        int cnt1 = 0;
        int cnt2 = 0;
        int cnt3 = 0;
        for (int i = 0; i < n; ++i) {

            int cind = s.charAt(i) - 'a' + 1;
            if (cind == 1) {
                ++cnt1;
            } else if (cind == 2) {
                ++cnt2;
            } else {
                ++cnt3;
            }
            long d1 = cnt1 - cnt2;
            long d2 = cnt1 - cnt3;

            long cur = d1 * shift + d2;
            if (pre.containsKey(cur)) {
                int previndex = pre.get(cur);
                int len = i - previndex;
                res = Math.max(res, len);
            }
            pre.putIfAbsent(cur, i);
        }
        return res;
    }

    private long shift = 100000000L;
}
