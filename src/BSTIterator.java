import base.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#173
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.



Example:



BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false


Note:

next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.
 */
public class BSTIterator {

    // when we push a node we always push its whole left subtree
// so that we know we can visit it directly when we see it popped

    Deque<TreeNode> st = new ArrayDeque<>();
    TreeNode n = null;
    boolean ch = false;

    void allleft(TreeNode node) {
        while (node != null) {
            st.push(node);
            node = node.left;
        }
    }

    public BSTIterator(TreeNode root) {
        allleft(root);
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        if (hasNext()) {
            ch = false;
            return n.val;
        }
        return -1;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        if (ch) {
            return n != null;
        }
        if (st.isEmpty()) {
            return false;
        }
        n = st.pop();
        allleft(n.right);
        ch = true;
        return true;
    }
}
