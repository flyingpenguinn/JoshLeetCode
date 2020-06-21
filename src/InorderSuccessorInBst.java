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
    // using stack here. we can doaway with a stack to traverse from the top again and determine
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Deque<TreeNode> st = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null && cur != p) {
            if (cur.val == p.val) {
                break;
            } else if (cur.val < p.val) {
                st.push(cur);
                cur = cur.right;
            } else {
                st.push(cur);
                cur = cur.left;
            }
        }
        if (cur == null) {
            return null;
        }
        if (cur.right != null) {
            cur = cur.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        } else {
            while (!st.isEmpty()) {
                if (st.peek().left == cur) {
                    return st.peek();
                } else {
                    cur = st.pop();
                }
            }
            return null;
        }
    }
}
