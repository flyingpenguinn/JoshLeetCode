package base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RollingHash {

    private long base = 1007;
    private long mod1 = 1_000_000_007L;
    private long mod2 = 1_000_000_009L;

    private int findWithRollingHash(String s, int len) {
        int n = s.length();
        Map<Long, List<Integer>> m = new HashMap<>();
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
            if (m.containsKey(key)) {
                // here could just return head if we trust the double ash
                List<Integer> pre = m.get(key);
                for (int start1 : pre) {
                    if (s.regionMatches(head, s, start1, len)) {
                        return head;
                    }

                }
            }
            m.computeIfAbsent(key, k -> new ArrayList<>()).add(head);

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
        return -1;
    }

}
