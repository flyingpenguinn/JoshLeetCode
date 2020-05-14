import base.TreeNode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
LC#501
Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.


For example:
Given BST [1,null,2,2],

   1
    \
     2
    /
   2


return [2].

Note: If a tree has more than one mode, you can return them in any order.

Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
 */
public class FindModeInBst {
    // two passes O(1) space
    int max = 0;
    int maxn = 0;
    TreeNode pre = null;
    int curcount = 0;
    int[] r;
    int ri = 0;

    public int[] findMode(TreeNode root) {
        dfs(root);
        r = new int[maxn];
        pre = null;
        curcount = 0;

        record(root);
        return r;
    }

    void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (pre != null && pre.val == root.val) {
            curcount++;
        } else {
            curcount = 1;
        }

        pre = root;
        if (curcount > max) {
            max = curcount;
            maxn = 1;
        } else if (curcount == max) {
            maxn++;
        }
        dfs(root.right);
    }

    void record(TreeNode root) {
        if (root == null) {
            return;
        }
        record(root.left);
        if (pre != null && pre.val == root.val) {
            curcount++;
        } else {
            curcount = 1;
        }
        pre = root;

        if (curcount == max) {
            r[ri++] = root.val;
        }
        record(root.right);
    }
}


