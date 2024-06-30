import java.util.*;

public class FindMaxDiamaeterAfterMergingTwoTrees {
    // get diameter of either tree then find the best merge
    private List<Set<Integer>> tree1, tree2;
    private int[] dist1, dist2;
    private int n, m;

    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        this.n = edges1.length + 1;
        this.m = edges2.length + 1;
        tree1 = new ArrayList<>();
        tree2 = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            tree1.add(new HashSet<>());
        }

        for (int i = 0; i < m; i++) {
            tree2.add(new HashSet<>());
        }

        for (int[] edge : edges1) {
            tree1.get(edge[0]).add(edge[1]);
            tree1.get(edge[1]).add(edge[0]);
        }

        for (int[] edge : edges2) {
            tree2.get(edge[0]).add(edge[1]);
            tree2.get(edge[1]).add(edge[0]);
        }

        int diameter1 = getDiameter(tree1, n);
        int diameter2 = getDiameter(tree2, m);

        return Math.max(diameter1, Math.max(diameter2, (diameter1 + 1) / 2 + (diameter2 + 1) / 2 + 1));
    }

    private int getDiameter(List<Set<Integer>> tree, int n) {
        dist1 = new int[n];
        int farthest = bfs(tree, 0, dist1);
        dist2 = new int[n];
        farthest = bfs(tree, farthest, dist2);
        return dist2[farthest];
    }

    private int bfs(List<Set<Integer>> tree, int start, int[] dist) {
        Arrays.fill(dist, (int) 1e9);
        dist[start] = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        int res = start;
        while (!queue.isEmpty()) {
            int top = queue.poll();
            int nd = dist[top] + 1;
            for (int ne : tree.get(top)) {
                if (dist[ne] > nd) {
                    dist[ne] = nd;
                    queue.add(ne);
                    if (dist[ne] > dist[res]) {
                        res = ne;
                    }
                }
            }
        }
        return res;
    }

}
