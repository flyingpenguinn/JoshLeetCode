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
    // binary search: enumerate how many nodes on the last level
    int r = 0;

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int lh = 0;
        TreeNode p = root;
        // lh from 0
        while (p.left != null) {
            lh++;
            p = p.left;
        }
        int l = 1;
        int pu = (int) Math.pow(2, lh);
        int base = pu - 1;
        int u = pu;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (notnull(root, lh, mid, pu)) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u + base;
    }

    // bs on node count of last level whether it's null
    boolean notnull(TreeNode n, int remh, int mid, int pu) {
        if (remh == 0) {
            return n != null;
        }
        if (mid <= pu / 2) {
            return notnull(n.left, remh - 1, mid, pu / 2);
        } else {
            return notnull(n.right, remh - 1, mid - pu / 2, pu / 2);
        }
    }
}

class CountCompleteTreeNodeAlternative {
    // also Olgn^2. make it a recursive problem
    public int countNodes(TreeNode root) {
        return doc(root);
    }

    private int doc(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lh = geth(root.left);
        int rh = geth(root.right);
        if (lh == rh) {
            // left fully populated, check right further
            int lc = (int) Math.pow(2, lh) - 1;// lh from 1
            return lc + doc(root.right) + 1;// right tree can be incomplete
        } else {
            // lch must > rh. right tree must be one level shorter. left is a complete tree but may not be full, need to check further
            int rc = (int) Math.pow(2, rh) - 1;
            return doc(root.left) + rc + 1;
        }
    }

    private int geth(TreeNode root) {
        int h = 0;
        while (root != null) {
            h++;
            root = root.left;
        }
        return h;
    }
}
