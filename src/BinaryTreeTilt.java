import base.TreeNode;

/*]
LC#563
Given a binary tree, return the tilt of the whole tree.

The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values. Null node has tilt 0.

The tilt of the whole tree is defined as the sum of all nodes' tilt.

Example:
Input:
         1
       /   \
      2     3
Output: 1
Explanation:
Tilt of node 2 : 0
Tilt of node 3 : 0
Tilt of node 1 : |2-3| = 1
Tilt of binary tree : 0 + 0 + 1 = 1
Note:

The sum of node values in any subtree won't exceed the range of 32-bit integer.
All the tilt values won't exceed the range of 32-bit integer.
 */
public class BinaryTreeTilt {
    public int findTilt(TreeNode root) {
        return dfs(root)[0];
    }

    // tilt and sum
    private int[] dfs(TreeNode n){
        if(n==null){
            return new int[]{0,0};
        }
        int[] lr = dfs(n.left);
        int[] rr = dfs(n.right);
        int cur = Math.abs(lr[1]-rr[1]);
        int sum = lr[1]+rr[1]+n.val;
        return new int[]{cur+lr[0]+rr[0], sum};
    }

}
