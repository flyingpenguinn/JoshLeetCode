import base.TreeNode;
import base.Trees;

/*
LC#99
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Example 1:

Input: [1,3,null,null,2]

   1
  /
 3
  \
   2

Output: [3,1,null,null,2]

   3
  /
 1
  \
   2
Example 2:

Input: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

Output: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
Follow up:

A solution using O(n) space is pretty straight forward.
Could you devise a constant space solution?
 */
public class RecoverBst {
    private TreeNode pre = null;
    private TreeNode n1 = null;
    private TreeNode n2 = null;

    public void recoverTree(TreeNode root) {
        dfs(root);
        if (n1 != null && n2 != null) {
            int tmp = n1.val;
            n1.val = n2.val;
            n2.val = tmp;
        }
    }

    private void dfs(TreeNode n) {
        if (n == null) {
            return;
        }
        dfs(n.left);
        if (n1 == null && pre != null && n.val < pre.val) {
            n1 = pre;
        }
        if (n1 != null && (n2 == null || n.val < n2.val)) {
            n2 = n;
        }
        pre = n;
        dfs(n.right);
    }

}

class RecoverBstMorris {
    // find cliff and min after it. this is O(1) space in order traversal...
    TreeNode bg = null;
    TreeNode sm = null;
    TreeNode pre = null;

    public void recoverTree(TreeNode root) {
        morris(root);
        swap(bg, sm);
    }

    // use morris traversal to get O1 space

    void morris(TreeNode n) {
        TreeNode cur = n;
        while (cur != null) {

            if (cur.left == null) {
                visit(cur);
                cur = cur.right;
            } else {
                TreeNode p = cur.left;
                // get predecessor

                while (p.right != null && p.right != cur) {
                    p = p.right;
                }
                // threaded before,restore tree

                if (p.right == cur) {

                    p.right = null;
                    visit(cur);
                    cur = cur.right;
                } else {
                    //not threaded before do it
                    p.right = cur;
                    cur = cur.left;
                }
            }
        }
    }

    void visit(TreeNode n) {

        if (bg == null && pre != null && n.val < pre.val) {

            bg = pre;
            sm = bg;// smallest after bg
        }
        if (sm != null && n.val < sm.val) {
            sm = n;
        }
        pre = n;
    }

    void swap(TreeNode n1, TreeNode n2) {
        int tmp = n1.val;
        n1.val = n2.val;
        n2.val = tmp;
    }
}
