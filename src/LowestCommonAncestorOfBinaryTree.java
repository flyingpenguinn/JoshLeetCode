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
    // all values unique in tree. note we can handle the case where lca doesnt exist we return null
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root, p, q)[0];
    }

    // if lca, return lca+null
    // otherwise, return null and either p or q found
    private TreeNode[] dfs(TreeNode n, TreeNode p, TreeNode q){
        if(n==null){
            return new TreeNode[]{null, null};
        }
        TreeNode[] left = dfs(n.left, p, q);
        if(left[0] != null){
            return left;
        }
        TreeNode[] right = dfs(n.right, p,q);
        if(right[0] != null){
            return right;
        }
        if(left[1]!= null && right[1]!= null){
            return new TreeNode[]{n, null};
        } else if (left[1] != null || right[1] != null){
            if(n==p || n==q){
                return new TreeNode[]{n, null};
            }else{
                return new TreeNode[]{null, left[1]!= null? left[1]: right[1]};
            }
        } else{
            if(n==p || n==q){
                return new TreeNode[]{null,n};
            }else{
                return new TreeNode[]{null, null};
            }
        }
    }


    public static void main(String[] args) {
        LowestCommonAncestorOfBinaryTree lca = new LowestCommonAncestorOfBinaryTree();
        System.out.println(lca.lowestCommonAncestor(Trees.fromString("3,5,1,6,2,0,8,null,null,7,4"), new TreeNode(4), new TreeNode(5)));
    }
}

class LcaSimpler {
    // return p or q or the lca. use the fact that lca must exist. so if ==q or ==q, just return....
    public TreeNode lowestCommonAncestor(TreeNode n, TreeNode p, TreeNode q) {
        if (n == null || n == p || n == q) {
            return n;
        }
        TreeNode left = lowestCommonAncestor(n.left, p, q);
        TreeNode right = lowestCommonAncestor(n.right, p, q);
        if (left != null && right != null) {
            return n;
        } else if (left != null) {
            return left;
        } else {
            return right;
        }
    }
}