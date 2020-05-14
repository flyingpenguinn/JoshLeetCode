import base.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
LC#94
Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreeInorderNonRecursive {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> r = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        pushAllLeft(root, stack);
        while (!stack.isEmpty()) {
            r.add(stack.peek().val);
            pushAllLeft(stack.pop().right, stack);
        }
        return r;
    }

    void pushAllLeft(TreeNode n, Deque<TreeNode> stack) {
        while (n != null) {
            stack.push(n);
            n = n.left;
        }
    }
}

class BinaryTreeInorderMorrisThreading {
    // o(1) space if we dont count in the result array
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> r = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                r.add(cur.val);
                cur = cur.right;
            } else {
                TreeNode p = cur.left;
                while (p.right != null) {
                    p = p.right;
                }
                p.right = cur;
                // avoid the loop.if dobt want to destroy input we can check if visited by going down again
                TreeNode ol = cur.left;
                cur.left = null;
                cur = ol;
            }
        }
        return r;
    }
}
