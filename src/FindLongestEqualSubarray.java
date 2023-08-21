import java.util.*;

public class FindLongestEqualSubarray {
    public int longestEqualSubarray(List<Integer> a, int k) {
        int n = a.size();
        int j = 0;
        Map<Integer, Integer> m = new HashMap<>();
        int res = 0;
        TreeMap<Integer, Set<Integer>> tm = new TreeMap<>();
        for (int i = 0; i < n; ++i) {
            adjust(a, m, tm, i, 1);

            while (j <= i) {
                int maxv = tm.lastKey();
                int diff = i - j + 1;
                int others = diff - maxv;
                if (others > k) {
                    adjust(a, m, tm, j, -1);
                    ++j;
                } else {
                    break;
                }
            }
            int maxv = tm.lastKey();
            res = Math.max(res, maxv);
        }
        return res;
    }

    protected void adjust(List<Integer> a, Map<Integer, Integer> m, TreeMap<Integer, Set<Integer>> tm, int i, int delta) {
        int v = a.get(i);
        int oldv = m.getOrDefault(v, 0);
        int newv = oldv + delta;
        m.put(v, newv);
        if (oldv > 0) {
            tm.get(oldv).remove(v);
            if (tm.get(oldv).isEmpty()) {
                tm.remove(oldv);
            }
        }
        Set<Integer> nset = tm.getOrDefault(newv, new HashSet<>());
        nset.add(v);
        tm.put(newv, nset);
    }
}
