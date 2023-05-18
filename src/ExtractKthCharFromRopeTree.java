
class RopeTreeNode {
    int len;
    String val;
    RopeTreeNode left;
    RopeTreeNode right;

    RopeTreeNode() {
    }

    RopeTreeNode(String val) {
        this.len = 0;
        this.val = val;
    }

    RopeTreeNode(int len) {
        this.len = len;
        this.val = "";
    }

    RopeTreeNode(int len, RopeTreeNode left, RopeTreeNode right) {
        this.len = len;
        this.val = "";
        this.left = left;
        this.right = right;
    }
}

public class ExtractKthCharFromRopeTree {
    public char getKthCharacter(RopeTreeNode root, int k) {
        return dfs(root, k);
    }

    private char dfs(RopeTreeNode n, int k) {
        if (n.len >= k) {
            int leftcount = 0;
            if (n.left == null) {
                leftcount = 0;
            } else if (n.left.len > 0) {
                leftcount = n.left.len;
            } else {
                leftcount = n.left.val.length();
            }
            if (leftcount >= k) {
                return dfs(n.left, k);
            } else {
                return dfs(n.right, k - leftcount);
            }
        } else {
            return n.val.charAt(k - 1);
        }

    }
}
