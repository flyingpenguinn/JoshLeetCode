import base.TreeNode;

/*
LC#450
Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).

Example:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7
 */
public class DeleteNodeInBst {
    // find-> check right subtree, if null just use left -> otherwise check p.right.left-> finally get the leftest p.right.left subtree
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode p = root;
        TreeNode pa = null;
        while (p != null && p.val != key) {
            if (p.val < key) {
                pa = p;
                p = p.right;
            } else {
                pa = p;
                p = p.left;
            }
        }
        if (p == null) {
            return root; // empty tree or nothing found
        }
        if (p.right == null) {
            // p.left could be null too but we don't care
            if (pa == null) {
                return p.left;
            } else if (pa.left == p) {
                pa.left = p.left;
            } else {
                pa.right = p.left;
            }
            return root;
        } else {
            if (p.right.left == null) {
                p.val = p.right.val;
                p.right = p.right.right;
            } else {
                TreeNode pr = p.right;
                TreeNode pd = p.right.left;
                while (pd.left != null) {
                    pr = pd;
                    pd = pd.left;
                }
                p.val = pd.val;
                pr.left = pd.right;
            }
            return root; // we are not deleting p at all we are just deleting its successor
        }
    }
}
