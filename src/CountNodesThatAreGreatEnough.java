import base.TreeNode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CountNodesThatAreGreatEnough {
    private Map<TreeNode, PriorityQueue<Integer>> tm = new HashMap<>();

    private int res = 0;

    public int countGreatEnoughNodes(TreeNode root, int k) {
        dfs(root, k);
        return res;
    }

    private void addPq(PriorityQueue<Integer> pq, PriorityQueue<Integer> pq2, int k) {
        while (!pq2.isEmpty()) {
            pq.offer(pq2.poll());
            if (pq.size() > k) {
                pq.poll();
            }
        }
    }

    private void dfs(TreeNode n, int k) {
        if (n == null) {
            return;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        if (n.left != null) {
            dfs(n.left, k);
            PriorityQueue<Integer> pql = tm.get(n.left);
            addPq(pq, pql, k);
        }
        if (n.right != null) {
            dfs(n.right, k);
            PriorityQueue<Integer> pqr = tm.get(n.right);
            addPq(pq, pqr, k);
        }
        if (pq.size() >= k && n.val > pq.peek()) {
            ++res;
        }
        pq.offer(n.val);
        if (pq.size() > k) {
            pq.poll();
        }
        tm.put(n, pq);
    }
}
