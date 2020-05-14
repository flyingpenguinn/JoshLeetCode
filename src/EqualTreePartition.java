import base.TreeNode;

import java.util.HashSet;
import java.util.Set;

/*
LC#663
Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees which have the equal sum of values after removing exactly one edge on the original tree.

Example 1:
Input:
    5
   / \
  10 10
    /  \
   2   3

Output: True
Explanation:
    5
   /
  10

Sum: 15

   10
  /  \
 2    3

Sum: 15
Example 2:
Input:
    1
   / \
  2  10
    /  \
   2   20

Output: False
Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.
Note:
The range of tree node value is in the range of [-100000, 100000].
1 <= n <= 10000
 */
public class EqualTreePartition {
    // special care when sum of whole tree == 0
    TreeNode root = null;
    Set<Integer> set = new HashSet<>();

    public boolean checkEqualTree(TreeNode root) {
        this.root = root;
        int sum = dfs(root);
        // -3%2== -1 not 1
        if (sum % 2 != 0) {
            return false;
        }
        return set.contains(sum / 2);
    }

    int dfs(TreeNode n) {
        int sum = n == null ? 0 : (dfs(n.left) + dfs(n.right) + n.val);
        if (n != root && n != null) {
            set.add(sum);
        }
        return sum;
    }
}
