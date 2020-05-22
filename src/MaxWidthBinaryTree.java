import base.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
LC#662
Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

Example 1:

Input:

           1
         /   \
        3     2
       / \     \
      5   3     9

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:

Input:

          1
         /
        3
       / \
      5   3

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:

Input:

          1
         / \
        3   2
       /
      5

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:

Input:

          1
         / \
        3   2
       /     \
      5       9
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).


Note: Answer will in the range of 32-bit signed integer.
 */
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
