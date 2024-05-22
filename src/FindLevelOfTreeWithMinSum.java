import base.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class FindLevelOfTreeWithMinSum {
    private Map<Integer, Long> m = new HashMap<>();
    private int ml = 0;

    public int minimumLevel(TreeNode root) {
        dfs(root, 1);
        long res = Long.MAX_VALUE;
        int reslevel = -1;
        for (int i = 1; i <= ml; ++i) {
            long cv = m.get(i);
            if (cv < res) {
                res = cv;
                reslevel = i;
            }
        }
        return reslevel;
    }

    private void dfs(TreeNode n, int level) {
        if (n == null) {
            return;
        }
        ml = Math.max(ml, level);
        m.put(level, m.getOrDefault(level, 0L) + n.val);
        dfs(n.left, level + 1);
        dfs(n.right, level + 1);
    }
}
