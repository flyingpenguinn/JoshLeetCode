public class TotalSumOfInteractionCostInTreeGroupsII {
    // virtual tree
    private List<Integer>[] adj;
    private int[][] lift;
    private int[] depth;
    private int[] tin;
    private int[] tout;
    private int bits;
    private int timer;

    public long interactionCosts(int n, int[][] edges, int[] group) {
        adj = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        bits = 1;
        while ((1 << bits) <= n) {
            ++bits;
        }

        lift = new int[bits][n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];

        buildTree(n);
        buildLifting(n);

        List<Integer>[] groups = new ArrayList[n+1];
        for (int i = 0; i <= n; ++i) {
            groups[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; ++i) {
            groups[group[i]].add(i);
        }

        long res = 0;

        for (int g = 1; g <= n; ++g) {
            if (groups[g].size() <= 1) {
                continue;
            }

            res += solveGroup(groups[g], g, group);
        }

        return res;
    }

    private long solveGroup(List<Integer> base, int g, int[] group) {
        base.sort((a, b) -> Integer.compare(tin[a], tin[b]));

        List<Integer> all = new ArrayList<>();

        for (int v : base) {
            all.add(v);
        }

        for (int i = 1; i < base.size(); ++i) {
            all.add(lca(base.get(i - 1), base.get(i)));
        }

        all.sort((a, b) -> Integer.compare(tin[a], tin[b]));

        List<Integer> nodes = new ArrayList<>();

        for (int v : all) {
            if (nodes.isEmpty() || nodes.get(nodes.size() - 1) != v) {
                nodes.add(v);
            }
        }

        int m = nodes.size();
        int[] parent = new int[m];
        Arrays.fill(parent, -1);

        int[] st = new int[m];
        int top = 0;

        for (int i = 0; i < m; ++i) {
            int v = nodes.get(i);

            while (top > 0 && !isAncestor(nodes.get(st[top - 1]), v)) {
                --top;
            }

            if (top > 0) {
                parent[i] = st[top - 1];
            }

            st[top++] = i;
        }

        long[] cnt = new long[m];

        for (int i = 0; i < m; ++i) {
            if (group[nodes.get(i)] == g) {
                cnt[i] = 1;
            }
        }

        long res = 0;
        long total = base.size();

        for (int i = m - 1; i >= 0; --i) {
            int p = parent[i];

            if (p == -1) {
                continue;
            }

            int v = nodes.get(i);
            int pv = nodes.get(p);

            long d = depth[v] - depth[pv];

            res += d * cnt[i] * (total - cnt[i]);

            cnt[p] += cnt[i];
        }

        return res;
    }

    private void buildTree(int n) {
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        int[] next = new int[n];
        int[] st = new int[n];

        int top = 0;
        st[top++] = 0;
        parent[0] = 0;

        while (top > 0) {
            int u = st[top - 1];

            if (next[u] == 0) {
                tin[u] = timer++;
                lift[0][u] = parent[u];
            }

            if (next[u] == adj[u].size()) {
                tout[u] = timer - 1;
                --top;
                continue;
            }

            int v = adj[u].get(next[u]++);

            if (v == parent[u]) {
                continue;
            }

            parent[v] = u;
            depth[v] = depth[u] + 1;
            st[top++] = v;
        }
    }

    private void buildLifting(int n) {
        for (int k = 1; k < bits; ++k) {
            for (int i = 0; i < n; ++i) {
                lift[k][i] = lift[k - 1][lift[k - 1][i]];
            }
        }
    }

    private int lca(int a, int b) {
        if (isAncestor(a, b)) {
            return a;
        }

        if (isAncestor(b, a)) {
            return b;
        }

        int cur = a;

        for (int k = bits - 1; k >= 0; --k) {
            int p = lift[k][cur];

            if (!isAncestor(p, b)) {
                cur = p;
            }
        }

        return lift[0][cur];
    }

    private boolean isAncestor(int a, int b) {
        return tin[a] <= tin[b] && tout[b] <= tout[a];
    }
}
