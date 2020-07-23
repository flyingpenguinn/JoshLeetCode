import base.TreeNode;
import base.Trees;

/*
LC#776
Given a Binary Search Tree (BST) with root node root, and a target value V, split the tree into two subtrees where one subtree has nodes that are all smaller or equal to the target value, while the other subtree has all nodes that are greater than the target value.  It's not necessarily the case that the tree contains a node with value V.

Additionally, most of the structure of the original tree should remain.  Formally, for any child C with parent P in the original tree, if they are both in the same subtree after the split, then node C should still have the parent P.

You should output the root TreeNode of both subtrees after splitting, in any order.

Example 1:

Input: root = [4,2,6,1,3,5,7], V = 2
Output: [[2,1],[4,3,6,null,null,5,7]]
Explanation:
Note that root, output[0], and output[1] are TreeNode objects, not arrays.

The given tree [4,2,6,1,3,5,7] is represented by the following diagram:

          4
        /   \
      2      6
     / \    / \
    1   3  5   7

while the diagrams for the outputs are:

          4
        /   \
      3      6      and    2
            / \           /
           5   7         1
Note:

The size of the BST will not exceed 50.
The BST is always valid and each node's value is different.
 */
public class SplitBst {

    private TreeNode smhead = null;
    private TreeNode bghead = null;

    public TreeNode[] splitBST(TreeNode root, int v) {
        dfs(root, v, null, null);
        return new TreeNode[]{smhead, bghead};
    }

    private void dfs(TreeNode n, int v, TreeNode sm, TreeNode bg) {
        if (n == null) {
            return;
        }
        TreeNode left = n.left;
        TreeNode right = n.right;
        TreeNode lastsm = sm;
        TreeNode lastbg = bg;
        if (n.val <= v) {
            if (sm != null) {
                setChild(n, sm);
            } else {
                smhead = n;
            }
            lastsm = n;
        } else {
            if (bg != null) {
                setChild(n, bg);
            } else {
                bghead = n;
            }
            lastbg = n;
        }
        // dont forget to set null
        n.left = null;
        n.right = null;
        dfs(left, v, lastsm, lastbg);
        dfs(right, v, lastsm, lastbg);
    }

    private void setChild(TreeNode n, TreeNode pa) {
        if (n.val > pa.val) {
            pa.right = n;
        } else {
            pa.left = n;
        }
    }
}

class SplitBstSimpler {

    // full recursion! return the pair to the upper level
    public TreeNode[] splitBST(TreeNode root, int v) {
        return dos(root, v);
    }

    private TreeNode[] dos(TreeNode n, int v) {
        TreeNode[] r = new TreeNode[2];
        if (n == null) {
            return r;
        }
        TreeNode[] left = dos(n.left, v);
        TreeNode[] right = dos(n.right, v);
        TreeNode sm = null;
        TreeNode bg = null;
        if (n.val <= v) {
            // dont even need to think about left again as it's <= by default
            n.right = right[0];
            // accept possible <- in the right
            sm = n;
            bg = right[1];
        } else {
            n.left = left[1];
            bg = n;
            sm = left[0];
        }
        return new TreeNode[]{sm, bg};
    }
}