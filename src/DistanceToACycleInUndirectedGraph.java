import base.ArrayUtils;

import java.util.*;

public class DistanceToACycleInUndirectedGraph {
    private Map<Integer, Set<Integer>> g = new HashMap<>();
    private Set<Integer> circle = new HashSet<>();
    private int nn = 100001;
    private int Max = (int) 2e9;
    private int n;

    public int[] distanceToCycle(int n, int[][] edges) {
        this.n = n;
        for (int[] e : edges) {
            int start = e[0];
            int end = e[1];
            g.computeIfAbsent(start, k -> new HashSet<>()).add(end);
            g.computeIfAbsent(end, k -> new HashSet<>()).add(start);
        }
        List<Integer> nodes = new ArrayList<>();
        Map<Integer, Integer> m = new HashMap<>();
        dfs(0, -1, nodes, m);
        g = process(circle);
        return shortest(nn);
    }

    private void dfs(int i, int from, List<Integer> nodes, Map<Integer, Integer> m) {

        int cur = nodes.size();
        nodes.add(i);
        m.put(i, cur);
        for (int ne : g.get(i)) {
            if (ne == from) {
                continue;
            }
            if (m.containsKey(ne)) {
                int index = m.get(ne);
                // from index to current one
                for (int j = index; j < nodes.size(); ++j) {
                    circle.add(nodes.get(j));
                }
                return;
            }
            dfs(ne, i, nodes, m);
            if (!circle.isEmpty()) {
                return;
            }
        }
        nodes.remove(nodes.size() - 1);
    }

    private Map<Integer, Set<Integer>> process(Set<Integer> circle) {
        Map<Integer, Set<Integer>> ng = new HashMap<>();

        ng.put(nn, new HashSet<>());
        for (int k : g.keySet()) {
            if (circle.contains(k)) {
                for (int v : g.get(k)) {
                    if (circle.contains(v)) {
                        continue;
                    }
                    ng.computeIfAbsent(nn, p -> new HashSet<>()).add(v);
                }
            } else {
                for (int v : g.get(k)) {
                    if (circle.contains(v)) {
                        ng.computeIfAbsent(k, p -> new HashSet<>()).add(nn);
                    } else {
                        ng.computeIfAbsent(k, p -> new HashSet<>()).add(v);
                    }
                }
            }
        }
        return ng;
    }

    private int[] shortest(int src) {
        int[] dist = new int[n];
        Arrays.fill(dist, Max);
        for (int ci : circle) {
            dist[ci] = 0;
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{src, 0});
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int i = top[0];
            int cd = top[1];
            for (int ne : g.get(i)) {
                if (ne == src) {
                    continue;
                }
                int ndist = 1 + cd;
                if (ndist < dist[ne]) {
                    dist[ne] = ndist;
                    q.offer(new int[]{ne, dist[ne]});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new DistanceToACycleInUndirectedGraph().distanceToCycle(7, ArrayUtils.read("[[1,2],[2,3],[3,4],[4,1],[0,1],[5,2],[6,5]]"))));
    }
}
