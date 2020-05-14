import base.TreeNode;

public class BinaryTreeConsecutiveSequenceII {

    class ReturnValue {
        int inc;
        int dec;

        public ReturnValue(int inc, int dec) {
            this.inc = inc;
            this.dec = dec;
        }
    }

    int max = 0;

    public int longestConsecutive(TreeNode root) {
        doLongest(root);
        return max;
    }

    private ReturnValue doLongest(TreeNode root) {
        if (root == null) {
            return new ReturnValue(0, 0);
        }
        ReturnValue left = doLongest(root.left);
        ReturnValue right = doLongest(root.right);
        int inc = 1;
        int dec = 1;
        if (root.left != null) {
            if (root.left.val == root.val - 1) {
                dec = left.dec + 1;
            } else if (root.left.val == root.val + 1) {
                inc = left.inc + 1;
            }
        }
        if (root.right != null) {
            if (root.right.val == root.val - 1) {
                dec = Math.max(dec, right.dec + 1);
            } else if (root.right.val == root.val + 1) {
                inc = Math.max(inc, right.inc + 1);
            }
        }
        // if both inc, we pick the longest, if not, their sum is the value. note w eneed to -1 as we double count the root
        int curpath = inc + dec - 1;
        max = Math.max(max, curpath);
        return new ReturnValue(inc, dec);
    }
}
