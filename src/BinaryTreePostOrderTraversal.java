import base.TreeNode;

import java.util.*;

/*
LC#145
Given a binary tree, return the postorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [3,2,1]
Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreePostOrderTraversal {
    // visit root, then put right on top of left. then reverse. note this is not real "traversal" just a trick for printing...
    public List<Integer> postorderTraversal(TreeNode root) {

        Deque<TreeNode> stack = new LinkedList<>();
        // pushkids only pushed one layer so need a flag to tell when we see a node: whether we pushed its kids or not
        stack.push(root);
        List<Integer> r = new ArrayList<>();
        while (!stack.isEmpty()) {
            TreeNode top = stack.pop();
            if (top != null) {
                r.add(top.val);
                // since we reverse later
                stack.push(top.left);
                stack.push(top.right);
            }
        }
        // we did top down right left,should be down top left right.so reverse
        Collections.reverse(r);
        return r;
    }
}

class BinaryTreePostOrder {
    // use prev to check if this node has been pushed or not
    public List<Integer> postorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> r = new ArrayList<>();
        if (root == null) {
            return r;
        }
        TreeNode prev = null;  // prev is the last visited node
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left == null && node.right == null) {
                r.add(node.val);
                prev = node;
            } else if ((node.right != null && prev == node.right) || (node.right == null && prev == node.left)) {
                r.add(node.val);
                prev = node;
            } else {
                // first visit, put BOTh children in stack so that we dont need to go back and check left/right
                stack.push(node);
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
        }
        return r;
    }
}
