import java.util.*;

public class CountPairsOfNodes {
    // binary search each node find how many if combined >t
    // there are two types:
    // connected, we count deg1+deg2-edges
    // not connected, we count deg1+deg2
    // note when calculating not connected, we need to take bad guys in connected out
    private int[] deg;
    private List<Integer>[] g; // deg[other]-connected
    private List<Integer>[] g2; // just deg[other] but connected to this node
    private List<Integer> alldeg = new ArrayList<>();

    public int[] countPairs(int n, int[][] edges, int[] queries) {
        deg = new int[n + 1];
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer>[] counter = new HashMap[n + 1];
        g = new ArrayList[n + 1];
        g2 = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
            g2[i] = new ArrayList<>();
            counter[i] = new HashMap<>();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            deg[v1]++;
            deg[v2]++;
            graph.computeIfAbsent(v1, s -> new HashSet<>()).add(v2);
            graph.computeIfAbsent(v2, s -> new HashSet<>()).add(v1);
            counter[v1].put(v2, counter[v1].getOrDefault(v2, 0) + 1);
            counter[v2].put(v1, counter[v2].getOrDefault(v1, 0) + 1);
        }

        for (int i = 1; i <= n; i++) {
            alldeg.add(deg[i]);
        }
        Collections.sort(alldeg);
        for (int v1 : graph.keySet()) {
            for (int v2 : graph.get(v1)) {
                g[v1].add(deg[v2] - counter[v1].getOrDefault(v2, 0));
                g2[v1].add(deg[v2]);
            }
        }
        for (int i = 1; i <= n; i++) {
            Collections.sort(g[i]);
            Collections.sort(g2[i]);
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int t = queries[i];
            int con = 0;
            int uncon = 0;
            for (int j = 1; j <= n; j++) {
                // find connected, qualified
                int count = find(g[j], t - deg[j]);
                con += count;
                // find all qualified, not counting connected or not
                int count2 = find(alldeg, t - deg[j]);
                // take out unqualified but connected
                int count3 = find(g2[j], t - deg[j]);
                count2 -= count3;
                // remember to decrease self
                if (t - deg[j] < deg[j]) {
                    count2--;
                }
                uncon += count2;
            }
            // we are counting each pair twice!
            res[i] = (con + uncon) / 2;
        }
        return res;
    }

    private int find(List<Integer> list, int t) {
        int l = 0;
        int u = list.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (list.get(mid) > t) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return list.size() - l;
    }
}
