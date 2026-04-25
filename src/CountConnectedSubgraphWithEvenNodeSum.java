import java.util.*;

public class CountConnectedSubgraphWithEvenNodeSum {
    public int evenSumSubgraphs(int[] a, int[][] es) {
        int n = a.length;
        int res = 0;
        for (int st = 1; st < (1 << n); ++st) {
            Map<Integer, List<Integer>> g = new HashMap<>();
            for (int i = 0; i < n; ++i) {
                if (((st >> i) & 1) == 1) {
                    g.put(i, new ArrayList<>());
                }
            }
            for (int[] e : es) {
                int v1 = e[0];
                int v2 = e[1];
                if (g.containsKey(v1) && g.containsKey(v2)) {
                    g.computeIfAbsent(v1, k -> new ArrayList<>()).add(v2);
                    g.computeIfAbsent(v2, k -> new ArrayList<>()).add(v1);
                }
            }
            if (good(g, a)) {
                ++res;
            }
        }
        return res;
    }

    private boolean good(Map<Integer, List<Integer>> g, int[] a) {
        Set<Integer> seen = new HashSet<>();
        boolean visited = false;
        for (int k : g.keySet()) {
            int sum = dfs(g, a, k, seen);
            if (sum % 2 != 0) {
                return false;
            }
            break;
        }
        for (int k : g.keySet()) {
            if (!seen.contains(k)) {
                return false;
            }
        }
        return true;
    }

    private int dfs(Map<Integer, List<Integer>> g, int[] a, int i, Set<Integer> seen) {
        int n = g.size();
        seen.add(i);
        int res = a[i];
        for (int ne : g.getOrDefault(i, List.of())) {
            if (seen.contains(ne)) {
                continue;
            }
            int other = dfs(g, a, ne, seen);
            res += other;
        }
        return res;
    }

}
