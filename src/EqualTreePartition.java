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
    // can't be the whole tree! so exclude root
    // can't use %2==1 we have negative values...
    boolean found = false;
    int sum = 0;

    public boolean checkEqualTree(TreeNode root) {
        sum = dfs1(root);

        if (sum % 2 != 0) {
            return false;
        }
        dfs2(root, root);
        return found;
    }

    int dfs1(TreeNode n) {
        return n == null ? 0 : dfs1(n.left) + dfs1(n.right) + n.val;
    }

    int dfs2(TreeNode n, TreeNode root) {
        if (n == null) {
            return 0;
        }
        if (found) {
            return -1;
        }
        int left = dfs2(n.left, root);
        int right = dfs2(n.right, root);
        int cursum = left + right + n.val;
        if (cursum == sum / 2) {
            if (n != root) {
                // can't be the whole tree!
                found = true;
            }
        }
        return cursum;
    }
}
