import java.util.*;

public class FindNumberOfCoinesToPlaceInTreeNode {
    class TreeNode {
        int v;
        long c;
        int subs = 1;

        public TreeNode(int v, long c) {
            this.v = v;
            this.c = c;
        }

        long max = -1;
        PriorityQueue<Long> pq1 = new PriorityQueue<>(); // all pos
        PriorityQueue<Long> pq2 = new PriorityQueue<>(Collections.reverseOrder());
    }

    private Map<Integer, TreeNode> nm = new HashMap<>();
    private Map<Integer, List<Integer>> tree = new HashMap<>();

    public long[] placedCoins(int[][] edges, int[] cost) {
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            tree.computeIfAbsent(v1, k -> new ArrayList<>()).add(v2);
            tree.computeIfAbsent(v2, k -> new ArrayList<>()).add(v1);
        }
        int n = cost.length;
        for (int i = 0; i < n; ++i) {
            TreeNode cur = new TreeNode(i, cost[i]);
            if (cost[i] < 0) {
                cur.pq2.offer(Long.valueOf(cost[i]));
            } else {
                cur.pq1.offer(Long.valueOf(cost[i]));
                cur.max = cost[i];
            }
            nm.put(i, cur);

        }
        dfs(0, -1, cost);

        long[] res = new long[n];
        for (int i = 0; i < n; ++i) {
            TreeNode node = nm.get(i);
            if (node.subs < 3) {
                res[i] = 1;
            } else {
                long allpos = 0;
                if (node.pq1.size() >= 3) {
                    long v1 = node.pq1.poll();
                    long v2 = node.pq1.poll();
                    long v3 = node.pq1.poll();
                    allpos = v1 * v2 * v3;
                }
                long hasneg = 0;
                if (node.pq2.size() >= 2 && node.max > 0) {
                    long v1 = node.pq2.poll();
                    long v2 = node.pq2.poll();
                    hasneg = v1 * v2 * node.max;
                }
                res[i] = Math.max(allpos, hasneg);
            }
        }
        return res;
    }

    private void dfs(int i, int p, int[] cost) {
        TreeNode cur = nm.get(i);
        for (int ne : tree.getOrDefault(i, new ArrayList<>())) {
            if (ne == p) {
                continue;
            }
            dfs(ne, i, cost);
            TreeNode nen = nm.get(ne);
            cur.subs += nen.subs;
            cur.max = Math.max(cur.max, nen.max);
            PriorityQueue<Long> nepq1 = new PriorityQueue<>(nen.pq1);
            while (!nepq1.isEmpty()) {
                cur.pq1.offer(nepq1.poll());
                if (cur.pq1.size() > 3) {
                    cur.pq1.poll();
                }
            }
            PriorityQueue<Long> nepq2 = new PriorityQueue<>(nen.pq2);
            while (!nepq2.isEmpty()) {
                cur.pq2.offer(nepq2.poll());
                if (cur.pq2.size() > 2) {
                    cur.pq2.poll();
                }
            }
        }
    }
}
