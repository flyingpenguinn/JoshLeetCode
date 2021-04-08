import base.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#285
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

The successor of a node p is the node with the smallest key greater than p.val.



Example 1:


Input: root = [2,1,3], p = 1
Output: 2
Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.
Example 2:


Input: root = [5,3,6,2,4,null,null,1], p = 6
Output: null
Explanation: There is no in-order successor of the current node, so the answer is null.


Note:

If the given node has no in-order successor in the tree, return null.
It's guaranteed that the values of the tree are unique.
 */
public class InorderSuccessorInBst {
    // Olgn to find, o1 space
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        if (p.right != null) {
            p = p.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        } else {
            TreeNode cur = root;
            TreeNode parent = null;
            TreeNode goodp = null; // last parent that goes to left child in the path
            while (cur != null) {
                if (parent != null && parent.left == cur) {
                    goodp = parent;
                }
                if (cur == p) {
                    break;
                }
                parent = cur;
                if (cur.val < p.val) {
                    cur = cur.right;
                } else {
                    cur = cur.left;
                }
            }
            if (cur == null) {
                return null;
            }
            return goodp;
        }
    }
}

class InorderSuccessorBstRecursion{
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root==null){
            return null;
        }
        if(root.val <= p.val){
            return inorderSuccessor(root.right, p);
        }else{
            TreeNode left = inorderSuccessor(root.left, p);
            return left==null? root: left;
        }
    }
}
