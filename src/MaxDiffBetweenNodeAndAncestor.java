import base.TreeNode;

public class MaxDiffBetweenNodeAndAncestor {
    int max = 0;

    public int maxAncestorDiff(TreeNode root) {
        domax(root);
        return max;
    }

    int[] domax(TreeNode node) {
        if (node == null) {
            int[] r = new int[2];
            r[0] = 100001;
            r[1] = -1;
            return r;
        }
        int[] lr = domax(node.left);
        int[] rr = domax(node.right);
        int omin = Math.min(lr[0], rr[0]);
        int omax = Math.max(lr[1], rr[1]);
        if (inRange(omin)) {
            max = Math.max(max, Math.abs(node.val - omin));
        }
        if (inRange(omax)) {
            max = Math.max(max, Math.abs(node.val - omax));
        }
        int[] r = new int[2];
        //   System.out.println(node.val+" "+omin+" "+omax);
        r[0] = Math.min(omin, node.val);
        r[1] = Math.max(omax, node.val);
        return r;
    }

    boolean inRange(int v) {
        return v >= 0 && v <= 100000;
    }
}

class MaxDiffBetweenNodeAncestorTopdown {
    int result = 0;

    public int maxAncestorDiff(TreeNode root) {
        if (root == null) {
            return 0;
        }
        domax(root.left, root.val, root.val);
        domax(root.right, root.val, root.val);
        return result;
    }

    private void domax(TreeNode node, int min, int max) {
        if (node == null) {
            return;
        }
        result = Math.max(Math.abs(node.val-min), result);
        result = Math.max(Math.abs(node.val-max), result);
        int nmin = Math.min(min, node.val);
        int nmax = Math.max(max, node.val);

        domax(node.left, nmin, nmax);
        domax(node.right, nmin, nmax);
    }
}

