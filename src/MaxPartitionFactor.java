import java.util.*;

public class MaxPartitionFactor {
    // binary search + bipartite graph check!
    public int maxPartitionFactor(int[][] ps) {
        int n = ps.length;
        if (n == 2) {
            return 0;
        }
        int maxdist = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {

                int x1 = ps[i][0];
                int x2 = ps[j][0];
                int y1 = ps[i][1];
                int y2 = ps[j][1];
                int dist = Math.abs(x1 - x2) + Math.abs(y1 - y2);
                maxdist = Math.max(maxdist, dist);
            }
        }
        int l = 0;
        int u = maxdist+1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            //   System.out.println("mi="+mid);
            if (doable(ps, mid)) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    private boolean doable(int[][] ps, int t) {
        int n = ps.length;
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {

                int x1 = ps[i][0];
                int x2 = ps[j][0];
                int y1 = ps[i][1];
                int y2 = ps[j][1];
                int dist = Math.abs(x1 - x2) + Math.abs(y1 - y2);
                if (dist < t) {
                    g.computeIfAbsent(i, k -> new HashSet<>()).add(j);
                    g.computeIfAbsent(j, k -> new HashSet<>()).add(i);
                }
            }
        }

        return isbipartite(g);
    }

    private boolean isbipartite(Map<Integer, Set<Integer>> g) {
        if (g.isEmpty()) {
            return true;
        }
        Map<Integer, Integer> color = new HashMap<>();
        for (int k : g.keySet()) {
            if (color.containsKey(k)) {
                continue;
            }
            boolean later = checkbipartite(g, k, color, 0);
            if(!later){
                return false;
            }
        }
        return true;
    }

    private boolean checkbipartite(Map<Integer, Set<Integer>> g, int cur, Map<Integer, Integer> color, int cc) {
        color.put(cur, cc);
        int ncc = cc ^ 1;
        for (int ne : g.getOrDefault(cur, new HashSet<>())) {
            if (color.containsKey(ne)) {
                if (color.get(ne) == ncc) {
                    continue;
                } else {
                    return false;
                }
            }
            boolean later = checkbipartite(g, ne, color, ncc);
            if (!later) {
                return false;
            }
        }
        return true;
    }
}
