import base.TreeNode;

import java.util.*;

public class PathSumIII {

    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, 1);
        return dop(root, sum, m, 0);

    }

    int dop(TreeNode root, int sum, Map<Integer, Integer> m, int psum) {
        if (root == null) {
            return 0;
        }
        int r = 0;
        int np = psum + root.val;
        int target = np - sum;
        int count = m.getOrDefault(target, 0);
        r += count;
        m.put(np, m.getOrDefault(np, 0) + 1);
        r += dop(root.left, sum, m, np);
        r += dop(root.right, sum, m, np);
        m.put(np, m.getOrDefault(np, 0) - 1);
        return r;
    }
}
