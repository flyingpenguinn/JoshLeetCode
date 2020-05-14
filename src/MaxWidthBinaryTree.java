import base.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class MaxWidthBinaryTree {

    // note we talk about width in complete tree here so using complete tree index
    Map<Integer, Integer> small = new HashMap<>();
    Map<Integer, Integer> big = new HashMap<>();
    int maxwidth = 0;

    public int widthOfBinaryTree(TreeNode root) {
        dfs(root, 0, 0);
        return maxwidth;
    }

    private void dfs(TreeNode root, int d, int w) {
        int curmin = small.getOrDefault(d, Integer.MAX_VALUE);
        int curmax = big.getOrDefault(d, Integer.MIN_VALUE);
        int min = Math.min(curmin, w);
        int max = Math.max(curmax, w);
        maxwidth = Math.max(maxwidth, max - min);
        small.put(d, min);
        big.put(d, max);
        dfs(root.left, d + 1, w - 1);
        dfs(root.right, d + 1, w + 1);
    }
}
