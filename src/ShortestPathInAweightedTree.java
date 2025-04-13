import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestPathInAweightedTree {

    class FenwickTree {
        private long[] fenwicks; // 1-based indexing

        public FenwickTree(int n) {
            fenwicks = new long[n + 1];
        }

        // Add "val" to index i
        public void update(int i, long val) {
            while (i < fenwicks.length) {
                fenwicks[i] += val;
                i += i & -i;
            }
        }

        // Returns the sum from 1..i
        public long query(int i) {
            long s = 0;
            while (i > 0) {
                s += fenwicks[i];
                i -= i & -i;
            }
            return s;
        }

        // Range update: add "val" to [l..r]
        // We do update(l, +val), update(r+1, -val)
        public void rangeUpdate(int l, int r, long val) {
            update(l, val);
            if (r + 1 < fenwicks.length) {
                update(r + 1, -val);
            }
        }
    }

    public class Solution {
        // Adjacency list: for each node store (neighbor, weight)
        private List<int[]>[] g;
        private int[] inTime, outTime;
        private long[] depth;
        private int n, timer;
        private FenwickTree fenwicks;

        // We will store original edge weights in a Map, keyed by (min(u,v), max(u,v)).
        private Map<Long, Integer> edgeWeightMap = new HashMap<>();

        // We do a DFS from node 1 to assign inTime, outTime, and compute initial depth
        private void dfs(int u, int parent, long distFromRoot) {
            depth[u] = distFromRoot;
            inTime[u] = ++timer;    // Start time in Euler Tour
            for (int[] edge : g[u]) {
                int v = edge[0];
                int w = edge[1];
                if (v == parent) continue;
                dfs(v, u, distFromRoot + w);
            }
            outTime[u] = timer;     // End time in Euler Tour
        }

        // Helper to get a unique key for an edge (u, v)
        private long edgeKey(int u, int v) {
            // Ensure smaller node first to be consistent
            if (u > v) {
                int temp = u;
                u = v;
                v = temp;
            }
            // Combine into a single long: (u << 20) ^ v can work
            // (assuming n <= 10^5, so 20 bits is enough for each node).
            return ((long) u << 20) ^ v;
        }

        public int[] treeQueries(int n, int[][] edges, int[][] queries) {
            this.n = n;

            // Build adjacency list
            g = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                g[i] = new ArrayList<>();
            }

            // Populate graph and store edge weights
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];
                g[u].add(new int[]{v, w});
                g[v].add(new int[]{u, w});
                edgeWeightMap.put(edgeKey(u, v), w);
            }

            // Prepare data structures
            inTime = new int[n + 1];
            outTime = new int[n + 1];
            depth = new long[n + 1];
            timer = 0;

            // DFS from the root (node 1) to get initial depth and Euler Tour inTime/outTime
            dfs(1, -1, 0L);

            // Fenwick tree for range updates over Euler Tour array of size n
            fenwicks = new FenwickTree(n);

            // Process queries
            List<Integer> ans = new ArrayList<>();
            for (int[] q : queries) {
                if (q[0] == 1) {
                    // [1, u, v, w'] => update the weight of edge (u, v) to w'
                    int u = q[1], v = q[2], newW = q[3];
                    long key = edgeKey(u, v);
                    int oldW = edgeWeightMap.get(key);
                    int diff = newW - oldW;

                    // Identify which one is the child in the rooted tree
                    // We compare depth[u] and depth[v], the one with larger depth is child
                    if (depth[u] > depth[v]) {
                        // u is deeper => child = u
                        fenwicks.rangeUpdate(inTime[u], outTime[u], diff);
                    } else {
                        // v is deeper => child = v
                        fenwicks.rangeUpdate(inTime[v], outTime[v], diff);
                    }
                    edgeWeightMap.put(key, newW); // store updated weight
                } else {
                    // [2, x] => compute the shortest path distance from node 1 to x
                    int x = q[1];
                    // The updated distance is original depth[x] + offset from Fenwick
                    long offset = fenwicks.query(inTime[x]);
                    long dist = depth[x] + offset;
                    ans.add((int) dist);
                }
            }

            // Convert to int[]
            int[] result = new int[ans.size()];
            for (int i = 0; i < ans.size(); i++) {
                result[i] = ans.get(i);
            }
            return result;
        }
    }
}
