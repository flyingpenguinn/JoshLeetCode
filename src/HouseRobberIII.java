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
        if (root == null) {
            return 0;
        }
        if (dp.containsKey(root)) {
            return dp.get(root);
        }
        int way1 = rob(root.left) + rob(root.right);
        int way2 = root.val;
        if (root.left != null) {
            way2 += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            way2 += rob(root.right.left) + rob(root.right.right);
        }
        int rt = Math.max(way1, way2);
        dp.put(root, rt);
        return rt;
    }
}
