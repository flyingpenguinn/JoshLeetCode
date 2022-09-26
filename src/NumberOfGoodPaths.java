import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

public class NumberOfGoodPaths {
    // for each node, store smaller adjs in the list to acheve the goal of filtering by node value
    private int[] pa;

    private int find(int i) {
        if (pa[i] == -1) {
            return i;
        }
        int rt = find(pa[i]);
        pa[i] = rt;
        return rt;
    }

    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int N = vals.length;
        pa = new int[N];
        Arrays.fill(pa, -1);
        ArrayList<Integer>[] adj = new ArrayList[N];
        TreeMap<Integer, ArrayList<Integer>> sameValues = new TreeMap<>();
        int ans = 0;

        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();

            if (!sameValues.containsKey(vals[i])) sameValues.put(vals[i], new ArrayList<>());
            sameValues.get(vals[i]).add(i);
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];

            if (vals[u] >= vals[v]) {
                adj[u].add(v);
            } else {
                adj[v].add(u);
            }
        }


        for (int val : sameValues.keySet()) {
            for (int u : sameValues.get(val)) {
                for (int v : adj[u]) {
                    union(u, v);
                }
            }

            HashMap<Integer, Integer> group = new HashMap<>();

            for (int u : sameValues.get(val)) {
                int au = find(u);
                group.put(au, group.getOrDefault(au, 0) + 1);
            }

            ans += sameValues.get(val).size();

            for (int key : group.keySet()) {
                int size = group.get(key);
                ans += size * (size - 1) / 2;
            }
        }

        return ans;

    }

    private void union(int i, int j) {
        int ai = find(i);
        int aj = find(j);
        if (ai == aj) {
            return;
        }
        pa[ai] = aj;
    }
}
