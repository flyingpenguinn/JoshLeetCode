import base.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
LC#337
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:

Input: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \
     3   1

Output: 7
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:

Input: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \
 1   3   1

Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 */
public class HouseRobberIII {
    // classical problem: max independent set of the tree
    private Map<TreeNode, Integer> dp = new HashMap<>();

    public int rob(TreeNode root) {
        return dorob(root);
    }

    private int dorob(TreeNode n) {
        if (n == null) {
            return 0;
        }
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        int left = dorob(n.left);
        int right = dorob(n.right);
        int ll = n.left == null ? 0 : dorob(n.left.left);
        int lr = n.left == null ? 0 : dorob(n.left.right);
        int rl = n.right == null ? 0 : dorob(n.right.left);
        int rr = n.right == null ? 0 : dorob(n.right.right);
        int way1 = n.val + ll + lr + rl + rr;
        int way2 = left + right;
        int rt = Math.max(way1, way2);
        dp.put(n, rt);
        return rt;
    }
}
