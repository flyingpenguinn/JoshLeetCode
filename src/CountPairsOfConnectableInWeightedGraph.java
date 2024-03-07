import java.util.HashMap;
import java.util.Map;

public class CountPairsOfConnectableInWeightedGraph {
    private Map<Integer, Map<Integer, Integer>> g;
    private int n;
    private int speed = 0;
    private boolean[] visited;

    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        n = edges.length + 1;
        g = new HashMap<>();
        speed = signalSpeed;
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            int v = e[2];
            g.computeIfAbsent(v1, p -> new HashMap<>()).put(v2, v);
            g.computeIfAbsent(v2, p -> new HashMap<>()).put(v1, v);
        }

        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            int cur = count(i);
            res[i] = cur;
        }
        return res;
    }

    private int count(int i) {
        // System.out.println("counting "+i);
        visited = new boolean[n];
        visited[i] = true;
        int cp = -1;
        int res = 0;

        for (int ne : g.getOrDefault(i, new HashMap<>()).keySet()) {
            int cd = g.get(i).get(ne);
            int cur = dfs(ne, cd);
            if (cp == -1) {
                cp = cur;
            } else {
                res += cp * cur;
                cp += cur;
            }

        }

        return res;
    }

    private int dfs(int i, int cd) {
        //  System.out.println(i+" "+cd);
        visited[i] = true;
        int res = 0;
        if (cd > 0 && cd % speed == 0) {
            ++res;
        }
        for (int ne : g.getOrDefault(i, new HashMap<>()).keySet()) {
            if (visited[ne]) {
                continue;
            }
            int nd = cd + g.get(i).get(ne);
            int cur = dfs(ne, nd);
            res += cur;

        }
        return res;
    }
}
