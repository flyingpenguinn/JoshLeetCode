import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NumberOfWaysToReconstructTree {
    // at each step: find node with out degree n-1
    // find connected components without it
    // dfs on these components. if any of them 0, return 0. if any of them 2, return 2.otherwise, if only one possible root, 1, otherwise, 2
    // complexity E*n, may have room to improve
    public int checkWays(int[][] ps) {
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (int i = 0; i < ps.length; i++) {
            int x = ps[i][0];
            int y = ps[i][1];
            g.computeIfAbsent(x, k -> new HashSet<>()).add(y);
            g.computeIfAbsent(y, k -> new HashSet<>()).add(x);
        }
        return dfs(g.keySet(), g);
    }

    private int dfs(Set<Integer> nodes, Map<Integer, Set<Integer>> g) {
        if (nodes.isEmpty() || nodes.size() == 1) {
            return 1;
        }
        //   System.out.println("nodes..."+nodes);
        // find root
        int root = 0;
        int maxsize = 0;
        int n = nodes.size();
        for (int k : nodes) {
            int size = g.get(k).size();
            if (size == n - 1) {
                root = k;
                maxsize++;
            }
        }
        if (maxsize == 0) {
            return 0;
        }
        //  System.out.println("found root as "+root);
        for (int k : g.keySet()) {
            g.get(k).remove(root);
        }
        g.remove(root);
        //  System.out.println ("new g "+g);
        Set<Integer> keys = new HashSet<>(g.keySet());
        for (int k : keys) {

            Set<Integer> comp = new HashSet<>();
            dfswithroot(k, g, comp);
            //    System.out.println("found comp "+comp +   " using " +k);
            int later = dfs(comp, g);
            if (later != 1) {
                return later;
            }
        }
        return maxsize > 1 ? 2 : 1;
    }

    private void dfswithroot(int n, Map<Integer, Set<Integer>> g, Set<Integer> seen) {
        seen.add(n);
        for (int next : g.getOrDefault(n, new HashSet<>())) {
            if (!seen.contains(next)) {
                dfswithroot(next, g, seen);
            }
        }
    }
}
