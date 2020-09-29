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
    // two cases:
    // 1. at leats one child is null: simple case
    // 2: both children are not null, then swap with the sucessor
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode dummy = new TreeNode(Integer.MAX_VALUE);
        dummy.left = root;
        TreeNode[] nodes = find(dummy, key);
        TreeNode n = nodes[0];
        TreeNode p = nodes[1];
        if(n==null){
            return root;
        }
        if(n.left == null || n.right == null){
            simpleDelete(n, p);
        }else{
            complexDelete(n);
        }
        return dummy.left;
    }

    private TreeNode[] find(TreeNode root, int key){
        TreeNode[] res = new TreeNode[2];
        TreeNode rn = root.left;
        TreeNode p = root;
        while(rn!= null){
            if(rn.val == key){
                res[0] = rn;
                res[1] = p;
                break;
            }else if (rn.val <key){
                p = rn;
                rn = rn.right;
            }else{
                p = rn;
                rn = rn.left;
            }
        }
        return res;
    }

    private void simpleDelete(TreeNode n, TreeNode p){
        // one child is null. covers leaf as well.
        TreeNode later = n.left == null? n.right: n.left;
        if(n==p.left){
            p.left = later;
        }else{
            p.right = later;
        }
    }

    private void complexDelete(TreeNode n){
        TreeNode dn = n.right;
        TreeNode p = n;
        while(dn.left != null){
            p = dn;
            dn = dn.left;
        }
        n.val = dn.val;
        simpleDelete(dn, p);
    }
}
