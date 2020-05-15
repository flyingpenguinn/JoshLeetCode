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

public class BinaryTreeCameras {
    // vertex cover in binary tree, with a twist on status maintaining. here only thinking about gp is not enough
    Map<TreeNode, Map<Integer, Integer>> dp = new HashMap<>();

    public int minCameraCover(TreeNode root) {
        return dom(root, 2);
    }

    int Max = 10000000;
    // 1: parent has camera, we can but dont need to put a camera
    // 2. parent has no camera, but covered by gp or some children
    // 3. parent has no camera and we must cover it

    private int dom(TreeNode root, int status) {
        if (root == null) {
            return status == 3 ? Max : 0;
        }
        Map<Integer, Integer> cm = dp.getOrDefault(root, new HashMap<>());
        Integer ch = cm.get(status);
        if (ch != null) {
            return ch;
        }
        int rt = 0;
        int put = 1 + dom(root.left, 1) + dom(root.right, 1);
        if (status == 3) {
            rt = put;
        }
        if (status == 1) {
            int noput = dom(root.left, 2) + dom(root.right, 2);
            rt = Math.min(put, noput);
        }
        if (status == 2) {
            int leftcover = dom(root.left, 3) + dom(root.right, 2);
            int rightcover = dom(root.left, 2) + dom(root.right, 3);
            int noput = Math.min(leftcover, rightcover);
            rt = Math.min(put, noput);
        }
        cm.put(status, rt);
        dp.put(root, cm);
        return rt;
    }
}
