import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ReachableNodesWithRestrictions {
    private int res = 0;
    private Map<Integer, Set<Integer>> g = new HashMap<>();
    private Set<Integer> seen = new HashSet<>();

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        for (int[] e : edges) {
            g.computeIfAbsent(e[0], k -> new HashSet<>()).add(e[1]);
            g.computeIfAbsent(e[1], k -> new HashSet<>()).add(e[0]);
        }
        for (int r : restricted) {
            seen.add(r);
        }
        dfs(0);
        return res;
    }

    private void dfs(int i) {
        seen.add(i);
        ++res;
        for (int ne : g.getOrDefault(i, new HashSet<>())) {
            if (!seen.contains(ne)) {
                dfs(ne);
            }
        }
    }
}
