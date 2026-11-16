import base.TreeNode;

public class LowestCommonAncestorOfBinaryTreeII {
    // 2026-11-15 daily redo. p or q may be missing, so we must NOT short-circuit:
    // recurse into both children BEFORE checking cur, so every node is visited and cnt counts both p and q.
    // Return value: the LCA if found below, otherwise whichever of p / q was seen (or null).
    // Both children non-null can only mean one side has p and the other has q (an LCA found inside one side
    // makes the other side null), so `left != null && right != null` would be an equivalent, shorter check.
    // 2 WAs: (1) checked only left == p && right == q (missed the swapped case); (2) wrote left == p || left == q
    // in the pass-up branch, but left can also be the LCA. Lesson: the return value is overloaded (p / q / LCA),
    // so branch on null vs non-null ("found something"), never on WHICH node it is.
    private int cnt = 0;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode found = dfs(root, p, q);
        if (cnt == 2) {
            return found;
        } else {
            return null;
        }
    }

    private TreeNode dfs(TreeNode cur, TreeNode p, TreeNode q) {
        if (cur == null) {
            return null;
        }
        TreeNode left = dfs(cur.left, p, q);
        TreeNode right = dfs(cur.right, p, q);
        if (cur == p || cur == q) {
            ++cnt;
            return cur;
        } else if (left == p && right == q || left == q && right == p) {
            return cur;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        // [3,5,1,6,2,0,8,null,null,7,4]
        TreeNode[] t = new TreeNode[11];
        int[] v = {3, 5, 1, 6, 2, 0, 8, 7, 4};
        for (int i = 0; i < v.length; ++i) {
            t[i] = new TreeNode(v[i]);
        }
        t[0].left = t[1]; t[0].right = t[2];
        t[1].left = t[3]; t[1].right = t[4];
        t[2].left = t[5]; t[2].right = t[6];
        t[4].left = t[7]; t[4].right = t[8];
        System.out.println(new LowestCommonAncestorOfBinaryTreeII().lowestCommonAncestor(t[0], t[1], t[2]).val); // 3
        System.out.println(new LowestCommonAncestorOfBinaryTreeII().lowestCommonAncestor(t[0], t[1], t[8]).val); // 5
        System.out.println(new LowestCommonAncestorOfBinaryTreeII().lowestCommonAncestor(t[0], t[1], new TreeNode(10))); // null
    }
}
