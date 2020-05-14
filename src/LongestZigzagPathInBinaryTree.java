import base.TreeNode;

import java.util.HashMap;
import java.util.Map;

/*
LC#1372
Given a binary tree root, a ZigZag path for a binary tree is defined as follow:

Choose any node in the binary tree and a direction (right or left).
If the current direction is right then move to the right child of the current node otherwise move to the left child.
Change the direction from right to left or right to left.
Repeat the second and third step until you can't move in the tree.
Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).

Return the longest ZigZag path contained in that tree.



Example 1:



Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
Output: 3
Explanation: Longest ZigZag path in blue nodes (right -> left -> right).
Example 2:



Input: root = [1,1,1,null,1,null,null,1,1,null,1]
Output: 4
Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).
Example 3:

Input: root = [1]
Output: 0


Constraints:

Each tree has at most 50000 nodes..
Each node's value is between [1, 100].
 */
public class LongestZigzagPathInBinaryTree {
    // can use the same way as 1374, dont need to explicitly dp
    int max = 0;

    public int longestZigZag(TreeNode root) {
        dol(root);
        return max - 1;
    }

    private int[] dol(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = dol(root.left);
        int[] right = dol(root.right);
        int curleft = left[1] + 1;
        int curright = right[0] + 1;
        int cur = Math.max(curleft, curright);
        max = Math.max(max, cur);
        return new int[]{curleft, curright};
    }

}
