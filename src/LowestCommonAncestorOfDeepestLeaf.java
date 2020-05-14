import base.TreeNode;

public class LowestCommonAncestorOfDeepestLeaf {
    int maxdc = 0;
    int maxd = -1;
    TreeNode found = null;

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null) {
            return null;
        }
        dfsdepth(root, 0);
        //  System.out.println(maxd);
        //  System.out.println(maxdc);

        dfsfind(root, 0);
        return found;
    }

    // get max depth
    void dfsdepth(TreeNode n, int d) {
        if (n == null) {
            return;
        }
        if (n.left == null && n.right == null) {
            if (d > maxd) {
                maxd = d;
                maxdc = 1;
            } else if (d == maxd) {
                maxdc++;
            }
        } else {
            dfsdepth(n.left, d + 1);
            dfsdepth(n.right, d + 1);
        }

    }

    // how many depth== maxd leaves in subtree of n
    int dfsfind(TreeNode n, int d) {
        if (found != null) {
            return -1;
        }

        if (n == null) {
            return 0;
        }
        int curcount = 0;
        if (n.left == null && n.right == null && d == maxd) {
            curcount = 1;
        }
        int left = dfsfind(n.left, d + 1);
        int right = dfsfind(n.right, d + 1);
        if (left + right + curcount == maxdc) {
            if (found == null) {
                // avoid overriding
                found = n;
            }
        }
        return left + right + curcount;
    }
}

class LowestCommonAncestorOnePass {
    class ReturnValue {
        // maxdepth in this subtree
        int depth;
        // the lca node we got in this subtree. may not be the root
        TreeNode lca;

        public ReturnValue(int depth, TreeNode lca) {
            this.depth = depth;
            this.lca = lca;
        }
    }

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return dolca(root,0).lca;
    }

    private ReturnValue dolca(TreeNode root, int depth) {
        if (root == null) {
            return new ReturnValue(depth, null);
        }
        ReturnValue left = dolca(root.left, depth+1);
        ReturnValue right = dolca(root.right, depth+1);
        // if depth is the same we found an lca at root for left and right tree
        if (left.depth == right.depth) {
            return new ReturnValue(left.depth, root);
        } else {
            return left.depth > right.depth ? new ReturnValue(left.depth, left.lca) : new ReturnValue(right.depth, right.lca);
        }
    }
}
