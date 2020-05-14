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
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode p = root;
        TreeNode pa = null;
        while (p != null && p.val != key) {
            pa = p;
            if (p.val < key) {
                p = p.right;
            } else if (p.val > key) {
                p = p.left;
            }
        }
        if (p == null) {  // not found, return root
            return root;
        }
        if (p.right == null) { // empty right, parent link to left tree
            if (pa == null) {
                return pa.left;
            } else if (p == pa.left) {
                pa.left = p.left;
            } else {
                pa.right = p.left;
            }
            return root;
        } else { // has right, use "next val" to sub this node. delete "next val"
            if (p.right.left == null) { // right has no left, directly connect its right
                p.val = p.right.val;
                p.right = p.right.right;
            } else {
                TreeNode rp = p.right.left;
                TreeNode prp = p.right;
                while (rp.left != null) {
                    prp = rp;
                    rp = rp.left;
                }
                p.val = rp.val; // rp has no left, so we connect its right to prp
                prp.left = rp.right;
            }
            return root;
        }
    }
}
