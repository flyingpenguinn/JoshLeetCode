import base.TreeNode;

import java.util.*;

/*
LC#549
Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:

Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].


Example 2:

Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].


Note: All the values of tree nodes are in the range of [-1e7, 1e7].
 */
public class BinaryTreeMaxConsecutivePathII {
    public int longestConsecutive(TreeNode root) {
        return dol(root)[2];
    }

    // cur is min, cur is max, max result in this subtree
    int[] dol(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0, 0};
        }
        int[] left = dol(root.left);
        int[] right = dol(root.right);
        int curmin = 1;
        int curmax = 1;
        boolean leftbg1 = root.left == null || root.val == root.left.val + 1;
        boolean leftsm1 = root.left == null || root.val == root.left.val - 1;
        boolean rightbg1 = root.right == null || root.val == root.right.val + 1;
        boolean rightsm1 = root.right == null || root.val == root.right.val - 1;
        if (leftbg1) {
            curmax = left[1] + 1;
        }
        if (leftsm1) {
            curmin = left[0] + 1;
        }
        if (rightbg1) {
            curmax = Math.max(curmax, right[1] + 1);
        }
        if (rightsm1) {
            curmin = Math.max(curmin, right[0] + 1);
        }
        // two paths or just one sided
        int cur = Math.max(curmax, curmin);
        if (leftbg1 && rightsm1) {
            cur = Math.max(cur, left[1] + right[0] + 1);
        } else if (leftsm1 && rightbg1) {
            cur = Math.max(cur, left[0] + right[1] + 1);
        }
        int rtmax = Math.max(cur, Math.max(left[2], right[2]));
        int[] rt = new int[]{curmin, curmax, rtmax};
        return rt;
    }
}

class BinaryTreeMaxConsecutivePathConvertGraph {
    // also O(n) but slower solution
    public int longestConsecutive(TreeNode root) {
        dfs(root, null);
        List<TreeNode> v = new ArrayList<>(g.keySet());
        Collections.sort(v, (x, y) -> Integer.compare(x.val, y.val));
        int max = 0;
        for (int i = 0; i < v.size(); i++) {
            TreeNode vi = v.get(i);
            int cur = dp(vi);
            max = Math.max(max, cur);
        }
        return max;
    }

    void dfs(TreeNode root, TreeNode p) {
        if (root == null) {
            return;
        }
        List<TreeNode> cur = new ArrayList<>();

        if (p != null) {
            cur.add(p);
        }
        if (root.left != null) {
            cur.add(root.left);
        }
        if (root.right != null) {
            cur.add(root.right);
        }
        g.put(root, cur);
        dfs(root.left, root);
        dfs(root.right, root);
    }

    Map<TreeNode, Integer> dp = new HashMap<>();
    Map<TreeNode, List<TreeNode>> g = new HashMap<>();

    int dp(TreeNode n) {
        //  System.out.println("at "+ n.val);
        if (n == null) {
            return 0;
        }
        Integer ch = dp.get(n);
        if (ch != null) {
            return ch;
        }
        List<TreeNode> next = g.getOrDefault(n, new ArrayList<>());
        int max = 0;
        for (TreeNode ne : next) {
            if (ne.val == n.val + 1) {
                int later = dp(ne);
                max = Math.max(max, later);
            }
        }
        int rt = max + 1;
        dp.put(n, rt);
        return rt;
    }
}
