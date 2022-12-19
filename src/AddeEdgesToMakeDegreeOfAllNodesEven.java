import java.util.*;

public class AddeEdgesToMakeDegreeOfAllNodesEven {
    private Map<Integer, Set<Integer>> m = new HashMap<>();

    public boolean isPossible(int n, List<List<Integer>> edges) {
        int[] deg = new int[n + 1];

        for (List<Integer> e : edges) {
            int v1 = e.get(0);
            int v2 = e.get(1);
            ++deg[v1];
            ++deg[v2];
            m.computeIfAbsent(v1, k -> new HashSet<>()).add(v2);
            m.computeIfAbsent(v2, k -> new HashSet<>()).add(v1);
        }
        int count = 0;
        List<Integer> odds = new ArrayList<>();
        for (int i = 1; i <= n; ++i) {
            if (deg[i] % 2 == 1) {
                odds.add(i);
            }
        }
        if (odds.size() == 0) {
            return true;
        }
        if (odds.size() == 2) {
            int v0 = odds.get(0);
            int v1 = odds.get(1);
            if (!connected(v0, v1)) {
                return true;
            }
            for (int k = 1; k <= n; ++k) {
                if (!connected(v0, k) && !connected(v1, k)) {
                    return true;
                }
            }
        }
        if (odds.size() == 4) {
            int v0 = odds.get(0);
            int v1 = odds.get(1);
            int v2 = odds.get(2);
            int v3 = odds.get(3);
            if ((!connected(v0, v1) && !connected(v2, v3))
                    || (!connected(v0, v2) && !connected(v1, v3))
                    || (!connected(v0, v3) && !connected(v1, v2))) {
                return true;
            }
        }
        return false;
    }

    private boolean connected(int v1, int v2) {
        return m.getOrDefault(v1, new HashSet<>()).contains(v2);
    }
}
