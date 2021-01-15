import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinimizingHammingDistAfterSwapping {
    // we can freely move in the same component
    public int minimumHammingDistance(int[] s, int[] t, int[][] es) {

        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (int i = 0; i < s.length; i++) {
            g.put(i, new HashSet<>());
        }
        for (int[] e : es) {
            g.get(e[0]).add(e[1]);
            g.get(e[1]).add(e[0]);
        }
        //   System.out.println(g);
        Set<Integer> seen = new HashSet<>();
        int res = 0;
        for (int k : g.keySet()) {
            if (seen.contains(k)) {
                continue;
            }
            Set<Integer> comp = new HashSet<>();
            dfs(k, g, comp, seen);

            Map<Integer, Integer> sm = new HashMap<>();
            Map<Integer, Integer> tm = new HashMap<>();
            for (int i : comp) {
                sm.put(s[i], sm.getOrDefault(s[i], 0) + 1);
                tm.put(t[i], tm.getOrDefault(t[i], 0) + 1);
            }
            int diff = 0;
            for (int tk : tm.keySet()) {
                int tc = tm.get(tk);
                int sc = sm.getOrDefault(tk, 0);
                if (sc <= tc) {
                    diff += tc - sc;
                }
            }
            res += diff;
        }
        return res;
    }

    private void dfs(int k, Map<Integer, Set<Integer>> g, Set<Integer> comp, Set<Integer> seen) {
        comp.add(k);
        seen.add(k);
        for (int next : g.getOrDefault(k, new HashSet<>())) {
            if (!seen.contains(next)) {
                dfs(next, g, comp, seen);
            }
        }
    }
}
