import base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/*
LC#515
You need to find the largest value in each row of a binary tree.

Example:
Input:

          1
         / \
        3   2
       / \   \
      5   3   9

Output: [1, 3, 9]
 */
public class FindLargestValueEachRow {
    List<Integer> r = new ArrayList<>();

    public List<Integer> largestValues(TreeNode root) {
        dfs(root, 0);
        return r;
    }

    void dfs(TreeNode root, int d) {
        if (root == null) {
            return;
        }
        if (r.size() == d) {
            r.add(root.val);
        } else if (r.get(d) < root.val) {
            r.set(d, root.val);
        }
        dfs(root.left, d + 1);
        dfs(root.right, d + 1);
    }
}
