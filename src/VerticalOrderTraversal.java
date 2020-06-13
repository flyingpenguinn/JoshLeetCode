import base.TreeNode;

import java.util.*;

public class VerticalOrderTraversal {

    // for each vertical line, depth to list of nodes. sort if they share the same place
    // note in each vertical line, the depth may not be continuous, and depth may not always increase in our traversial
    Map<Integer, TreeMap<Integer, List<Integer>>> nodemap = new HashMap<>();
    int minv = Integer.MAX_VALUE;
    int maxv = Integer.MIN_VALUE;

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        dfs(root, 0, 0);
        List<List<Integer>> r = new ArrayList<>();
        for (int i = minv; i <= maxv; i++) {
            List<Integer> curr = new ArrayList<>();
            TreeMap<Integer, List<Integer>> cm = nodemap.get(i);
            for (int j : cm.keySet()) {
                List<Integer> inner = cm.get(j);
                curr.addAll(inner);
            }
            r.add(curr);
        }
        return r;
    }

    void dfs(TreeNode n, int v, int d) {
        if (n == null) {
            return;
        }
        minv = Math.min(minv, v);
        maxv = Math.max(maxv, v);

        TreeMap<Integer, List<Integer>> cm = nodemap.getOrDefault(v, new TreeMap<>());
        List<Integer> clist = cm.getOrDefault(d, new ArrayList<>());
        clist.add(n.val);
        Collections.sort(clist);
        cm.put(d, clist);
        nodemap.put(v, cm);
        dfs(n.left, v - 1, d + 1);
        dfs(n.right, v + 1, d + 1);
    }
}
