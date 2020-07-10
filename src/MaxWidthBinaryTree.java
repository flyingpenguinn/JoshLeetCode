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
    private Map<Integer,Integer> min = new HashMap<>();
    private Map<Integer,Integer> max = new HashMap<>();
    private int maxDepth = -1;

    public int widthOfBinaryTree(TreeNode root) {
        dfs(root, 0, 1);
        int r = 0;
        for(int i=0; i<=maxDepth; i++){
            r = Math.max(max.get(i)-min.get(i)+1, r);
        }
        return r;
    }

    private void dfs(TreeNode n, int depth, int index){
        if(n==null){
            return;
        }
        maxDepth = Math.max(depth, maxDepth);
        Integer curmin = min.get(depth);
        if(curmin == null || curmin>index){
            min.put(depth, index);
        }
        Integer curmax = max.get(depth);
        if(curmax==null || curmax<index){
            max.put(depth, index);
        }
        dfs(n.left, depth+1, 2*index);
        dfs(n.right, depth+1, 2*index+1);
    }
}
