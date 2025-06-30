import java.util.Map;
import java.util.TreeMap;

public class LontestCommonPrefixBetweenAdjStringsAfterRemoval {
    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    private final TreeMap<Integer, Integer> tm = new TreeMap<>();

    public int[] longestCommonPrefix(String[] ws) {
        int n = ws.length;
        int[] next = new int[n];
        int[] next2 = new int[n];
        for (int i = 0; i + 1 < n; ++i) {
            String s1 = ws[i];
            String s2 = ws[i + 1];
            int pref = commonPref(s1, s2);
            update(tm, pref, 1);
            next[i] = pref;
            if (i + 2 < n) {
                int pref2 = commonPref(s1, ws[i + 2]);
                next2[i] = pref2;
            }
        }
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            int remove1 = -1;
            if (i - 1 >= 0) {
                remove1 = next[i - 1];
                update(tm, remove1, -1);
            }
            int remove2 = -1;
            if (i + 1 < n) {
                remove2 = next[i];
                update(tm, remove2, -1);
            }
            int add = -1;
            if (i - 1 >= 0 && i + 1 < n) {
                add = next2[i - 1];
                update(tm, add, 1);
            }
            if (tm.isEmpty()) {
                res[i] = 0;
            } else {
                res[i] = tm.lastKey();
            }
            if (remove1 != -1) {
                update(tm, remove1, 1);
            }
            if (remove2 != -1) {
                update(tm, remove2, 1);
            }
            if (add != -1) {
                update(tm, add, -1);
            }
        }
        return res;
    }

    private int commonPref(String s1, String s2) {
        int n = Math.min(s1.length(), s2.length());
        for (int i = 0; i < n; ++i) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return i;
            }
        }
        return n;
    }
}
