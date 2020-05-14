import base.TreeNode;
import base.Trees;

import java.util.HashMap;
import java.util.Map;

/*
LC#968
Given a binary tree, we install cameras on the nodes of the tree.

Each camera at a node can monitor its parent, itself, and its immediate children.

Calculate the minimum number of cameras needed to monitor all nodes of the tree.



Example 1:


Input: [0,0,null,0,0]
Output: 1
Explanation: One camera is enough to monitor all nodes if placed as shown.
Example 2:


Input: [0,0,null,0,null,0,null,null,0]
Output: 2
Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.

Note:

The number of nodes in the given tree will be in the range [1, 1000].
Every node has value 0.
 */
// vertex cover in binary tree
public class BinaryTreeCameras {
    int Max = 1000000;
    Map<TreeNode, Map<Integer, Integer>> dp = new HashMap<>();

    public int minCameraCover(TreeNode root) {
        // start with 1: think of root's p as covered
        return dfs(root, 1);
    }

    // 0: parent has camera
    // 1: p doesnt have camera but covered
    // 2: p not covered need this to cover
    int dfs(TreeNode n, int status) {
        if (n == null) {
            return status == 2 ? Max : 0;
            // 2 means p needs help so max to show impossibility
        }
        Map<Integer, Integer> cm = dp.getOrDefault(n, new HashMap<>());
        Integer ch = cm.get(status);
        if (ch != null) {
            return ch;
        }
        int c = dfs(n.left, 0) + dfs(n.right, 0) + 1;
        int nc = Max;
        if (status == 0) {
            nc = dfs(n.left, 1) + dfs(n.right, 1);
        } else if (status == 1) {
            // one of the kids must cover. note not both of them
            int nc1 = dfs(n.left, 2) + dfs(n.right, 1);
            int nc2 = dfs(n.left, 1) + dfs(n.right, 2);
            nc = Math.min(nc1, nc2);
        }// if status == 2 we must have camera here
        int rt = Math.min(c, nc);
        cm.put(status, rt);
        dp.put(n, cm);
        return rt;
    }
}
