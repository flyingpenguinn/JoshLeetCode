import base.TreeNode;

/*
LC#572
Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.
Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
Return false.
 */
public class SubtreeOfAnotherTree {

    // two different recursions, don't mingle them together!
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return hassub(s, t);
    }

    // here t is the root of the t tree
    boolean hassub(TreeNode s, TreeNode t){
        if(sametree(s, t)){
            return true;
        }
        return s==null? false: hassub(s.left, t) || hassub(s.right, t);
    }

    // here t is a node in old t tree
    boolean sametree(TreeNode s, TreeNode t){
        if(s==null && t== null){
            return true;
        }
        if(s==null || t==null){
            return false;
        }
        if(s.val != t.val){
            return false;
        }
        return sametree(s.left, t.left) && sametree(s.right, t.right);
    }
}
