import java.util.*;

public class LongestPathWithDifferentAdjChars {
    private Map<Integer, Set<Integer>> t = new HashMap<>();
    private int res = 0;

    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        for (int i = 0; i < n; ++i) {
            int p = parent[i];
            if (p != -1) {
                t.computeIfAbsent(p, k -> new HashSet<>()).add(i);
            }
        }
        dfs(0, s);
        return res;
    }

    private int dfs(int i, String s) {
        int v1 = 0;
        int v2 = 0;
        for (int ne : t.getOrDefault(i, new HashSet<>())) {
            int cur = dfs(ne, s);
            if (s.charAt(ne) == s.charAt(i)) {
                continue;
            }
            if (cur > v1) {
                v2 = v1;
                v1 = cur;
            } else if (cur > v2) {
                v2 = cur;
            }
        }
        int cur1 = v1 + v2 + 1;
        res = Math.max(res, cur1);
        return v1 + 1;
    }
}
