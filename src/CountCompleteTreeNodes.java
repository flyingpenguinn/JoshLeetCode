import base.TreeNode;

/*
LC#222
Given a complete binary tree, count the number of nodes.

Note:

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example:

Input:
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6
 */
public class CountCompleteTreeNodes {
    private int h(TreeNode n){
        return n==null? 0: 1+h(n.left);
    }
    public int countNodes(TreeNode root) {
        if(root==null){
            return 0;
        }
        int lh = h(root.left);
        int rh = h(root.right);
        if(lh==rh){
            // left full, right need to be checked
            return (1<<lh) + countNodes(root.right);
        }else{
            // left>right, right full, count left
            return (1<<rh) + countNodes(root.left);
        }
    }
}
