import base.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HeightOfBinaryTreeAfterSubtreeRemoval {
    private Map<Integer, PriorityQueue<int[]>> m = new HashMap<>();
    private Map<Integer, Integer> dm = new HashMap<>();

    public int[] treeQueries(TreeNode root, int[] queries) {
        // the contribution to depth is the depth itself + height.
        // we only need to keep top 2 of the heights for the same depth
        int allhigh = dfs(root, 0) - 1;
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int q = queries[i];
            int cd = dm.get(q);
            PriorityQueue<int[]> pq = m.get(cd);
            int[] top = pq.poll();
            if (pq.isEmpty()) {
                res[i] = allhigh - top[0];
            } else if (pq.peek()[1] == q) {
                // peek is the smaller one

                int diff = pq.peek()[0] - top[0];
                res[i] = allhigh - diff;


            } else {
                res[i] = allhigh;
            }
            pq.offer(top);
        }
        return res;
    }

    // return height, pass in depth
    private int dfs(TreeNode root, int d) {
        if (root == null) {
            return 0;
        }
        dm.put(root.val, d);
        int lh = dfs(root.left, d + 1);
        int rh = dfs(root.right, d + 1);
        int ch = Math.max(lh, rh) + 1;
        PriorityQueue<int[]> pq = m.getOrDefault(d, new PriorityQueue<>((x, y) -> Integer.compare(x[0], y[0])));
        pq.offer(new int[]{ch, root.val}); // height, value
        if (pq.size() > 2) {
            pq.poll();
        }
        m.put(d, pq);

        return ch;
    }
}

class HeightOfBinaryTreeAfterSubtreeRemovalEulerTour {
    // subtree is a continuous subarray. its height does not impact anything outside
    int n = 0;
    int idx = 0;
    int[] tin, tout, depthAt;

    public int[] treeQueries(TreeNode root, int[] queries) {
        n = count(root);

        tin = new int[n + 1];
        tout = new int[n + 1];
        depthAt = new int[n];

        dfs(root, 0);

        int[] pref = new int[n];
        int[] suff = new int[n];

        for (int i = 0; i < n; i++) {
            pref[i] = depthAt[i];
            if (i > 0) pref[i] = Math.max(pref[i], pref[i - 1]);
        }

        for (int i = n - 1; i >= 0; i--) {
            suff[i] = depthAt[i];
            if (i + 1 < n) suff[i] = Math.max(suff[i], suff[i + 1]);
        }

        int[] res = new int[queries.length];

        for (int qi = 0; qi < queries.length; qi++) {
            int q = queries[qi];

            int l = tin[q];
            int r = tout[q];

            int best = 0;

            if (l > 0) {
                best = Math.max(best, pref[l - 1]);
            }

            if (r + 1 < n) {
                best = Math.max(best, suff[r + 1]);
            }

            res[qi] = best;
        }

        return res;
    }

    private void dfs(TreeNode node, int d) {
        if (node == null) return;

        int cur = idx;
        tin[node.val] = cur;
        depthAt[idx] = d;
        idx++;

        dfs(node.left, d + 1);
        dfs(node.right, d + 1);

        tout[node.val] = idx - 1;
    }

    private int count(TreeNode node) {
        if (node == null) return 0;
        return 1 + count(node.left) + count(node.right);
    }

}
