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

    // only keep nodes whose left subtree is in searching in the stack
    // next node is either left most of the right tree
    // or first ancestor that this node is in its left subtree
    // we only keep nodes whose left subtree is under searching. so when we back out we will see the ancestor above
    Deque<TreeNode> st = new ArrayDeque<>();

    void allleft(TreeNode p) {
        while (p != null) {
            st.push(p);
            p = p.left;
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
            TreeNode p = st.pop();
            allleft(p.right);
            return p.val;
        } else {
            return -1;
        }
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !st.isEmpty();
    }
}
