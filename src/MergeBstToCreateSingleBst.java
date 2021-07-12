import base.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MergeBstToCreateSingleBst {
    private List<Integer> topo = new ArrayList<>();
    private boolean bad = false;

    public TreeNode canMerge(List<TreeNode> trees) {
        int n = trees.size();
        Map<Integer, Integer> roots = new HashMap<>();
        for (int i = 0; i < n; i++) {
            roots.put(trees.get(i).val, i);
        }
        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            TreeNode t = trees.get(i);
            if (t.left != null && roots.containsKey(t.left.val)) {
                g[i].add(roots.get(t.left.val));
            }
            if (t.right != null && roots.containsKey(t.right.val)) {
                g[i].add(roots.get(t.right.val));
            }
        }
        int[] st = new int[n];
        for (int i = 0; i < n; i++) {
            if (st[i] == 0) {
                dfs(g, i, st);
                if (bad) {
                    break;
                }
            }
        }
        if (bad) {
            return null;
        }

        for (int i = 0; i < topo.size(); i++) {

            int ri = topo.get(i);
            TreeNode tree = trees.get(ri);
            if (tree.left != null) {
                int tv = tree.left.val;
                if (roots.containsKey(tv)) {
                    int tvi = roots.get(tv);
                    tree.left = trees.get(tvi);
                    //      System.out.println(ri+" l-> "+tvi);

                    roots.remove(tv);
                }
            }
            if (tree.right != null) {
                int tv = tree.right.val;
                if (roots.containsKey(tv)) {
                    int tvi = roots.get(tv);
                    tree.right = trees.get(tvi);

                    //     System.out.println(ri+" r-> "+tvi);

                    roots.remove(tv);
                }
            }
        }
        if (roots.size() != 1) {
            return null;
        }
        TreeNode root = trees.get(topo.get(n - 1));
        if (valid(root, 0, 100000)) {
            return root;
        }
        return null;
    }

    private void dfs(List<Integer>[] g, int i, int[] st) {
        st[i] = 1;
        for (int j : g[i]) {
            if (st[j] == 1) {
                bad = true;
                return;
            } else if (st[j] == 0) {
                dfs(g, j, st);
            }
        }
        st[i] = 2;
        topo.add(i);
    }

    private boolean valid(TreeNode n, int l, int u) {
        if (n == null) {
            return true;
        }
        if (n.val < l || n.val > u) {
            return false;
        }
        return valid(n.left, l, n.val - 1) && valid(n.right, n.val + 1, u);
    }
}
