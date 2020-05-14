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
    Map<TreeNode, Integer> dp = new HashMap<>();

    public int rob(TreeNode root) {
        return dor(root);
    }

    int dor(TreeNode n) {
        if (n == null) {
            return 0;
        }
        Integer ch = dp.get(n);
        if (ch != null) {
            return ch;
        }
        int wo = dor(n.left) + dor(n.right);
        int with = n.val;
        if (n.left != null) {
            with += dor(n.left.left);
            with += dor(n.left.right);
        }
        if (n.right != null) {
            with += dor(n.right.left);
            with += dor(n.right.right);
        }
        int rt = Math.max(wo, with);
        dp.put(n, rt);
        return rt;
    }
}
