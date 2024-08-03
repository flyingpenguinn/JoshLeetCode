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
    private Map<Integer, Long> m = new HashMap<>();
    private long res = 0;

    private void dfs(TreeNode n, long i, int d) {
        if (n == null) {
            return;
        }
        if (!m.containsKey(d)) {
            m.put(d, i);
        }
        long cur = i - m.get(d) + 1; // use deducted value to avoid overflow. because the value can be held in int, the gap is no bigger than 1<<32. so on next level long can still hold them
        res = Math.max(res, cur);
        dfs(n.left, cur * 2, d + 1);
        dfs(n.right, cur * 2 + 1, d + 1);
    }

    public int widthOfBinaryTree(TreeNode n) {
        dfs(n, 1, 1);
        return (int) res;
    }
}
