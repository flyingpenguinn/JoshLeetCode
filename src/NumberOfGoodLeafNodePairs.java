import base.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class NumberOfGoodLeafNodePairs {
    private int res = 0;

    public int countPairs(TreeNode root, int distance) {
        if (root == null) {
            return 0;
        }
        dfs(root, distance, 0);
        return res;
    }

    // depth and their count
    private Map<Integer, Integer> dfs(TreeNode n, int d, int depth) {
        if (n.left == null && n.right == null) {
            Map<Integer, Integer> m = new HashMap<>();
            m.put(depth, 1);
            return m;
        } else if (n.left == null) {
            return dfs(n.right, d, depth + 1);
        } else if (n.right == null) {
            return dfs(n.left, d, depth + 1);
        } else {
            Map<Integer, Integer> left = dfs(n.left, d, depth + 1);
            Map<Integer, Integer> right = dfs(n.right, d, depth + 1);
            for (int k1 : left.keySet()) {
                for (int k2 : right.keySet()) {
                    // use depth diff to get leaf  dist
                    if ((k1 - depth) + (k2 - depth) <= d) {
                        res += left.get(k1) * right.get(k2);
                    }
                }
            }
            Map<Integer, Integer> rt = new HashMap<>(left);
            for (int k : right.keySet()) {
                rt.put(k, rt.getOrDefault(k, 0) + right.get(k));
            }
            return rt;
        }
    }
}
