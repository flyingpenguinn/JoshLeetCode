import base.TreeNode;
import base.Trees;

/*
LC#236
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]




Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.


Note:

All of the nodes' values will be unique.
p and q are different and both values will exist in the binary tree.
 */
public class LowestCommonAncestorOfBinaryTree {
    TreeNode r = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return r;
    }

    // unique, so just need a boolean to indicate if found p OR q in this subtree
    boolean dfs(TreeNode n, TreeNode p, TreeNode q) {
        if (n == null) {
            return false;
        }
        boolean fl = dfs(n.left, p, q);
        boolean fr = dfs(n.right, p, q);
        if (fl && fr) {
            r = n;
            return true;
        }
        if (fl || fr) {
            if (n == p || n == q) { // fl or fr can only be the other one that we need
                r = n;
            }
            return true;
        }
        return n.val == p.val || n.val == q.val;
    }

    public static void main(String[] args) {
        LowestCommonAncestorOfBinaryTree lca = new LowestCommonAncestorOfBinaryTree();
        System.out.println(lca.lowestCommonAncestor(Trees.fromString("3,5,1,6,2,0,8,null,null,7,4"), new TreeNode(4), new TreeNode(5)));
    }
}
