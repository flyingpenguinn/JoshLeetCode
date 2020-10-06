import base.TreeNode;

public class InsertIntoBst {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode nn = new TreeNode(val);
        TreeNode p = root;
        TreeNode pp = null;
        while (p != null) {
            pp = p;
            if (p.val < val) {
                p = p.right;
            } else {
                p = p.left;
            }
        }
        if (pp == null) {
            return nn;
        } else {
            if (pp.val < val) {
                pp.right = nn;
            } else {
                pp.left = nn;
            }
            return root;
        }
    }
}

class InsertIntoBstRecursion {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        return doInsert(root, val);
    }

    private TreeNode doInsert(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val);
        } else {
            if (node.val < val) {
                node.right = doInsert(node.right, val);
            } else {
                node.left = doInsert(node.left, val);
            }
            return node;
        }
    }
}
