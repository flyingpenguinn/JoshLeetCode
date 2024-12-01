import java.util.*;

public class IdentifyNumberOfTargetNodesAfterConnectingTreesI {
    // greedy: best way is to connect self to some other node in t2. find the node that can "cover" the most in t2
    private Map<Integer, Set<Integer>> t1 = new HashMap<>();
    private Map<Integer, Set<Integer>> t2 = new HashMap<>();
    private int t2offset = 10000;

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int n = edges1.length + 1;
        for (int[] e : edges1) {
            int v1 = e[0];
            int v2 = e[1];
            t1.computeIfAbsent(v1, p -> new HashSet<>()).add(v2);
            t1.computeIfAbsent(v2, p -> new HashSet<>()).add(v1);
        }
        for (int[] e : edges2) {
            int v1 = t2offset + e[0];
            int v2 = t2offset + e[1];
            t2.computeIfAbsent(v1, p -> new HashSet<>()).add(v2);
            t2.computeIfAbsent(v2, p -> new HashSet<>()).add(v1);
        }
        int m = edges2.length + 1;
        int bestcover = -1;
        int bestcand = -1;
        for (int j = 0; j < m; ++j) {
            int start = t2offset + j;
            int ccover = dfs2(t2, start, -1, 0, k - 1);
            if (ccover > bestcover) {
                bestcover = ccover;
                bestcand = start;
            }
        }
        int[] res = new int[n];
        int ri = 0;
        for (int root : t1.keySet()) {
            int cres = 0;
            t1.computeIfAbsent(root, p -> new HashSet<>()).add(bestcand);
            int cur = dfs(root, -1, 0, k);
            cres = Math.max(cres, cur);
            t1.computeIfAbsent(root, p -> new HashSet<>()).remove(bestcand);

            res[ri++] = cres;
        }
        return res;
    }

    private int dfs2(Map<Integer, Set<Integer>> t2, int i, int p, int ec, int k) {
        if (ec > k) {
            return 0;
        }
        int res = 1;
        for (int ne : t2.getOrDefault(i, new HashSet<>())) {
            if (ne == p) {
                continue;
            }
            int later = dfs2(t2, ne, i, ec + 1, k);
            res += later;
        }
        return res;
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Set<Integer>[] g = new HashSet[n];
        List<Integer> res = new ArrayList<>();
        // leaves in each round
        for (int i = 0; i < n; i++) {
            g[i] = new HashSet<>();
        }
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        for (int i = 0; i < n; i++) {
            if (g[i].size() <= 1) {
                res.add(i);
            }
        }
        int rem = n;
        // can't use res.size()>2. we must have processed at least n-2 elements!
        // note we iterate by generations! here generation matters as the last generation is the answer
        // if we dont iterate by generation we won't be able to tell which one in the last 2 is the answer, or both of them.
        // we will have to judge the last 2 by checking their height one by one
        while (rem > 2) {
            List<Integer> nextleaves = new ArrayList<>();
            for (int i = 0; i < res.size(); i++) {
                int j = res.get(i);
                for (int ne : g[j]) {
                    g[j].remove(ne);
                    g[ne].remove(j);
                    if (g[ne].size() == 1) {
                        nextleaves.add(ne);
                    }
                }
            }
            rem -= res.size();
            res = nextleaves;
        }
        return res;
    }

    private int dfs(int i, int p, int ec, int k) {
        if (ec > k) {
            return 0;
        }
        int res = 1;
        for (int ne : t1.getOrDefault(i, new HashSet<>())) {
            if (ne == p) {
                continue;
            }
            int later = dfs(ne, i, ec + 1, k);
            res += later;
        }
        for (int ne : t2.getOrDefault(i, new HashSet<>())) {
            if (ne == p) {
                continue;
            }
            int later = dfs(ne, i, ec + 1, k);
            res += later;
        }
        return res;
    }
}
