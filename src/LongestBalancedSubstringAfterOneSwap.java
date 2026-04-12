import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestBalancedSubstringAfterOneSwap {
    public int longestBalanced(String s) {

        String revs = new StringBuilder(s).reverse().toString();

        return Math.max(calc(s), calc(revs));
    }

    private int calc(String s) {
        int n = s.length();
        int[] first = new int[2];
        int[] last = new int[2];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);
        char[] c = s.toCharArray();
        for (int i = 0; i < n; ++i) {
            int cind = c[i] - '0';
            if (first[cind] == -1) {
                first[cind] = i;
            }
            last[cind] = i;
        }
        Map<Integer, Integer> pre = new HashMap<>();
        pre.put(0, -1);
        int cur = 0;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int cind = c[i] - '0';
            if (cind == 1) {
                ++cur;
            } else {
                --cur;
            }
            if (pre.containsKey(cur)) {
                int diff = i - pre.get(cur);
                res = Math.max(res, diff);
            }
            if (first[0] <= i && last[1] > i) {
                if (pre.containsKey(cur + 2)) {
                    int diff = i - pre.get(cur + 2);
                    res = Math.max(res, diff);
                }
            }
            if (first[1] <= i && last[0] > i) {
                if (pre.containsKey(cur - 2)) {
                    int diff = i - pre.get(cur - 2);
                    res = Math.max(res, diff);
                }
            }
            pre.putIfAbsent(cur, i);
        }
        return res;
    }
}
