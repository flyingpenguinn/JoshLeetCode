import base.TreeNode;

/*
LC#298
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

Example 1:

Input:

   1
    \
     3
    / \
   2   4
        \
         5

Output: 3

Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
Example 2:

Input:

   2
    \
     3
    /
   2
  /
 1

Output: 2

Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
 */
public class BinaryTreeLongestConsecutivePath {

    public int longestConsecutive(TreeNode root) {
        return dfs(root)[1];
    }

    // at node, parent value is rv, how many (==accu) nodes before n already consecutive
    // nax at the subtree of this n
    int[] dfs(TreeNode n) {
        if (n == null) {
            return new int[]{0, 0};
        }
        int curmin = 1;
        int[] left = dfs(n.left);
        int[] right = dfs(n.right);

        boolean leftsm1 = n.left == null || n.left.val - 1 == n.val;
        boolean rightsm1 = n.right == null || n.right.val - 1 == n.val;

        if (leftsm1) {
            curmin = left[0] + 1;
        }
        if (rightsm1) {
            curmin = Math.max(curmin, right[0] + 1);
        }
        int rtmax = Math.max(curmin, Math.max(left[1], right[1]));
        return new int[]{curmin, rtmax};
    }
}
