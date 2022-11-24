import base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class ClosestNodeQueriesInBst {
    private TreeSet<Integer> ts = new TreeSet<>();

    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        dfs(root);
        List<List<Integer>> res = new ArrayList<>();
        for (int q : queries) {
            Integer minq = ts.floor(q);
            int minv = minq == null ? -1 : minq;
            Integer maxq = ts.ceiling(q);
            int maxv = maxq == null ? -1 : maxq;
            res.add(List.of(minv, maxv));
        }
        return res;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        ts.add(root.val);
        dfs(root.left);
        dfs(root.right);
    }
}
